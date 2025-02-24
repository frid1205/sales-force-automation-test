package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import bases.BaseConfig;
import utilization.Use;
import utilization.Utilize;

public class ProductPage extends BaseConfig{
	public ProductPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//list data table
	@FindBy(xpath="//button[contains(@data-url,'setting/product/create')]")
    WebElement addProductButton;
	
	@FindBy(xpath="//input[@type='search']")
    WebElement searchInput;
	
    WebElement editProductButton;
	
	//form add/edit class
	@FindBy(xpath="//input[@name='product_name']")
    WebElement productNameInput;
	
	@FindBy(xpath="//span[@id='select2-category-container']")
	WebElement categorySelectButton;
	
	@FindBy(xpath="//input[@class='select2-search__field']")
	WebElement categorySelectInput;
	
	WebElement categorySelectLi;
	
	@FindBy(xpath="//textarea[@name='product_advantage']")
	WebElement productAdvantageInput;
	
	@FindBy(xpath="//textarea[@name='product_detail']")
	WebElement productDetailInput;
	
	@FindBy(xpath="//input[@name='image[]']")
	WebElement productImageInput;
	
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Edit')]")
	WebElement saveButton;
	
	WebElement productAlert;
	
	public void addProduct(String productName, String productCategory, String productAdvantage, String productDetail, String productImage, String expectedResult) throws InterruptedException {
		Use.Click(addProductButton);
		Use.SendKeys(productNameInput, productName);
		if(!productCategory.equals("")) {			
			Use.Click(categorySelectButton);
			Thread.sleep(1000);
			categorySelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+productCategory+"')]")));
			Use.Click(categorySelectLi);
		}
		Use.SendKeys(productAdvantageInput, productAdvantage);
		Use.SendKeys(productDetailInput, productDetail);
		Use.uploadImage(productImageInput, Utilize.getAbsolutePath(productImage));
		Use.Click(addButton);
		productAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(productAlert.getText(), expectedResult);
	}
	
	public void editProduct(String productName, String productCategory, String productAdvantage, String productDetail, String productImage, String expectedResult) throws InterruptedException {
		Use.SendKeys(searchInput, productName);
		Thread.sleep(2000);
		editProductButton = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("(//a[contains(@data-url,'setting/product/edit')]/i)[1][ancestor::tr/td[contains(text(),'"+productName+"')]]"))));
		Use.Click(editProductButton);
		Use.SendKeys(productNameInput, productName);
		if(!productCategory.equals("")) {			
			Use.Click(categorySelectButton);
			Thread.sleep(1000);
			categorySelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+productCategory+"')]")));
			Use.Click(categorySelectLi);
		}
		Use.SendKeys(productAdvantageInput, productAdvantage);
		Use.SendKeys(productDetailInput, productDetail);
		Use.uploadImage(productImageInput, Utilize.getAbsolutePath(productImage));
		Use.Click(saveButton);
		productAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(productAlert.getText(), expectedResult);
	}
}