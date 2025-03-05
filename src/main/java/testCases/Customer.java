package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.CustomerAndOutletPage;
import utilization.Utilize;

public class Customer extends BaseConfig{
	CustomerAndOutletPage CustomerPage;
	String FilePath;
	
	public Customer() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		CustomerPage = new CustomerAndOutletPage();
	}
	
	@DataProvider(name = "dataAddCustomer")
	public Object[][] getAddCustomer() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\Customer.xlsx","addCustomer");
	    return data;
	}
	
	@DataProvider(name = "dataEditCustomer")
	public Object[][] getEditCustomer() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\Customer.xlsx","editCustomer");
		return data;
	}
	
	@Test(dataProvider="dataAddCustomer")
	public void testAddCustomer(String customerName, String province, String region, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("setting/customer");
        CustomerPage.addCustomer(customerName, province, region, expectedResult);
	}
	
	@Test(dataProvider="dataEditCustomer")
	public void testEditCustomer(String customerName, String province, String region, String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("setting/customer");
		CustomerPage.editCustomer(customerName, province, region, expectedResult);
	}
}