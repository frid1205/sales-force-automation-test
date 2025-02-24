package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import bases.BaseConfig;
import pages.admin.RoleManagementPage;
import utilization.Utilize;

public class RoleManagement extends BaseConfig{
	RoleManagementPage RoleManagementPage;
	String FilePath;
	
	public RoleManagement() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		RoleManagementPage = new RoleManagementPage();
	}
	
	@DataProvider(name = "dataAddRole")
	public Object[][] getAddClass() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\RoleManagement.xlsx","addRoleManagement");
	    return data;
	}
	
	@DataProvider(name = "dataEditRole")
	public Object[][] getEditClass() {
		Object data[][] = Utilize.getDataFromExcel("\\testdata\\RoleManagement.xlsx","editRoleManagement");
		return data;
	}
	
	@Test(dataProvider="dataAddRole")
	public void testAddRoleManagement(String roleName, String roleDescription, String canAccessCMS, String accessControl, String expectedResult) throws InterruptedException, IOException 
	{
        goToUrl("setting/role-management");
        RoleManagementPage.addRoleManagement(roleName, roleDescription, canAccessCMS, accessControl, expectedResult);
	}
	
	@Test(dataProvider="dataEditRole")
	public void testEditRoleManagement(String roleName, String roleDescription, String canAccessCMS, String accessControl, String expectedResult) throws InterruptedException, IOException 
	{
		goToUrl("setting/role-management");
		RoleManagementPage.editRoleManagement(roleName, roleDescription, canAccessCMS, accessControl, expectedResult);
	}
}