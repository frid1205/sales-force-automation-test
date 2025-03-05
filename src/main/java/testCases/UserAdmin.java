package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.UserPage;
import utilization.Utilize;

public class UserAdmin extends BaseConfig{
	UserPage UserPage;
	String FilePath;
	
	public UserAdmin() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		UserPage = new UserPage();
	}
	
	@DataProvider(name = "dataAddUserAdmin")
	public Object[][] getAddClass() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\UserAdmin.xlsx","addUserAdmin");
	    return data;
	}
	
	@DataProvider(name = "dataEditUserAdmin")
	public Object[][] getEditClass() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\UserAdmin.xlsx","editUserAdmin");
		return data;
	}

	@Test(dataProvider="dataAddUserAdmin")
	public void testAddUserAdmin(String name, String phoneNumber, String role, String email, String password, String coPassword, String profilePicture, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("setting/user-management");
        UserPage.addUserAdmin(name, phoneNumber, role, email, password, coPassword, profilePicture, expectedResult);
	}
	
	@Test(dataProvider="dataEditUserAdmin")
	public void testEditUserAdmin(String name, String phoneNumber, String role, String email, String password, String coPassword, String profilePicture, String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("setting/user-management");
		UserPage.editUserAdmin(name, phoneNumber, role, email, password, coPassword, profilePicture, expectedResult);
	}
}