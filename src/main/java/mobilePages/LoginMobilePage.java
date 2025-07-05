package mobilePages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import bases.BaseConfig;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilization.MUse;

public class LoginMobilePage extends BaseConfig{
	public LoginMobilePage() {
        PageFactory.initElements(new AppiumFieldDecorator(getMDriver()), this);
    }
	
	//list user of call scheduller
	@FindBy (xpath="//android.widget.EditText[contains(@hint,'Enter Email Address')]")
	RemoteWebElement emailInput;
    
	@FindBy(xpath="//android.widget.EditText[contains(@hint,'Enter Password')]")
	RemoteWebElement passwordInput;
	
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@content-desc='Login']")
	WebElement loginButton;
	
	@FindBy(how = How.XPATH, using = "//android.view.View[@content-desc='Welcome back']")
	List<WebElement> welcomeText;
	
	String longtitude;String latitude;
	
	public void testLogin(String email, String password, String expectedResult) throws InterruptedException {
		MUse.sendKeys(emailInput, email);
		MUse.hideKeyboard();
		MUse.sendKeys(passwordInput, password);
		MUse.hideKeyboard();
		MUse.swipeUp(462, 1350, 462, 1024);
		MUse.click(loginButton);
		Thread.sleep(25000);
	}
}