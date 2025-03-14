package mobileTestCases;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import bases.BaseConfig;
import mobilePages.RequestExpensesPage;
import utilization.Utilize;

public class RequestExpenses extends BaseConfig{
	RequestExpensesPage RequestExpensesPage;
	String FilePath;
	
	public RequestExpenses() {
		super();
	}
	
	@BeforeTest
	public void setUpBeforeTest() {
		RequestExpensesPage = new RequestExpensesPage();
		getMDriver().executeScript("mobile: shell", ImmutableMap.of(
	            "command", "am start -n com.lumoshive.salestrackpro/com.lumoshive.salestrackpro.MainActivity"
	    ));
	}
	
	@DataProvider(name = "reqExpenses")
	public Object[][] getDataRequestExpenses() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\mobile\\RequestExpenses.xlsx","requestExpenses");
	    return data;
	}
	
	@Test(dataProvider="reqExpenses")
	public void testRequestExpenses(String task, String cost, String detail, String attachmentType, String attachment, String expectedResult) throws InterruptedException, IOException
	{
		RequestExpensesPage.requestExpenses(task, cost, detail, attachmentType, attachment, expectedResult);
	}
}