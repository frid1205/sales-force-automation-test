package mobileTestCases;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import bases.BaseConfig;
import mobilePages.ClockInOutPage;
import utilization.Utilize;

public class ClockInOut extends BaseConfig{
	ClockInOutPage ClockInOutPage;
	String FilePath;
	
	public ClockInOut() {
		super();
	}
	
	@BeforeTest
	public void setUpBeforeTest() {
		ClockInOutPage = new ClockInOutPage();
		try {
			if (!platform.equalsIgnoreCase("ios")) {
				getMDriver().executeScript("mobile: shell", ImmutableMap.of(
			            "command", "am start -n com.lumoshive.livre.hris/com.lumoshive.livre.hris.MainActivity"
			    ));
			}
		} catch (Exception e) {
			try {
				if (getMDriver() instanceof io.appium.java_client.InteractsWithApps) {
					((io.appium.java_client.InteractsWithApps) getMDriver()).activateApp("com.lumoshive.livre.hris");
				}
			} catch (Exception ignored) {}
		}
	}
	
	@DataProvider(name = "clockIn")
	public Object[][] getDataClockIn() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\mobile\\ClockInOut.xlsx","ClockIn");
	    return data;
	}
	
	@DataProvider(name = "clockOut")
	public Object[][] getDataClockOut() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\mobile\\ClockInOut.xlsx","ClockOut");
		return data;
	}
	
	@Test(dataProvider="clockIn")
	public void testClockIn(String longtitude, String latitude) throws InterruptedException, IOException
	{
		ClockInOutPage.clockInOut(longtitude, latitude);
	}
	
	@Test(dataProvider="clockOut")
	public void testClockOut(String longtitude, String latitude) throws InterruptedException, IOException
	{
		ClockInOutPage.clockInOut(longtitude, latitude);
	}
}