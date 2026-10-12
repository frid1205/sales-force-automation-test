package mobilePages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import bases.BaseConfig;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilization.MUse;

public class LoginMobilePage extends BaseConfig {
	public LoginMobilePage() {
        PageFactory.initElements(new AppiumFieldDecorator(getMDriver()), this);
    }
	
	// Locator Email untuk Android & iOS
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Email\"]/android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='Email' or @value='Email' or @label='Email']")
	private WebElement emailInput;
    
	// Locator Password untuk Android & iOS
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Password\"]/android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSecureTextField[@name='Password' or @value='Password' or @label='Password']")
	private WebElement passwordInput;
	
	// Locator Tombol Login untuk Android & iOS
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Log in']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Log in' or @label='Log in']")
	private WebElement loginButton;
	
	public void testLogin(String email, String password, String expectedResult) throws InterruptedException {
		MUse.sendKeys(emailInput, email);
		MUse.sendKeys(passwordInput, password);
		MUse.click(loginButton);
		Thread.sleep(18000);
	}
}