package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import bases.BaseConfig;
import utilization.Use;

public class ExpensesManagementPage extends BaseConfig{
	public ExpensesManagementPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath="//span[@id='select2-status-container']")
	WebElement statusApprovalButton;
	
	WebElement statusApprovalLi;
	
    WebElement detailExpensesButton;
    
    @FindBy(xpath="//button[@data-type='approve']")
    WebElement approveButton;
    
    @FindBy(xpath="//button[@data-type='reject']")
    WebElement rejectButton;
    
    @FindBy(xpath="//input[@id='swal2-input']")
    WebElement reasonRejectInput;
    
    @FindBy(xpath="//button[contains(text(),'Yes')]")
    WebElement confirmYesButton;
    
	WebElement expenseAlert;
	
	public void approveOrReject(String status, String requester, String outlet, String approveOrReject, String reasonReject, String expectedResult) throws InterruptedException {
		if(!status.equals("")) {
			Use.Click(statusApprovalButton);
			Thread.sleep(1000);
			statusApprovalLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+status+"')]")));
			Use.Click(statusApprovalLi);
		}
		detailExpensesButton = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//a[@title='Detail Expenses'][ancestor::tr/td[contains(text(),'"+requester+"')]][ancestor::tr/td[contains(text(),'"+outlet+"')]]"))));
		Use.Click(detailExpensesButton);
		
		if(approveOrReject.contains("approve")) {
			Use.Click(approveButton);
			Use.Click(confirmYesButton);
		}else {
			Use.Click(rejectButton);
			Use.SendKeys(reasonRejectInput, reasonReject);
			Use.Click(confirmYesButton);
		}
		
		expenseAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(expenseAlert.getText(), expectedResult);
	}
}