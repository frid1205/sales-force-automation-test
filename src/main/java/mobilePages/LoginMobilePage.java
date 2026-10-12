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
	@iOSXCUITFindBy(xpath = "//*[@name='Email' or @label='Email']//XCUIElementTypeTextField | (//XCUIElementTypeTextField)[1]")
	private WebElement emailInput;
    
	// Locator Password untuk Android & iOS
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Password\"]/android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//*[@name='Password' or @label='Password']//XCUIElementTypeTextField | //*[@name='Password' or @label='Password']//XCUIElementTypeSecureTextField | //XCUIElementTypeSecureTextField | (//XCUIElementTypeTextField)[2]")
	private WebElement passwordInput;
	
	// Locator Tombol Login untuk Android & iOS
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Log in']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Log in' or @label='Log in'] | //*[@name='Log in' or @label='Log in' or contains(@name, 'Log in') or contains(@label, 'Log in')]")
	private WebElement loginButton;
	
	public void testLogin(String email, String password, String expectedResult) throws InterruptedException {
		Thread.sleep(3000);
		MUse.sendKeys(emailInput, email);
		MUse.hideKeyboard();
		MUse.sendKeys(passwordInput, password);
		MUse.hideKeyboard();
		MUse.click(loginButton);
		Thread.sleep(18000);
	}
}