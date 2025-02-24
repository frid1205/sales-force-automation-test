package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import bases.BaseConfig;
import utilization.Use;

public class RoleManagementPage extends BaseConfig{
	public RoleManagementPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//list data table
	@FindBy(xpath="//button[contains(@data-url,'role-management/create')]")
    WebElement addRoleButton;
	
	@FindBy(xpath="//input[@type='search']")
    WebElement searchInput;
	
	@FindBy(xpath="(//a[contains(@data-url,'role-management/edit')]/i)[1]")
    WebElement editRoleButton;
	
	@FindBy(xpath="//input[@id='name']")
	WebElement roleNameInput;
	
	@FindBy(xpath="//input[@id='description']")
	WebElement roleDescriptionInput;
	
	@FindBy(xpath="//input[@id='can_access_cms']")
	WebElement canAccessCMSCheckbox;
	
	WebElement viewAccessControlCheckbox;
	WebElement createAccessControlCheckbox;
	WebElement updateAccessControlCheckbox;
	WebElement deleteAccessControlCheckbox;
	
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveButton;
	
	WebElement RoleManagementAlert;
	
	public void addRoleManagement(String roleName, String roleDescription, String canAccessCMS, String accessControl, String expectedResult) throws InterruptedException {
		Use.Click(addRoleButton);
		Use.SendKeys(roleNameInput, roleName);
		Use.SendKeys(roleDescriptionInput, roleDescription);
		Use.checkBox(canAccessCMSCheckbox, canAccessCMS);
		
		viewAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'view')][ancestor::tr[td[contains(text(), 'Call Scheduling')]]]"))));
		createAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'create')][ancestor::tr[td[contains(text(), 'Call Scheduling')]]]"))));
		updateAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'update')][ancestor::tr[td[contains(text(), 'Call Scheduling')]]]"))));
		deleteAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'delete')][ancestor::tr[td[contains(text(), 'Call Scheduling')]]]"))));
		
		 String[] menus = accessControl.split(";");

        for (String accessControlMenu : menus) {
            String[] values = accessControlMenu.split("-");
            
            String menuName = values[0].trim(); // Menu Name
            String view = values[1].trim(); // View
            String create = values[2].trim(); // Create
            String edit = values[3].trim(); // Edit
            String delete = values[4].trim(); // Delete
            
            viewAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'view')][ancestor::tr[td[contains(text(), '"+menuName+"')]]]"))));
    		createAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'create')][ancestor::tr[td[contains(text(), '"+menuName+"')]]]"))));
    		updateAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'update')][ancestor::tr[td[contains(text(), '"+menuName+"')]]]"))));
    		deleteAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'delete')][ancestor::tr[td[contains(text(), '"+menuName+"')]]]"))));
    		
    		Use.checkBox(viewAccessControlCheckbox, view);
    		Use.checkBox(createAccessControlCheckbox, create);
    		Use.checkBox(updateAccessControlCheckbox, edit);
    		Use.checkBox(deleteAccessControlCheckbox, delete);
        }

		Use.Click(addButton);
		RoleManagementAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(RoleManagementAlert.getText(), expectedResult);
	}
	
	public void editRoleManagement(String roleName, String roleDescription, String canAccessCMS, String accessControl, String expectedResult) throws InterruptedException {
		Use.SendKeys(searchInput, roleName);
		Thread.sleep(2000);
		Use.Click(editRoleButton);
		Use.SendKeys(roleNameInput, roleName);
		Use.SendKeys(roleDescriptionInput, roleDescription);
		Use.checkBox(canAccessCMSCheckbox, canAccessCMS);
		
		viewAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'view')][ancestor::tr[td[contains(text(), 'Call Scheduling')]]]"))));
		createAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'create')][ancestor::tr[td[contains(text(), 'Call Scheduling')]]]"))));
		updateAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'update')][ancestor::tr[td[contains(text(), 'Call Scheduling')]]]"))));
		deleteAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'delete')][ancestor::tr[td[contains(text(), 'Call Scheduling')]]]"))));
		
		String[] menus = accessControl.split(";");
		
		for (String accessControlMenu : menus) {
			String[] values = accessControlMenu.split("-");
			
			String menuName = values[0].trim(); // Menu Name
			String view = values[1].trim(); // View
			String create = values[2].trim(); // Create
			String edit = values[3].trim(); // Edit
			String delete = values[4].trim(); // Delete
			
			viewAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'view')][ancestor::tr[td[contains(text(), '"+menuName+"')]]]"))));
			createAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'create')][ancestor::tr[td[contains(text(), '"+menuName+"')]]]"))));
			updateAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'update')][ancestor::tr[td[contains(text(), '"+menuName+"')]]]"))));
			deleteAccessControlCheckbox =  getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//input[@type='checkbox' and contains(@value,'delete')][ancestor::tr[td[contains(text(), '"+menuName+"')]]]"))));
			
			Use.checkBox(viewAccessControlCheckbox, view);
			Use.checkBox(createAccessControlCheckbox, create);
			Use.checkBox(updateAccessControlCheckbox, edit);
			Use.checkBox(deleteAccessControlCheckbox, delete);
		}
		
		Use.Click(saveButton);
		RoleManagementAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(RoleManagementAlert.getText(), expectedResult);
	}
}
