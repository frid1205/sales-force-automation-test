package testCases;

import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.LoginAdminPage;
import utilization.Utilize;

public class Login extends BaseConfig{
	LoginAdminPage LoginAdminPage;
	String FilePath;
	
	public Login() {
		super();
	}
	
	@BeforeTest
	public void setUpBeforeTest() {
		LoginAdminPage = new LoginAdminPage();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		getDriver().get(getBaseUrl()+"login");
	}
	
	@DataProvider(name = "dataSuperadmin")
	public Object[][] getDataSuperadmin() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\Login.xlsx","Superadmin");
	    return data;
	}
	
	@DataProvider(name = "dataAdminCompany")
	public Object[][] getDataAdminCompany() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\Login.xlsx","AdminCompany");
		return data;
	}
	
	@DataProvider(name = "dataLoginSales")
	public Object[][] getDataLoginSales() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\Login.xlsx","Sales");
		return data;
	}
	
	@Test(dataProvider="dataSuperadmin")
	public void loginSuperadmin(String email, String password, String expectedCondition) throws InterruptedException, IOException
	{
		goToUrl("login");
		LoginAdminPage.testLogin(email, password, expectedCondition);
	}
	
	@Test(dataProvider="dataAdminCompany")
	public void loginAdminCompany(String email, String password, String expectedCondition) throws InterruptedException, IOException
	{
		goToUrl("login");
		LoginAdminPage.testLogin(email, password, expectedCondition);
	}
	
	@Test(dataProvider="dataLoginSales")
	public void loginSales(String email, String password, String expectedCondition) throws InterruptedException, IOException
	{
		goToUrl("login");
		LoginAdminPage.testLogin(email, password, expectedCondition);
	}
}