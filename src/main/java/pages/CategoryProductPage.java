package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import bases.BaseConfig;
import utilization.Use;

public class CategoryProductPage extends BaseConfig{
	public CategoryProductPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//list data table
	@FindBy(xpath="//button[contains(@data-url,'category-product/create')]")
    WebElement addCategoryProductButton;
	
	@FindBy(xpath="//input[@type='search']")
    WebElement searchCategoryInput;
	
	@FindBy(xpath="(//a[contains(@data-url,'category-product/edit')]/i)[1]")
    WebElement editCategoryProductButton;
	
	@FindBy(xpath="//a[@title='Delete Category Product']")
    WebElement deleteButton;
	
	@FindBy(xpath="//button[@class='swal2-confirm swal2-styled swal2-default-outline']")
	WebElement confirmDeleteButton;
	
	//form add/edit class
	@FindBy(xpath="//input[@name='category_name']")
    WebElement categoryNameInput;
	
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveButton;
	
	@FindBy(xpath="//button[text()='OK']")
	WebElement OKButton;
	
	WebElement categoryProductAlert;
	
	public void addCategoryProduct(String categoryProduct, String expectedResult) throws InterruptedException {
		Use.Click(addCategoryProductButton);
		Use.SendKeys(categoryNameInput, categoryProduct);
		Use.Click(addButton);
		categoryProductAlert = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+expectedResult+"')]")));
		Assert.assertEquals(categoryProductAlert.getText(), expectedResult);
		Use.Click(OKButton);
	}
	
	public void editCategoryProduct(String categoryProductName, String expectedResult) throws InterruptedException {
		Use.SendKeys(searchCategoryInput, categoryProductName);
		Thread.sleep(2500);
		editCategoryProductButton = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@data-url,'category-product/edit')]/i)[1][ancestor::tr/td[contains(text(),'"+categoryProductName+"')]]")));
		Use.Click(editCategoryProductButton);
		Use.SendKeys(categoryNameInput, categoryProductName);
		Use.Click(saveButton);
		categoryProductAlert = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+expectedResult+"')]")));
		Assert.assertEquals(categoryProductAlert.getText(), expectedResult);
		Use.Click(OKButton);
	}
}