package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.admin.CallSchedullerPage;
import utilization.Utilize;

public class CallScheduller extends BaseConfig{
	CallSchedullerPage CallSchedullerPage;
	String FilePath;
	
	public CallScheduller() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		CallSchedullerPage = new CallSchedullerPage();
	}
	
	@DataProvider(name = "dataAddCallScheduller")
	public Object[][] getAddCallScheduller() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\CallScheduller.xlsx","addCallScheduller");
	    return data;
	}
	
	@Test(dataProvider="dataAddCallScheduller")
	public void testAddCallScheduller(String userName, String days, String startTime, String endTime, String customer, String outlet, String taskCategory, String description, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("sales/call-scheduling");
        CallSchedullerPage.addCallScheduller(userName,days,startTime,endTime,customer,outlet,taskCategory,description,expectedResult);
	}
}