package mobilePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bases.BaseConfig;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilization.MUse;

public class AddCallSchedullerPage extends BaseConfig{
	public AddCallSchedullerPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getMDriver()), this);
    }
	
	@FindBy (xpath="//android.widget.ImageView[contains(@content-desc,'Schedule')]")
	WebElement scheduleMenuButton;
	
	@FindBy (xpath="//android.view.View[@content-desc='Add Task']")
	WebElement addTaskButton;
	
	@FindBy (xpath="//android.view.View[@content-desc='Start Time']")
	WebElement startTimeButton;
	
	@FindBy (xpath="//android.view.View[@content-desc='OK']")
	WebElement okTimeButton;
	
	@FindBy (xpath="//android.view.View[@content-desc='End Time']")
	WebElement endTimeButton;
	
	@FindBy (xpath="//android.view.View[@content-desc='Select Customer']")
	WebElement customerSelectButton;
	WebElement customerSelect;
	
	@FindBy (xpath="//android.view.View[@content-desc='Select Outlet']")
	WebElement outletSelectButton;
	
	WebElement outletSelect;
	
	@FindBy (xpath="//android.view.View[@content-desc='Select Task Category']")
	WebElement taskSelectButton;
	
	WebElement taskSelect;
	
	@FindBy (xpath="//android.widget.EditText")
	WebElement descriptionInput;
	
	@FindBy (xpath="//android.widget.Button[@content-desc='Submit']")
	WebElement submitButton;
	
	public void addCallScheduller(String startTime, String endTime, String customer, String outlet, String task, String description) throws InterruptedException {
        MUse.click(scheduleMenuButton);
        MUse.click(addTaskButton);
        MUse.click(startTimeButton);
        MUse.click(okTimeButton);
        MUse.click(endTimeButton);
        MUse.swipeUp(717, 685, 717, 481);
        MUse.click(okTimeButton);
        MUse.click(customerSelectButton);Thread.sleep(1000);
        customerSelect = getMDriver().findElement(By.xpath("//android.view.View[@content-desc='"+customer+"']"));
        MUse.click(customerSelect);
        
        MUse.click(outletSelectButton);Thread.sleep(1000);
        outletSelect = getMDriver().findElement(By.xpath("//android.view.View[@content-desc='"+outlet+"']"));
        MUse.click(outletSelect);
        
        MUse.click(taskSelectButton);Thread.sleep(1000);
        taskSelect = getMDriver().findElement(By.xpath("//android.view.View[@content-desc='"+task+"']"));
        MUse.click(taskSelect);
        
        MUse.sendKeys(descriptionInput, description);
        MUse.click(submitButton);
        MUse.click(submitButton);
        Thread.sleep(15000);
	}
}