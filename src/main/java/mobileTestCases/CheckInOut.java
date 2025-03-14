package mobileTestCases;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import bases.BaseConfig;
import mobilePages.CheckInOutPage;
import utilization.Utilize;

public class CheckInOut extends BaseConfig{
	CheckInOutPage CheckInOutPage;
	String FilePath;
	
	public CheckInOut() {
		super();
	}
	
	@BeforeTest
	public void setUpBeforeTest() {
		CheckInOutPage = new CheckInOutPage();
		getMDriver().executeScript("mobile: shell", ImmutableMap.of(
	            "command", "am start -n com.lumoshive.salestrackpro/com.lumoshive.salestrackpro.MainActivity"
	    ));
	}
	
	@DataProvider(name = "CheckInOut")
	public Object[][] getDataCheckIn() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\mobile\\CheckInOut.xlsx","checkinout");
	    return data;
	}
	
	@Test(dataProvider="CheckInOut")
	public void testCheckInOut(String scenario, String longtitude, String latitude, String status, String outlet, String customer, String dataProduct, String attachmentCheckout, String reason) throws InterruptedException, IOException
	{
		CheckInOutPage.checkInOut(scenario,longtitude,latitude,status,outlet,customer, dataProduct,attachmentCheckout, reason);
	}
}