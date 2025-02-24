package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.admin.UserPage;
import utilization.Utilize;

public class UserCompany extends BaseConfig{
	UserPage UserPage;
	String FilePath;
	
	public UserCompany() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		UserPage = new UserPage();
	}
	
	@DataProvider(name = "dataAddUserCompany")
	public Object[][] getAddClass() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\UserCompany.xlsx","addUserCompany");
	    return data;
	}
	
	@DataProvider(name = "dataEditUserCompany")
	public Object[][] getEditClass() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\UserCompany.xlsx","editUserCompany");
		return data;
	}

	@Test(dataProvider="dataAddUserCompany")
	public void testAddUserCompany(String companyName, String name, String phoneNumber, String role, String email, String password, String coPassword, String profilePicture, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("company");
        UserPage.addUserCompany(companyName, name, phoneNumber, role, email, password, coPassword, profilePicture, expectedResult);
	}
	
	@Test(dataProvider="dataEditUserCompany")
	public void testEditUserCompany(String companyName, String name, String phoneNumber, String role, String email, String password, String coPassword, String profilePicture, String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("company");
		UserPage.editUserCompany(companyName, name, phoneNumber, role, email, password, coPassword, profilePicture, expectedResult);
	}
}