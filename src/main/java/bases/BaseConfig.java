package bases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.Location;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilization.DB;
import utilization.Utilize;

@Listeners(bases.TestListener.class)
public class BaseConfig {
	private static WebDriver driver;
	private static AndroidDriver mdriver;
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
    protected static Location location;
    public static String isCloud = "false";
    static HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
    
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
	@Parameters({ "browser","headless","record","env"})
	public void startBrowser(String browser,  String headless, String record, String env) throws Exception 
	{
		
		if(record.equals("yes")){
			context = Reporter.getCurrentTestResult().getTestContext();
			Recording.startRecording(context.getSuite().getName());
		}
		if(env.equals("mobile")) {
			configureMobileDriver();
		}else {
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
		}
		setupExtentReport();
		sa = new SoftAssert();
	}
	
	private static void setupChromeDriver(String headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        if (headless.equals("true")) {
            options.addArguments("--headless");
			options.addArguments("--no-sandbox");
        	options.addArguments("--disable-dev-shm-usage");
        	options.addArguments("--disable-gpu");
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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
        driver.manage().window().maximize();
        actions = new Actions(driver);
        
        wait = new WebDriverWait(driver, Duration.ofMillis(DEFAULT_TIMEOUT));
    }
	
	private static HashMap<String, Object> getBrowserstackOptions(){
	    HashMap<String, Object> browserstackOptions = new HashMap<>();
	    browserstackOptions.put("userName","lumoshiveindones_TDMlAk");
	    browserstackOptions.put("accessKey","sUX73p6v6jzT3jC1X8Lp");
	    browserstackOptions.put("deviceName", "Google Pixel 6");
	    browserstackOptions.put("osVersion", "12.0");
	    browserstackOptions.put("projectName", "STP Project");
	    browserstackOptions.put("buildName", "Build 1.0");
	    browserstackOptions.put("sessionName", "STP Session");
	    browserstackOptions.put("appiumVersion", "2.4.1");
	    return browserstackOptions;
	}
	
	private static void configureMobileDriver() throws MalformedURLException, InterruptedException  {
		browserstackOptions = getBrowserstackOptions();
		UiAutomator2Options options = new UiAutomator2Options();
		if(isCloud.equals("true")) {
			options.setPlatformName("Android")
		       .setApp("bs://1ac4a8d59b8f434d8a7a6640f7ed90b79c7de607")
		       .setAppPackage("com.lumoshive.salestrackpro")
		       .setAppActivity("com.lumoshive.salestrackpro.MainActivity")
		       .setAutomationName("UiAutomator2");
			options.setCapability("bstack:options", getBrowserstackOptions());
			
		}else {
			options.setPlatformName("Android") 
				.setDeviceName("emulator-5554") 
				.setUdid("emulator-5554") 
				.setAppPackage("com.lumoshive.salestrackpro")
				.setAppActivity("com.lumoshive.salestrackpro.MainActivity") 
				.setIgnoreHiddenApiPolicyError(true)
				.setApp(Utilize.getAbsolutePath("\\testdata\\STP.apk")) // Path ke file APK
				.setAutomationName("UiAutomator2")
				.setNoReset(false) 
				.setFullReset(false); 
		}

		try {
			// Set capability before driver is created
			options.setCapability("uiautomator2ServerLaunchTimeout", 120000);
			options.setCapability("uiautomator2ServerInstallTimeout", 120000);
			options.setCapability("adbExecTimeout", 120000); // 120 seconds

			// Inisialisasi AndroidDriver dengan UiAutomator2Options
			if(isCloud.equals("true")) {
			    String username = "lumoshiveindones_TDMlAk";
			    String accessKey = "sUX73p6v6jzT3jC1X8Lp";
			    String cloudUrl = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
			    mdriver = new AndroidDriver(new URL(cloudUrl), options);
			} else {
				mdriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
			}
			wait = new WebDriverWait(driver, Duration.ofMillis(DEFAULT_TIMEOUT));
			
			if(isCloud.equals("true")) {
				options.setCapability("autoGrantPermissions", true);
				
				options.setCapability("appPermissions", Arrays.asList(
					    "android.permission.ACCESS_FINE_LOCATION",
					    "android.permission.ACCESS_COARSE_LOCATION",
					    "android.permission.ACCESS_BACKGROUND_LOCATION",
					    "android.permission.CAMERA",
					    "android.permission.POST_NOTIFICATIONS"
				));
				
			}else {
				String packageName = "com.lumoshive.salestrackpro";
				//clear cache and storage
				mdriver.executeScript("mobile: shell", Map.of("command", "pm clear " + packageName));
				// Berikan izin lokasi secara otomatis
				mdriver.executeScript("mobile: shell", ImmutableMap.of("command", "pm grant " + packageName + " android.permission.ACCESS_FINE_LOCATION"));
				mdriver.executeScript("mobile: shell", ImmutableMap.of("command", "pm grant " + packageName + " android.permission.ACCESS_COARSE_LOCATION"));
				mdriver.executeScript("mobile: shell", ImmutableMap.of("command", "pm grant " + packageName + " android.permission.ACCESS_BACKGROUND_LOCATION"));
				// Grant camera permission
				mdriver.executeScript("mobile: shell", ImmutableMap.of("command", "pm grant " + packageName + " android.permission.CAMERA"));
				// Grant notification permission
				mdriver.executeScript("mobile: shell", ImmutableMap.of("command", "pm grant " + packageName + " android.permission.POST_NOTIFICATIONS"));
			}
	
		 } catch (MalformedURLException e) {
	        e.printStackTrace();
		 }
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
	@Parameters({"env"})
	public void quitDriver(String env) throws Exception {
		if(env.equals("mobile")) {
			mdriver.quit();
		}else {
			driver.quit();
		}
	}
	
	public static WebDriverWait getWait() {
		return wait;
	}
	
	public static AndroidDriver getMDriver() {
		return mdriver;
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
	@Parameters({"record"})
	public void stopRecording(String record) throws Exception {
		if(record.equals("yes")) {
			Recording.stopRecording();
		}
	}
}