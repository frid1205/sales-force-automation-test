package bases;

import java.io.File;

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
	static {
		File dir1 = new File(Utilize.getAbsolutePath("//actualResult//screenshot"));
		
		File[] listFiles1 = dir1.listFiles();
		if(dir1.exists()){
			for (File file : listFiles1) {
				file.getName();
				file.delete();
			}
			dir1.delete();
		}
	}

    @Override
    public void onStart(ITestContext context) {
    	test = extent.createTest(context.getName());
    	System.out.println("============ "+context.getName()+" ============");
    }

    @Override
    public void onTestStart(ITestResult result) {
    	//info for extent report
    	Object[] parameters = result.getParameters(); 
    	StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            sb.append(parameters[i].toString());
            if (i < parameters.length - 1) {
                sb.append(", ");
            }
        }
        String combinedParameters = sb.toString();
        test.info( result.getMethod().getMethodName()+" : " + combinedParameters);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass( result.getMethod().getMethodName()+" : Test Passed");
        System.out.println(result.getMethod().getMethodName()+"-Passed-");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	String combinedErrorDetails = "";
    	if (result.getThrowable() != null) {
            StackTraceElement[] stackTrace = result.getThrowable().getStackTrace();
            String[] errorDetails = new String[4];
            for (StackTraceElement element : stackTrace) {
                // Menampilkan file dan baris error
                if (element.getClassName().contains("testCases")) {
                	errorDetails[0] = "Class: " + element.getClassName();
                	errorDetails[1] = "Method: " + element.getMethodName();
                	errorDetails[2] = "File: " + element.getFileName();
                	errorDetails[3] = "Line: " + element.getLineNumber();
                    break;  
                }
            }
            combinedErrorDetails = String.join(", ", errorDetails);
        }
    	
    	
    	// Ambil pesan error utama dari Throwable
        String errorMessage = result.getThrowable().getMessage();

        // Potong pesan pada "For documentation on this error"
        String[] splitMessage = errorMessage.split("For documentation on this error");
        String cleanErrorMessage = splitMessage[0].trim();
        
        try {
            String base64Image = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
            
	        test.fail(result.getMethod().getMethodName() +" : Test Failed, \n Cause : " + cleanErrorMessage + ", " + combinedErrorDetails
	        		,MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(result.getMethod().getMethodName()+"-Failed-");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip(result.getMethod().getMethodName()+" : Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Dijalankan jika metode tes gagal tetapi berada dalam persentase keberhasilan yang ditentukan
        System.out.println("Test Failed But Within Success Percentage: " + result.getMethod().getMethodName());
    }


    @Override
    public void onFinish(ITestContext context) {
    	if (extent != null) {
			extent.flush();
		}
    }
}