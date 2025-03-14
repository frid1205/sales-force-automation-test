package testCases;

import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.CallSchedullerPage;
import pages.LeadManagementPage;
import utilization.Utilize;

public class LeadManagement extends BaseConfig{
	LeadManagementPage LeadManagementPage;
	CallSchedullerPage CallSchedullerPage;
	String FilePath;
	
	public LeadManagement() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		LeadManagementPage = new LeadManagementPage();
		CallSchedullerPage = new CallSchedullerPage();
	}
	
	@DataProvider(name = "dataAddLeadManagement")
	public Object[][] getAddLeadManagement() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\LeadManagement.xlsx","addLeadManagement");
	    return data;
	}
	
	@DataProvider(name = "dataEditLeadManagement")
	public Object[][] getEditLeadManagement() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\LeadManagement.xlsx","editLeadManagement");
		return data;
	}
	
	@DataProvider(name = "dataAddCallScheduller")
	public Object[][] getAddCallScheduller() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\LeadManagement.xlsx","addCallScheduller");
		return data;
	}
	
	@Test(dataProvider="dataAddLeadManagement")
	public void testAddLeadManagement(String state, String assignTo, String label, String startDate, String endDate, String customer, String priority,
			String progress, String description,String detail, String revenue, String chanceOfWinning,  String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("sales/leads-management");
        LeadManagementPage.addLeadManagement(state, assignTo, label, startDate, endDate, customer, priority, progress, description,detail, revenue, chanceOfWinning,  expectedResult);
	}
	
	@Test(dataProvider="dataEditLeadManagement")
	public void testEditAndCommentLeadManagement(String state, String assignTo, String label, String startDate, String endDate, String customer, String priority,
			String progress, String description,String detail, String revenue, String chanceOfWinning,String comment,  String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("sales/leads-management");
		LeadManagementPage.editLeadManagement(state, assignTo, label, startDate, endDate, customer, priority, progress, description,detail, revenue, chanceOfWinning, comment, expectedResult);
	}
	
	@Test(dataProvider="dataAddCallScheduller")
	public void testAddCallSchedullerFromLead(String assignTo, String startTime, String endTime, String customer, String outlet, String taskCategory, String description, String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("sales/leads-management");
		CallSchedullerPage.addCallSchedullerFromLead(assignTo,startTime,endTime,customer,outlet,taskCategory,description,expectedResult);
	}
}