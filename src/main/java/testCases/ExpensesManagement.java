package testCases;

import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.ExpensesManagementPage;
import utilization.Utilize;

public class ExpensesManagement extends BaseConfig{
	ExpensesManagementPage ExpensesManagementPage;
	String FilePath;
	
	public ExpensesManagement() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		ExpensesManagementPage = new ExpensesManagementPage();
	}
	
	@DataProvider(name = "dataApproveOrReject")
	public Object[][] getApproveOrReject() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\ExpensesManagement.xlsx","approveReject");
	    return data;
	}
	
	@Test(dataProvider="dataApproveOrReject")
	public void testExpenseManagement(String status, String requester, String outlet, String approveOrReject, String reasonReject, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("sales/expenses-management");
        ExpensesManagementPage.approveOrReject(status, requester, outlet, approveOrReject, reasonReject, expectedResult);
	}
}