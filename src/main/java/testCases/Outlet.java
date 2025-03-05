package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.CustomerAndOutletPage;
import utilization.Utilize;

public class Outlet extends BaseConfig{
	CustomerAndOutletPage OutletPage;
	String FilePath;
	
	public Outlet() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		OutletPage = new CustomerAndOutletPage();
	}
	
	@DataProvider(name = "dataAddOutlet")
	public Object[][] getAddOutlet() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\Outlet.xlsx","addOutlet");
	    return data;
	}
	
	@DataProvider(name = "dataEditOutlet")
	public Object[][] getEditOutlet() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\Outlet.xlsx","editOutlet");
		return data;
	}
	
	@Test(dataProvider="dataAddOutlet")
	public void testAddOutlet(String customerName, String outletName, String province, String region,String address, String location, String PIC, String phoneNumber, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("setting/customer");
        OutletPage.addOutletCustomer(customerName,outletName,province,region,address,location,PIC,phoneNumber,expectedResult);
	}
	
	
	@Test(dataProvider="dataAddOutlet")
	public void testEditOutlet(String customerName, String outletName, String province, String region,String address, String location, String PIC, String phoneNumber, String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("setting/customer");
		OutletPage.editOutletCustomer(customerName,outletName,province,region,address,location,PIC,phoneNumber,expectedResult);
	}
}