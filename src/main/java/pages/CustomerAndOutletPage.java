package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import bases.BaseConfig;
import utilization.Use;

public class CustomerAndOutletPage extends BaseConfig{
	public CustomerAndOutletPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//list customer data table
	@FindBy(xpath="//button[contains(@data-url,'setting/customer/create')]")
    WebElement addCustomerButton;
	
	@FindBy(xpath="//input[@type='search']")
    WebElement searchInput;
	
    WebElement editCustomerButton;
    WebElement listOutletButton;
    
	//form add/edit class
	@FindBy(xpath="//input[@name='customer_name']")
    WebElement customerNameInput;
	
	@FindBy(xpath="//span[@id='select2-province-container']")
	WebElement provinceSelectButton;
	
	@FindBy(xpath="//input[@class='select2-search__field']")
	WebElement selectInput;
	
	WebElement provinceSelectLi;
	
	@FindBy(xpath="//span[@id='select2-region-container']")
	WebElement regionSelectButton;
	
	WebElement regionSelectLi;

	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveButton;
	
	WebElement customerAlert;
	
	//list outlet data table
    @FindBy(xpath="//a[contains(@href,'outlet/create')]")
    WebElement addOutletButton;
	
    WebElement editOutletButton;
    
	@FindBy(xpath="//input[@name='outlet_name']")
    WebElement outletNameInput;
	
	@FindBy(xpath="//textarea[@name='address']")
	WebElement addressInput;
	
	@FindBy(xpath="//span[@id='btnModal']")
	WebElement setPointButton;
	
	@FindBy(xpath="//input[@id='pac-input']")
	WebElement searchLocationInput;
	
	@FindBy(xpath="//button[@id='btnSubmitMap']")
	WebElement submitMapButton;
	
	@FindBy(xpath="//input[@id='PIC']")
	WebElement PICInput;
	
	@FindBy(xpath="//input[@name='phone_number']")
	WebElement phoneNumberInput;
	
	WebElement outletAlert;
	
	
	public void addCustomer(String customerName, String province, String region, String expectedResult) throws InterruptedException {
		Use.Click(addCustomerButton);
		Use.SendKeys(customerNameInput, customerName);
		if(!province.equals("")) {			
			Use.Click(provinceSelectButton);
			Thread.sleep(1000);
			Use.SendKeys(selectInput, province);
			Thread.sleep(1500);
			provinceSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+province+"')]")));
			Use.Click(provinceSelectLi);
		}
		if(!region.equals("")) {			
			Use.Click(regionSelectButton);
			Thread.sleep(1000);
			Use.SendKeys(selectInput, region);
			Thread.sleep(2000);
			regionSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+region+"')]")));
			Use.Click(regionSelectLi);
		}
		Use.Click(addButton);
		customerAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(customerAlert.getText(), expectedResult);
	}
	
	public void editCustomer(String customerName, String province, String region, String expectedResult) throws InterruptedException {
		if(customerName.equals("Suka Makmur")) {
			System.out.println("masuk");
		}
		Use.SendKeys(searchInput, customerName);
		Thread.sleep(2000);
		listOutletButton = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("(//a[contains(@data-url,'setting/customer/edit')]/i)[1][ancestor::tr/td[contains(text(),'"+customerName+"')]]"))));
		Use.Click(listOutletButton);
		
		Use.SendKeys(customerNameInput, customerName);
		if(!province.equals("")) {			
			Use.Click(provinceSelectButton);
			Thread.sleep(1000);
			Use.SendKeys(selectInput, province);
			Thread.sleep(1500);
			provinceSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+province+"')]")));
			Use.Click(provinceSelectLi);
		}
		if(!region.equals("")) {			
			Use.Click(regionSelectButton);
			Thread.sleep(1000);
			Use.SendKeys(selectInput, region);
			Thread.sleep(1500);
			regionSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+region+"')]")));
			Use.Click(regionSelectLi);
		}
		Use.Click(saveButton);
		customerAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(customerAlert.getText(), expectedResult);
	}
	
	public void addOutletCustomer(String customerName, String outletName, String province, String region,String address, String location, String PIC, String phoneNumber, String expectedResult) throws InterruptedException {
		Use.SendKeys(searchInput, customerName);
		Thread.sleep(2500);
		listOutletButton = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("(//a[contains(@href,'outlet')]/i)[ancestor::tr/td[contains(text(),'"+customerName+"')]]"))));
		Use.Click(listOutletButton);
		Use.Click(addOutletButton);
		
		Use.SendKeys(outletNameInput, outletName);
		if(!province.equals("")) {			
			Use.Click(provinceSelectButton);
			Thread.sleep(1000);
			Use.SendKeys(selectInput, province);
			Thread.sleep(1500);
			provinceSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+province+"')]")));
			Use.Click(provinceSelectLi);
		}
		if(!region.equals("")) {			
			Use.Click(regionSelectButton);
			Thread.sleep(1000);
			Use.SendKeys(selectInput, region);
			Thread.sleep(1500);
			regionSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+region+"')]")));
			Use.Click(regionSelectLi);
		}
		Use.SendKeys(addressInput, address);
		
		//open map
		Use.Click(setPointButton);
		Use.SendKeys(searchLocationInput, location);
		Thread.sleep(1500);
		searchLocationInput.sendKeys(Keys.ENTER);
		Use.Click(submitMapButton);
		
		Use.SendKeys(PICInput, PIC);
		Use.SendKeys(phoneNumberInput, phoneNumber);
		Thread.sleep(1000);
		Use.Click(saveButton);
		outletAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(outletAlert.getText(), expectedResult);
	}
	
	public void editOutletCustomer(String customerName, String outletName, String province, String region,String address, String location, String PIC, String phoneNumber, String expectedResult) throws InterruptedException {
		Use.SendKeys(searchInput, customerName);
		Thread.sleep(2000);
		listOutletButton = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("(//a[contains(@data-url,'outlet')]/i)[1][ancestor::tr/td[contains(text(),'"+customerName+"')]]"))));
		Use.Click(listOutletButton);

		Use.SendKeys(searchInput, outletName);
		Thread.sleep(2000);
		editOutletButton = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("(//a[contains(@href,'outlet')]/i)[ancestor::tr/td[contains(text(),'"+outletName+"')]]"))));
		Use.Click(editOutletButton);
		
		Use.SendKeys(outletNameInput, outletName);
		if(!province.equals("")) {			
			Use.Click(provinceSelectButton);
			Thread.sleep(1000);
			Use.SendKeys(selectInput, province);
			Thread.sleep(1500);
			provinceSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+province+"')]")));
			Use.Click(provinceSelectLi);
		}
		if(!region.equals("")) {			
			Use.Click(regionSelectButton);
			Thread.sleep(1000);
			Use.SendKeys(selectInput, region);
			Thread.sleep(1500);
			regionSelectLi = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+region+"')]")));
			Use.Click(regionSelectLi);
		}
		Use.SendKeys(addressInput, address);
		
		//open map
		Use.Click(setPointButton);
		Use.SendKeys(searchLocationInput, location);
		Thread.sleep(1500);
		searchLocationInput.sendKeys(Keys.ENTER);
		Use.Click(submitMapButton);
		
		Use.SendKeys(PICInput, PIC);
		Use.SendKeys(phoneNumberInput, phoneNumber);
		Use.Click(saveButton);
		outletAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(outletAlert.getText(), expectedResult);
	}
}