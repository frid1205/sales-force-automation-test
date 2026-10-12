package mobileTestCases;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import bases.BaseConfig;
import mobilePages.LoginMobilePage;
import utilization.Utilize;

public class Login extends BaseConfig{
	LoginMobilePage LoginMobilePage;
	String FilePath;
	
	public Login() {
		super();
	}
	
	@BeforeTest
	public void setUpBeforeTest() {
		LoginMobilePage = new LoginMobilePage();
		try {
			getMDriver().executeScript("mobile: shell", ImmutableMap.of(
		            "command", "am start -n com.lumoshive.livre.hris/com.lumoshive.livre.hris.MainActivity"
		    ));
		} catch (Exception e) {
			getMDriver().activateApp("com.lumoshive.livre.hris");
		}
	}
	
	@DataProvider(name = "loginSales")
	public Object[][] getDataLogin() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\mobile\\Login.xlsx","Sales");
	    return data;
	}
	
	@Test(dataProvider="loginSales")
	public void loginSales(String email, String password, String expectedCondition) throws InterruptedException, IOException
	{
		LoginMobilePage.testLogin(email, password, expectedCondition);
	}
}