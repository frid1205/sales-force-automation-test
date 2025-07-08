package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import bases.BaseConfig;
import utilization.Use;

public class TaskCategoryPage extends BaseConfig{
	public TaskCategoryPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//list data table
	@FindBy(xpath="//button[contains(@data-url,'task/create')]")
    WebElement addTaskCategoryButton;
	
	@FindBy(xpath="//input[@type='search']")
    WebElement searchCategoryInput;
	
    WebElement editTaskCategoryButton;
	
	//form add/edit class
	@FindBy(xpath="//input[@name='name']")
    WebElement taskCategoryNameInput;
	
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Edit')]")
	WebElement saveButton;
	
	WebElement taskCategoryProductAlert;
	
	public void addTaskCategoryProduct(String taskCategoryProduct, String expectedResult) throws InterruptedException {
		Use.Click(addTaskCategoryButton);
		Use.SendKeys(taskCategoryNameInput, taskCategoryProduct);
		Use.Click(addButton);
		taskCategoryProductAlert = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+expectedResult+"')]")));
		Assert.assertEquals(taskCategoryProductAlert.getText(), expectedResult);
	}
	
	public void editTaskCategoryProduct(String taskCategoryProduct, String expectedResult) throws InterruptedException {
		Use.SendKeys(searchCategoryInput, taskCategoryProduct);
		Thread.sleep(3000);
		editTaskCategoryButton = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@data-url,'setting/task/edit')]/i)[1][ancestor::tr[td[contains(text(), '"+taskCategoryProduct+"')]]]")));
		Use.Click(editTaskCategoryButton);
		Use.SendKeys(taskCategoryNameInput, taskCategoryProduct);
		Use.Click(saveButton);
		taskCategoryProductAlert = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+expectedResult+"')]")));
		Assert.assertEquals(taskCategoryProductAlert.getText(), expectedResult);
	}
}