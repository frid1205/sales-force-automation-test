package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.admin.CompanyPage;
import utilization.Utilize;

public class Company extends BaseConfig{
	CompanyPage CompanyPage;
	String FilePath;
	
	public Company() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		CompanyPage = new CompanyPage();
	}
	
	@DataProvider(name = "dataAddCompany")
	public Object[][] getAddClass() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\Company.xlsx","addCompany");
	    return data;
	}
	
	@DataProvider(name = "dataEditCompany")
	public Object[][] getEditClass() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\Company.xlsx","editCompany");
		return data;
	}
	
	@Test(dataProvider="dataAddCompany")
	public void testAddCompany(String companyName, String companyContactNumber, String companyAddress, String companyDescription, String companyPICName,
			String companyPICEmail, String companyPICPassword, String mapType, String mapAPIKey, String activationDate, String expirationDate, String companyLogo, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("company");
        CompanyPage.addCompany(companyName, companyContactNumber, companyAddress, companyDescription, companyPICName,companyPICEmail, companyPICPassword, mapType, mapAPIKey, activationDate, expirationDate, companyLogo, expectedResult);
	}
	
	@Test(dataProvider="dataEditCompany")
	public void testEditCompany(String companyName, String companyContactNumber, String companyAddress, String companyDescription, String companyPICName,
			String mapType, String mapAPIKey, String activationDate, String expirationDate, String companyLogo, String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("company");
		CompanyPage.editCompany(companyName, companyContactNumber, companyAddress, companyDescription, companyPICName, mapType, mapAPIKey, activationDate, expirationDate, companyLogo, expectedResult);
	}
}