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
	@FindBy (xpath="//android.view.View[@content-desc=\"Email\"]/android.widget.EditText")
	RemoteWebElement emailInput;
    
	@FindBy(xpath="//android.view.View[@content-desc=\"Password\"]/android.widget.EditText")
	RemoteWebElement passwordInput;
	
	@FindBy(how = How.XPATH, using = "//android.widget.Button[@content-desc='Log in']")
	WebElement loginButton;
	
	
	String longtitude;String latitude;
	
	public void testLogin(String email, String password, String expectedResult) throws InterruptedException {
		MUse.sendKeys(emailInput, email);
		MUse.sendKeys(passwordInput, password);
		MUse.click(loginButton);
		Thread.sleep(18000);
	}
}