package mobileTestCases;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import bases.BaseConfig;
import mobilePages.AddCallSchedullerPage;
import utilization.Utilize;

public class AddCallScheduller extends BaseConfig{
	AddCallSchedullerPage AddCallSchedullerPage;
	String FilePath;
	
	public AddCallScheduller() {
		super();
	}
	
	@BeforeTest
	public void setUpBeforeTest() {
		AddCallSchedullerPage = new AddCallSchedullerPage();
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
	
	@DataProvider(name = "AddCallScheduller")
	public Object[][] getDataAddCallScheduller() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\mobile\\CallScheduller.xlsx","AddCallScheduller");
	    return data;
	}
	
	@Test(dataProvider="AddCallScheduller")
	public void testAddCallScheduller(String startTime, String endTime, String customer, String outlet, String task, String description) throws InterruptedException, IOException
	{
		AddCallSchedullerPage.addCallScheduller(startTime, endTime, customer, outlet, task, description);
	}
}