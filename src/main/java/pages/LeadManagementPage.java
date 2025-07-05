package pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import bases.BaseConfig;
import utilization.Use;

public class LeadManagementPage extends BaseConfig{
	public LeadManagementPage() {
		PageFactory.initElements(getDriver(), this);
	}

	//lead management board
    WebElement addActionButton;
    
    @FindBy(xpath="//span[@id='select2-assign_to-container']")
    WebElement assignToSelectButton;
    
    @FindBy(xpath="//input[@class='select2-search__field']")
    WebElement selectInput;
    
    WebElement assignToSelectLi;
    
    @FindBy(xpath="//span[@class='select2-search select2-search--inline']")
    WebElement labelSelectButton;
    
    WebElement labelSelectLi;
    
    @FindBy(xpath="//input[@id='start_date']")
    WebElement startDateInput;
    
    @FindBy(xpath="//input[@id='end_date']")
    WebElement endDateInput;
    
    @FindBy(xpath="//span[@id='select2-status-container']")
    WebElement statusSelectButton;
    
    WebElement statusSelectLi;
    
    @FindBy(xpath="//span[@id='select2-customer-container']")
    WebElement customerSelectButton;
    
    WebElement customerSelectLi;
    
    @FindBy(xpath="//span[@id='select2-priority-container']")
    WebElement prioritySelectButton;
    
    WebElement prioritySelectLi;
    
    @FindBy(xpath="//span[@id='select2-progress-container']")
    WebElement progressSelectButton;
    
    WebElement progressSelectLi;
    
    WebElement taskSelectLi;
    
    @FindBy(xpath="//textarea[@name='description']")
    WebElement descriptionInput;
    
    @FindBy(xpath="//input[@id='detail']")
    WebElement detailInput;
    
    @FindBy(xpath="//button[@id='detail_add']")
    WebElement detailAddButton;
    
    @FindBy(xpath="//input[@id='revenue']")
    WebElement revenueInput;
    
    @FindBy(xpath="//span[@id='select2-chance_of_winning-container']")
    WebElement chanceOfWinningSelectButton;
    
    @FindBy(xpath="//input[@class='select2-search__field']")
    WebElement chanceOfWinningSelectInput;
    
    WebElement chanceOfWinningSelectLi;
	
    WebElement cardSelect;
    
    @FindBy(xpath="//textarea[@name='comment']")
    WebElement commentInput;
    
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Edit')][ancestor::div/div[contains(@class,'comment')]]")
	WebElement editCommentButton;
	
	@FindBy(xpath="//button[contains(text(),'Edit') and @data-action='view']")
	WebElement editCardButton;
	
	@FindBy(xpath="//button[@data-action = 'edit' and contains(text(),'Save')]")
	WebElement saveCardButton;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveCommentButton;
	
	@FindBy(xpath="//button[@data-bs-dismiss='modal' and contains(text(),'Cancel')]")
	WebElement cancelButton;
	
	@FindBy(xpath="//button[text()='OK']")
	WebElement OKButton;
	
	WebElement leadManagementAlert;
	WebElement leadManagementCommentAlert;
	String[] dates;
	
