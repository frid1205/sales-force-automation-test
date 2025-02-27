package bases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.mail.MessagingException;
import utilization.DB;

@Listeners(bases.TestListener.class)
public class BaseConfig {
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static Actions actions;
	private static long DEFAULT_TIMEOUT = 30000;
	public static SoftAssert sa, se;
	public static ExtentReports extent;
	private static ExtentSparkReporter spark;
	public static ITestContext context;
	public static ExtentTest test;
	public static String browser;
    protected static Connection connection;
    private static Properties properties;
    private static final String CONFIG_FILE = "config.properties";
    private static String currentUrl;
    private static String targetUrl;
    
    public BaseConfig() {
	    properties = new Properties();
	
	    try {
	        FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE);
	        properties.load(fileInputStream);
	        fileInputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@BeforeSuite
	@Parameters({ "browser","headless","record"})
	public void startBrowser(String browser,  String headless, String record) throws Exception 
	{
		
		if(record.equals("yes")){
			context = Reporter.getCurrentTestResult().getTestContext();
			Recording.startRecording(context.getSuite().getName());
		}
		BaseConfig.browser = browser;
		switch (browser.toLowerCase()) {
	        case "firefox":
	            setupFirefoxDriver(headless);
	            break;
	        case "chrome":
	            setupChromeDriver(headless);
	            break;
	        default:
	            throw new IllegalArgumentException("Browser not supported: " + browser);
		}
		configureDriver(getBaseUrl());
		setupExtentReport();
		sa = new SoftAssert();
	}
	
	private static void setupChromeDriver(String headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless.equals("true")) {
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);
    }
	
	private static void setupFirefoxDriver(String headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless.equals("true")) {
            options.addArguments("--headless");
        }
        // Set preferences for location access
        options.addPreference("geo.enabled", true); // Enable geolocation
        options.addPreference("geo.provider.use_corelocation", true); // For MacOS
        options.addPreference("geo.prompt.testing", true); // Bypass the popup
        options.addPreference("geo.prompt.testing.allow", true);
        driver = new FirefoxDriver(options);
    }
	
	private static void configureDriver(String url) {
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        actions = new Actions(driver);
        //driver.get(url);
        
        wait = new WebDriverWait(driver, TimeUnit.SECONDS.convert(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS));
    }
	
	private static void setupExtentReport() {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("index.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Extent Report Name");
		extent.attachReporter(spark);
	}
	
	protected static void setupDatabaseConnection(String dbUrl, String dbUser, String dbPassword) {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            DB.executeAllQueries();
        } catch (SQLException e) {
            System.out.println("Koneksi ke database gagal!");
            e.printStackTrace();
        }
    }
	
	public static void goToUrl(String url) {
		currentUrl = getDriver().getCurrentUrl();
        targetUrl = getBaseUrl() + url;
        
        if (!currentUrl.equals(targetUrl)) {
            getDriver().get(targetUrl);
        }
	}
	
	@AfterSuite
	public void quitDriver() throws Exception {
		driver.quit();
	}
	
	public static WebDriverWait getWait() {
		return wait;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static Actions getActions() {
		return actions;
	}
	
	public static SoftAssert getSoftAssert() {
        return sa;
    }
	
	public static String getBrowser() {
		return browser;
	}
	
	public void refreshPage(){
		getDriver().navigate().refresh();
	}
	
	public static String goTo(String url) {
		return url;
	}
	
	public static File getscreenshot() throws IOException 
	{
		File des = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		return des;
	}
	
	public static Connection getConnection() {
	    return connection;
	}
	
	public static String getBaseUrl() {
		return properties.getProperty("baseUrl", "");
	}
	
	public String getDbUrl() {
        return properties.getProperty("dbUrl", "");
    }
	public String getDbUser() {
		return properties.getProperty("dbUser", "");
	}
	public String getDbPassword() {
		return properties.getProperty("dbPassword", "");
	}
	
	@AfterSuite
	@Parameters({"record","email"})
	public void stopRecording(String record, String email) throws Exception {
		if(record.equals("yes")) {
			Recording.stopRecording();
		}
		
		if(email.equals("yes")) {
    		try {
    			EmailSender.SentEmail();
    		} catch (MessagingException | IOException e) {
    			e.printStackTrace();
    		}
    	}
	}
}