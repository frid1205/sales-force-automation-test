package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import bases.BaseConfig;
import utilization.Use;

public class LeadLabelPage extends BaseConfig{
	public LeadLabelPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//list data table
	@FindBy(xpath="//button[contains(@data-url,'label-leads-management/create')]")
    WebElement addLeadLabelButton;
	
	@FindBy(xpath="//input[@type='search']")
    WebElement searchLabelInput;
	
    WebElement editLeadLabelButton;
	
	//form add/edit class
	@FindBy(xpath="//input[@name='label_name']")
    WebElement labelNameInput;
	
	@FindBy(xpath="//input[@name='color']")
	WebElement labelColorInput;
	
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveButton;
	
	WebElement LeadLabelAlert;
	
	public void addLeadLabel(String labelName, String labelColor, String expectedResult) throws InterruptedException {
		Use.Click(addLeadLabelButton);
		Use.SendKeys(labelNameInput, labelName);
		Use.SendKeys(labelColorInput, labelColor);
		Use.Click(addButton);
		LeadLabelAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(LeadLabelAlert.getText(), expectedResult);
	}
	
	public void editLeadLabel(String labelName, String labelColor, String expectedResult) throws InterruptedException {
		Use.SendKeys(searchLabelInput, labelName);
		Thread.sleep(2000);
		editLeadLabelButton = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("(//a[contains(@data-url,'label-leads-management/edit')]/i)[1][ancestor::tr[td[contains(text(), '"+labelName+"')]]]"))));
		Use.Click(editLeadLabelButton);
		Use.SendKeys(labelNameInput, labelName);
		Use.SendKeys(labelColorInput, labelColor);
		Use.Click(saveButton);
		LeadLabelAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(LeadLabelAlert.getText(), expectedResult);
	}
}