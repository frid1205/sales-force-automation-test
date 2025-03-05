package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import bases.BaseConfig;
import utilization.Use;
import utilization.Utilize;

public class UserPage extends BaseConfig{
	public UserPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//list data table company 
	@FindBy(xpath="//input[@type='search']")
    WebElement searchInput;
	
	@FindBy(xpath="//a[contains(@href,'users')]")
	WebElement listUserCompanyButton;
	
	//list data table of user
	@FindBy(xpath="//a[contains(@href,'create')]")
    WebElement addUserButton;
	
	@FindBy(xpath="//a[contains(@title,'Edit User')]")
	WebElement editUserButton;
	
	//form add/edit user
	@FindBy(xpath="//input[@id='file-input']")
    WebElement photoProfileInput;
	
	@FindBy(xpath="//input[@name='name']")
    WebElement nameInput;
	
	@FindBy(xpath="//input[@name='phone_number']")
	WebElement phoneNumberInput;
	
	@FindBy(xpath="//span[contains(@id,'user') and contains(@id,'role')]")
	WebElement roleSelectButton;
	
	@FindBy(xpath="//input[@class='select2-search__field']")
	WebElement roleSelectInput;
	
	WebElement roleSelectLi;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement emailInput;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement passwordInput;
	
	@FindBy(xpath="//input[@id='confirm-password']")
	WebElement confirmPasswordInput;
	
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveButton;
	
	WebElement userAlert;
	
	public void addUserCompany(String companyName, String name, String phoneNumber, String role, String email, String password, String coPassword, String profilePicture, String expectedResult) throws InterruptedException {
		Use.SendKeys(searchInput, companyName);
		Thread.sleep(2000);
		Use.Click(listUserCompanyButton);
		Use.Click(addUserButton);
		Use.SendKeys(nameInput, name);
		Use.SendKeys(phoneNumberInput, phoneNumber);
		if(!role.equals("")) {			
			Use.Click(roleSelectButton);
			//Use.SendKeys(roleSelectInput, role);
			Thread.sleep(1000);
			roleSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+role+"')]")));
			Use.Click(roleSelectLi);
		}
		Use.SendKeys(emailInput, email);
		Use.SendKeys(passwordInput, password);
		Use.SendKeys(confirmPasswordInput, coPassword);
		Use.uploadImage(photoProfileInput, Utilize.getAbsolutePath(profilePicture));
		Use.Click(addButton);
		userAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(userAlert.getText(), expectedResult);
	}
	
	public void editUserCompany(String companyName, String name, String phoneNumber, String role, String email, String password, String coPassword, String profilePicture, String expectedResult) throws InterruptedException {
		Use.SendKeys(searchInput, companyName);
		Thread.sleep(2000);
		Use.Click(listUserCompanyButton);
		Use.SendKeys(searchInput, name);
		Thread.sleep(2000);
		editUserButton = getWait().until(ExpectedConditions.elementToBeClickable((getDriver().findElement(By.xpath("//a[contains(@title, 'Edit User')][ancestor::tr[td[contains(text(), '"+name+"')]]]/i")))));
		Use.Click(editUserButton);
		Use.SendKeys(nameInput, name);
		Use.SendKeys(phoneNumberInput, phoneNumber);
		if(!role.equals("")) {			
			Use.Click(roleSelectButton);
			Use.SendKeys(roleSelectInput, role);
			roleSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+role+"')]")));
			Use.Click(roleSelectLi);
		}
		Use.SendKeys(emailInput, email);
		Use.SendKeys(passwordInput, password);
		Use.SendKeys(confirmPasswordInput, coPassword);
		Use.uploadImage(photoProfileInput, Utilize.getAbsolutePath(profilePicture));
		Use.Click(saveButton);
		userAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(userAlert.getText(), expectedResult);
	}
	
	public void addUserAdmin(String name, String phoneNumber, String role, String email, String password, String coPassword, String profilePicture, String expectedResult) throws InterruptedException {
		Use.Click(addUserButton);
		Use.SendKeys(nameInput, name);
		Use.SendKeys(phoneNumberInput, phoneNumber);
		if(!role.equals("")) {			
			Use.Click(roleSelectButton);
			Thread.sleep(1000);
			roleSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+role+"')]")));
			Use.Click(roleSelectLi);
		}
		Use.SendKeys(emailInput, email);
		Use.SendKeys(passwordInput, password);
		Use.SendKeys(confirmPasswordInput, coPassword);
		Use.uploadImage(photoProfileInput, Utilize.getAbsolutePath(profilePicture));
		Use.Click(addButton);
		userAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(userAlert.getText(), expectedResult);
	}
	
	public void editUserAdmin(String name, String phoneNumber, String role, String email, String password, String coPassword, String profilePicture, String expectedResult) throws InterruptedException {
		Use.SendKeys(searchInput, name);
		Thread.sleep(2000);
		editUserButton = getWait().until(ExpectedConditions.elementToBeClickable((getDriver().findElement(By.xpath("//a[contains(@title, 'Edit User')][ancestor::tr[td[contains(text(), '"+name+"')]]]/i")))));
		Use.Click(editUserButton);
		Use.SendKeys(nameInput, name);
		Use.SendKeys(phoneNumberInput, phoneNumber);
		if(!role.equals("")) {			
			Use.Click(roleSelectButton);
			Use.SendKeys(roleSelectInput, role);
			roleSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+role+"')]")));
			Use.Click(roleSelectLi);
		}
		Use.SendKeys(emailInput, email);
		Use.SendKeys(passwordInput, password);
		Use.SendKeys(confirmPasswordInput, coPassword);
		Use.uploadImage(photoProfileInput, Utilize.getAbsolutePath(profilePicture));
		Use.Click(saveButton);
		userAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(userAlert.getText(), expectedResult);
	}
}