	public void addLeadManagement(String status, String assignTo, String label, String startDate, String endDate, String customer, String priority,
			String progress, String description,String detail, String revenue, String chanceOfWinning,  String expectedResult) throws InterruptedException {
		addActionButton = getWait().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//button[contains(@class,'btnCreateItem')][ancestor::div/header/div/div[contains(text(),'"+status+"')]]"))));
		Use.Click(addActionButton);
		if(!assignTo.equals("")) {
			Use.Click(assignToSelectButton);
			Thread.sleep(1500);
			assignToSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+assignTo+"')]")));
			Use.Click(assignToSelectLi);
		}
		if(!label.equals("")) {
			Use.Click(labelSelectButton);
			Thread.sleep(1500);
			labelSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+label+"')]")));
			Use.Click(labelSelectLi);
		}
		dates = getStartAndEndOfCurrentMonth();
		Use.SendKeys(startDateInput, dates[0]);
		Use.SendKeys(endDateInput, dates[1]);
		Thread.sleep(2000);
		if(!status.equals("")) {
			Use.Click(statusSelectButton);
			Thread.sleep(1500);
			statusSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+status+"')]")));
			Use.Click(statusSelectLi);
		}
		if(!customer.equals("")) {
			Use.Click(customerSelectButton);
			Thread.sleep(1500);
			customerSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+customer+"')]")));
			Use.Click(customerSelectLi);
		}
		if(!priority.equals("")) {
			Use.Click(prioritySelectButton);
			Thread.sleep(1500);
			prioritySelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+priority+"')]")));
			Use.Click(prioritySelectLi);
		}
		if(!progress.equals("")) {
			Use.Click(progressSelectButton);
			Thread.sleep(1500);
			progressSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+progress+"')]")));
			Use.Click(progressSelectLi);
		}
		Use.SendKeys(descriptionInput, description);
		Use.SendKeys(detailInput, detail);
		Use.Click(detailAddButton);
		Use.SendKeys(revenueInput, revenue);
		if(!chanceOfWinning.equals("")) {
			Use.Click(chanceOfWinningSelectButton);
			Thread.sleep(1500);
			chanceOfWinningSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+chanceOfWinning+"')]")));
			Use.Click(chanceOfWinningSelectLi);
		}
		Use.Click(addButton);

		leadManagementAlert = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+expectedResult+"')]")));
		Assert.assertEquals(leadManagementAlert.getText(), expectedResult);
		Use.Click(OKButton);
	}
	
	public void editLeadManagement(String status, String assignTo, String label, String startDate, String endDate, String customer, String priority,
			String progress, String description,String detail, String revenue, String chanceOfWinning,  String comment, String expectedResult) throws InterruptedException {
		cardSelect = getWait().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//div/h2[contains(text(),'"+assignTo+"')][ancestor::main/div]"))));
		Use.Click(cardSelect);
		if(comment.equals("")) {
			Use.Click(editCardButton);
			if(!assignTo.equals("")) {
				Use.Click(assignToSelectButton);
				Thread.sleep(1500);
				assignToSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+assignTo+"')]")));
				Use.Click(assignToSelectLi);
			}
			if(!label.equals("")) {
				Use.Click(labelSelectButton);
				Thread.sleep(1500);
				labelSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+label+"')]")));
				Use.Click(labelSelectLi);
			}
			Use.SendKeys(startDateInput, startDate);
			Use.SendKeys(endDateInput, endDate);
			Thread.sleep(2000);
			if(!status.equals("")) {
				Use.Click(statusSelectButton);
				Thread.sleep(1500);
				statusSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+status+"')]")));
				Use.Click(statusSelectLi);
			}
			if(!customer.equals("")) {
				Use.Click(customerSelectButton);
				Thread.sleep(1500);
				customerSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+customer+"')]")));
				Use.Click(customerSelectLi);
			}
			if(!priority.equals("")) {
				Use.Click(prioritySelectButton);
				Thread.sleep(1500);
				prioritySelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+priority+"')]")));
				Use.Click(prioritySelectLi);
			}
			if(!progress.equals("")) {
				Use.Click(progressSelectButton);
				Thread.sleep(1500);
				progressSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+progress+"')]")));
				Use.Click(progressSelectLi);
			}
			Use.SendKeys(descriptionInput, description);
			Use.SendKeys(detailInput, detail);
			Use.Click(detailAddButton);
			Use.SendKeys(revenueInput, revenue);
			if(!chanceOfWinning.equals("")) {
				Use.Click(chanceOfWinningSelectButton);
				Thread.sleep(1500);
				chanceOfWinningSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+chanceOfWinning+"')]")));
				Use.Click(chanceOfWinningSelectLi);
			}
			Thread.sleep(500);
			Use.Click(saveCardButton);
			
			leadManagementAlert = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+expectedResult+"')]")));
			Assert.assertEquals(leadManagementAlert.getText(), expectedResult);
			Use.Click(OKButton);
		}else {
			Use.Click(commentInput);
			Use.SendKeys(commentInput, comment);
			Use.Click(saveCommentButton);
			
			leadManagementCommentAlert = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+expectedResult+"')]")));
			Assert.assertEquals(leadManagementCommentAlert.getText(), expectedResult);
			Use.Click(cancelButton);//for closing form modal
		}
	}
	
	public static String[] getStartAndEndOfCurrentMonth() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String startDateFormatted = startOfMonth.format(formatter);
        String endDateFormatted = endOfMonth.format(formatter);

        return new String[]{startDateFormatted, endDateFormatted};
    }
}