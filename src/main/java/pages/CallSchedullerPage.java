package pages;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import bases.BaseConfig;
import utilization.Use;

public class CallSchedullerPage extends BaseConfig{
	public CallSchedullerPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//list user of call scheduller
	@FindBy(xpath="//input[@type='search']")
    WebElement searchInput;
	
    WebElement detailCallSchedullingButton;
    
    WebElement addTaskButton;
    
    @FindBy(xpath="//input[@id='start-time-new-task']")
    WebElement startTimeInput;
    
    @FindBy(xpath="//input[@id='end-time-new-task']")
    WebElement endTimeInput;
    
    @FindBy(xpath="//span[@id='select2-task-add-customer-container']")
    WebElement customerSelectButton;
    
    @FindBy(xpath="//input[@class='select2-search__field']")
    WebElement selectInput;
    
    WebElement customerSelectLi;
    
    @FindBy(xpath="//span[@id='select2-task-add-outlet-container']")
    WebElement outletSelectButton;
    
    WebElement outletSelectLi;
    
    @FindBy(xpath="//span[@id='select2-task-add-category-container']")
    WebElement taskSelectButton;
    
    WebElement taskSelectLi;
    
    @FindBy(xpath="//textarea[@name='description']")
    WebElement descriptionInput;
	
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveButton;
	
	@FindBy(xpath="//button[text()='OK']")
	WebElement OKButton;
	
	WebElement callSchedullerAlert;
	
	//add call scheduller from lead management
	WebElement addCallSchedullerFromLeadButton;
	
	 @FindBy(xpath="//textarea[@name='description'][ancestor::form[@id='formCreateSchedule']]")
	 WebElement descriptionAddSchedullerLeadInput;
	 
	 @FindBy(xpath="//button[contains(text(),'Add')][ancestor::div[@id='formModalSchedule']]")
	 WebElement addButton2;

	@FindBy(xpath="//button[text()='OK']")
	WebElement dateSelectButton;
	
	
	public void addCallScheduller(String userName, String day, String startTime, String endTime, String customer, String outlet, String taskCategory, String description, String expectedResult) throws InterruptedException {
		Use.SendKeys(searchInput, userName);
		Thread.sleep(2000);
		detailCallSchedullingButton = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'sales/call-scheduling')]/i[ancestor::tr/td[contains(text(),'"+userName+"')]]")));
		Use.Click(detailCallSchedullingButton);
		
		addTaskButton = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@onclick,'"+date(day)+"')]")));
		Use.Click(addTaskButton);
		if(day.equals("0")) {
			Use.SendKeys(startTimeInput, hours(startTime));
			Use.SendKeys(endTimeInput, hours(endTime));
		}else{
			Use.SendKeys(startTimeInput, startTime);
			Use.SendKeys(endTimeInput, endTime);
		}
		if(!customer.equals("")) {
			Use.Click(customerSelectButton);
			Thread.sleep(1000);
			customerSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+customer+"')]")));
			Use.Click(customerSelectLi);
		}
		if(!outlet.equals("")) {
			Use.Click(outletSelectButton);
			Thread.sleep(1000);
			outletSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+outlet+"')]")));
			Use.Click(outletSelectLi);
		}
		if(!taskCategory.equals("")) {			
			Use.Click(taskSelectButton);
			Thread.sleep(1000);
			taskSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+taskCategory+"')]")));
			Use.Click(taskSelectLi);
		}
		Use.SendKeys(descriptionInput, description);
		Use.Click(addButton);

		callSchedullerAlert = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+expectedResult+"')]")));
		Assert.assertEquals(callSchedullerAlert.getText(), expectedResult);
	}
	
	public void addCallSchedullerFromLead(String assignTo, String startTime, String endTime, String customer, String outlet, String taskCategory, String description, String expectedResult) throws InterruptedException {
		addCallSchedullerFromLeadButton = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add task schedule'][ancestor::div/div/div/h2[contains(text(),'"+assignTo+"')]]")));
		Use.Click(addCallSchedullerFromLeadButton);
		System.out.println("Masuk 1");
		Use.SendKeys(startTimeInput, hours(startTime));
		Use.SendKeys(endTimeInput, hours(endTime));
		if(!customer.equals("")) {
			Use.Click(customerSelectButton);
			Thread.sleep(1000);
			customerSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+customer+"')]")));
			Use.Click(customerSelectLi);
		}
		if(!outlet.equals("")) {
			Use.Click(outletSelectButton);
			Thread.sleep(1000);
			outletSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+outlet+"')]")));
			Use.Click(outletSelectLi);
		}
		if(!taskCategory.equals("")) {			
			Use.Click(taskSelectButton);
			Thread.sleep(1000);
			taskSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+taskCategory+"')]")));
			Use.Click(taskSelectLi);
		}
		Use.SendKeys(descriptionAddSchedullerLeadInput, description);
		Use.Click(addButton2);
		Thread.sleep(1000);
		sa.assertAll();
		callSchedullerAlert = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+expectedResult+"')]")));
		Assert.assertEquals(callSchedullerAlert.getText(), expectedResult);
		
	}
	
	public static String date(String i) {
		LocalDate todayDate = LocalDate.now().plusDays(Integer.parseInt(i));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);

        String formatedDate = todayDate.format(formatter);
        return formatedDate;
	}
	
	public static String hours(String i) {
		LocalTime currentTime = LocalTime.now().plusHours(Integer.parseInt(i));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        DateTimeFormatter AMPM = DateTimeFormatter.ofPattern("a");

        String formatedTime = currentTime.format(formatter);
        String getAMPM = currentTime.format(AMPM);
        String hours = formatedTime+""+getAMPM;
        return hours;
	}
}