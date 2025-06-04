package mobilePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import bases.BaseConfig;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilization.MUse;

public class RequestExpensesPage extends BaseConfig{
	public RequestExpensesPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getMDriver()), this);
    }
	
	@FindBy (xpath="//android.widget.ImageView[contains(@content-desc,'Expenses')]")
	WebElement expenseMenuButton;
	
	@FindBy (xpath="//android.view.View[@content-desc='Add Expenses']")
	WebElement addExpensesButton;
	
	@FindBy (xpath="//android.view.View[@content-desc='Date']/android.view.View")
	WebElement dateInput;
	
	@FindBy (xpath="//android.view.View[@content-desc='Apply']")
	WebElement dateApplyButton;
	
	@FindBy (xpath="//android.view.View[@content-desc='Select Task']")
	WebElement taskSelectButton;
	
	WebElement taskSelect;
	
	@FindBy (xpath="//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.EditText[1]")
	WebElement costInput;
	
	@FindBy (xpath="//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.EditText[2]")
	WebElement detailButton;
	
	@FindBy (xpath="//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.EditText[2]")
	WebElement detailInput;
	
	@FindBy (xpath="//android.view.View[contains(@content-desc,'Select File')]")
	WebElement selectFile;
	
	@FindBy (xpath="//android.view.View[@content-desc='Open Camera']")
	WebElement cameraButton;
	
	@FindBy (xpath="//android.widget.ImageView")
	WebElement takeAPicButton;
	
	@FindBy (xpath="//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]")
	WebElement confirmTakePicButton;
	
	@FindBy (xpath="//android.view.View[@content-desc='From Gallery']")
	WebElement galleryButton;
	
	@FindBy (xpath="//android.widget.ImageView[@resource-id='com.google.android.documentsui:id/icon_thumb']")
	WebElement selectImageFromFolder;
	
	@FindBy (xpath="//android.view.View[@content-desc='From Files']")
	WebElement fileButton;
	
	@FindBy (xpath="//android.widget.Button[@content-desc='Submit']")
	WebElement submitButton;
	
	WebElement successAlert;
	
	public void requestExpenses(String task, String cost, String detail, String attachmentType, String attachment, String expectedResult) throws InterruptedException {
		Thread.sleep(3000);
		MUse.click(expenseMenuButton);
		MUse.click(addExpensesButton);
		MUse.tap(dateInput);
		MUse.click(dateApplyButton);
		MUse.click(taskSelectButton);
		taskSelect = getWait().until(ExpectedConditions.elementToBeClickable(getMDriver().findElement(By.xpath("//android.view.View[@content-desc='"+task+"']"))));
		MUse.click(taskSelect);
		MUse.sendKeys(costInput, cost);
		MUse.click(detailButton);
		MUse.sendKeys2(detailInput, detail);
		MUse.click(selectFile);
		if(attachmentType.equals("camera")) {
			MUse.click(cameraButton);
			MUse.click(takeAPicButton);
			MUse.click(confirmTakePicButton);
		}else if(attachmentType.equals("galery")) {
			MUse.click(addExpensesButton);
			MUse.click(selectImageFromFolder);
		}else {
			MUse.click(fileButton);
			MUse.click(selectImageFromFolder);
		}
		MUse.click(submitButton);
		MUse.click(submitButton);//confirm submit
		
		Thread.sleep(8000);
	}
}