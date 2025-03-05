package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.LeadLabelPage;
import utilization.Utilize;

public class LeadLabel extends BaseConfig{
	LeadLabelPage LeadLabelPage;
	String FilePath;
	
	public LeadLabel() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		LeadLabelPage = new LeadLabelPage();
	}
	
	@DataProvider(name = "dataAddLeadLabel")
	public Object[][] getAddClass() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\LeadLabel.xlsx","addLeadLabel");
	    return data;
	}
	
	@DataProvider(name = "dataEditLeadLabel")
	public Object[][] getEditClass() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\LeadLabel.xlsx","editLeadLabel");
		return data;
	}
	
	@Test(dataProvider="dataAddLeadLabel")
	public void testAddLeadLabel(String labelName, String labelColor, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("setting/label-leads-management");
        LeadLabelPage.addLeadLabel(labelName, labelColor, expectedResult);
	}
	
	@Test(dataProvider="dataEditLeadLabel")
	public void testEditLeadLabel(String LeadLabelName, String labelColor, String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("setting/label-leads-management");
		LeadLabelPage.editLeadLabel(LeadLabelName,labelColor, expectedResult);
	}
}