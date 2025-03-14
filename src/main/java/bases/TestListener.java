package bases;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilization.Utilize;

public class TestListener extends BaseConfig implements ISuiteListener, ITestListener {
	String base64Image, errorMessage,cleanErrorMessage;
	String[] splitMessage;
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
    	// Ubah nama report sesuai keinginan
        String reportName = context.getSuite().getName(); // Ganti sesuai kebutuhan
        String reportPath = "automationtestreport/" + reportName;

        // Inisialisasi ExtentSparkReporter dengan nama yang diubah
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath+ ".html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Buat test berdasarkan nama TestNG suite/class
        test = extent.createTest(context.getName());
        System.out.println("============ " + context.getName() + " ============");
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

    	
        try {
        	errorMessage = result.getThrowable().getMessage();
        	splitMessage = errorMessage.split("For documentation on this error");
            cleanErrorMessage = splitMessage[0].trim();
        }catch(Exception e) {
        	System.out.println(e);
        }
        
        try {
        	if(context.getSuite().getName().contains("Mobile")) {
        		base64Image = ((TakesScreenshot) getMDriver()).getScreenshotAs(OutputType.BASE64);
        	}else {
        		base64Image = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        	}
            
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