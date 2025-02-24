package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import bases.BaseConfig;
import utilization.Use;

public class LoginAdminPage extends BaseConfig{
	public LoginAdminPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath="//a[contains(@href,'/login')]")
	WebElement loginPageButton;
	
	@FindBy(xpath="//input[@name='email']")
    WebElement emailInput;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement passwordInput;
	
	@FindBy(xpath="//button[contains(text(),'SIGN IN')]")
	WebElement signInButton;
	
	@FindBy(xpath="//button[@title='Click here to show/hide password']")
	WebElement hideShowPassButton;
	
	@FindBy(xpath="//a[contains(@href,'/forgot-password')]")
	WebElement forgotPassButton;
	
	WebElement loginAlert;
	
	public void testLogin(String email, String password, String expectedCondition) throws InterruptedException{
		Use.SendKeys(emailInput, email);
		Use.SendKeys(passwordInput, password);
		Use.Click(signInButton);
		loginAlert = getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedCondition+"')]"));
		Use.waitElement(loginAlert);
		Assert.assertEquals(loginAlert.getText(), expectedCondition);
	}
}