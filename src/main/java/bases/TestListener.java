package bases;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilization.Utilize;

public class TestListener extends BaseConfig implements ISuiteListener, ITestListener {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath;

    static {
        // Bersihkan screenshot lama
        File dir1 = new File(Utilize.getAbsolutePath("//actualResult//screenshot"));
        if (dir1.exists()) {
            for (File file : dir1.listFiles()) {
                file.delete();
            }
            dir1.delete();
        }
    }

    @Override
    public void onStart(ITestContext context) {
        if (extent == null) { // Pastikan hanya diinisialisasi sekali
            reportPath = "test-output/AutomationTestResult.html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        System.out.println("============ " + context.getName() + " ============");
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());

        // Menampilkan parameter dalam report
        Object[] parameters = result.getParameters();
        if (parameters.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < parameters.length; i++) {
                sb.append(parameters[i].toString());
                if (i < parameters.length - 1) {
                    sb.append(", ");
                }
            }
            test.info("Test Parameters: " + sb.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass(result.getMethod().getMethodName() + " : Test Passed");
        System.out.println(result.getMethod().getMethodName() + "-Passed-");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String errorDetails = "";

        if (result.getThrowable() != null) {
            StackTraceElement[] stackTrace = result.getThrowable().getStackTrace();
            for (StackTraceElement element : stackTrace) {
                if (element.getClassName().contains("testCases")) {
                    errorDetails = "Class: " + element.getClassName() +
                            ", Method: " + element.getMethodName() +
                            ", File: " + element.getFileName() +
                            ", Line: " + element.getLineNumber();
                    break;
                }
            }
        }

        try {
            String base64Image;
            // FIX: get context from result
            String suiteName = result.getTestContext().getSuite().getName();

            if (suiteName != null && suiteName.contains("Mobile")) {
                if (getMDriver() != null) {
                    base64Image = ((TakesScreenshot) getMDriver()).getScreenshotAs(OutputType.BASE64);
                } else {
                    base64Image = null;
                }
            } else {
                if (getDriver() != null) {
                    base64Image = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
                } else {
                    base64Image = null;
                }
            }
        } catch (Exception e) {
            test.fail(result.getMethod().getMethodName() + " : Test Failed\nError: " + result.getThrowable().getMessage() +
                    "\n" + errorDetails);
            e.printStackTrace();
        }

        System.out.println(result.getMethod().getMethodName() + "-Failed-");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip(result.getMethod().getMethodName() + " : Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}
