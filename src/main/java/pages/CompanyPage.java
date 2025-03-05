package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import bases.BaseConfig;
import utilization.Use;
import utilization.Utilize;

public class CompanyPage extends BaseConfig{
	public CompanyPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//list data table
	@FindBy(xpath="//a[contains(@href,'company/create')]")
    WebElement addCompanyButton;
	
	@FindBy(xpath="//input[@type='search']")
    WebElement searchCompanyInput;
	
	@FindBy(xpath="//a[contains(@title,'Edit Company')]")
    WebElement editCompanyButton;
	
	@FindBy(xpath="//a[@title='Delete Company']")
    WebElement deleteButton;
	
	@FindBy(xpath="//button[@class='swal2-confirm swal2-styled swal2-default-outline']")
	WebElement confirmDeleteButton;
	
	//form add/edit class
	@FindBy(xpath="//input[@name='name']")
    WebElement companyNameInput;
	
	@FindBy(xpath="//input[@name='contact_number']")
    WebElement companyContactNumberInput;
	
	@FindBy(xpath="//textarea[@name='address']")
	WebElement companyAddressTextarea;
	
	@FindBy(xpath="//textarea[@name='description']")
	WebElement companyDescriptionTextarea;
	
	@FindBy(xpath="//input[@name='pic_name']")
	WebElement companyPICNameInput;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement companyPICEmailInput;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement companyPICPasswordInput;
	
	@FindBy(xpath="//span[@id='select2-pic-container']")
	WebElement PICButton;
	
	@FindBy(xpath="//input[@class='select2-search__field']")
	WebElement PICSelectInput;
	
	WebElement PICSelect;
	
	@FindBy(xpath="//input[@id='OpenStreetMap']")
	WebElement openStreetCheckbox;
	
	@FindBy(xpath="//input[@id='GoogleMap']")
	WebElement googleMapCheckbox;
	
	@FindBy(xpath="//button[contains(text(),'OK')]")
	WebElement OKButton;
	
	@FindBy(xpath="//input[@name='map_api_key']")
	WebElement mapAPIKeyInput;
	
	@FindBy(xpath="//input[@name='activation_date']")
	WebElement activationDateInput;
	
	@FindBy(xpath="//input[@name='expiration_date']")
	WebElement expiratationDateInput;
	
	@FindBy(xpath="//input[@name='logo']")
	WebElement companyLogoInput;
	
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveButton;
	
	WebElement companyAlert;
	
	
	public void addCompany(String companyName, String companyContactNumber, String companyAddress, String companyDescription, String companyPICName,
			String companyPICEmail, String companyPICPassword, String mapType, String mapAPIKey, String activationDate, String expirationDate, String companyLogo, String expectedResult) throws InterruptedException {
		Use.Click(addCompanyButton);
		Use.SendKeys(companyNameInput, companyName);
		Use.SendKeys(companyContactNumberInput, companyContactNumber);
		Use.SendKeys(companyAddressTextarea, companyAddress);
		Use.SendKeys(companyDescriptionTextarea, companyDescription);
		Use.SendKeys(companyPICNameInput, companyPICName);
		Use.SendKeys(companyPICEmailInput, companyPICEmail);
		Use.SendKeys(companyPICPasswordInput, companyPICPassword);
		if(mapType.equals("googlemap")) {
			Use.Click(googleMapCheckbox);
			Use.Click(OKButton);
			Use.SendKeys(mapAPIKeyInput, mapAPIKey);
		}else {
			Use.Click(openStreetCheckbox);
		}
		
		Use.SendKeys(activationDateInput, activationDate);
		Use.SendKeys(expiratationDateInput, expirationDate);
		Use.uploadImage(companyLogoInput, Utilize.getAbsolutePath(companyLogo));
		Use.Click(addButton);
		companyAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(companyAlert.getText(), expectedResult);
	}
	
	public void editCompany(String companyName, String companyContactNumber, String companyAddress, String companyDescription, String companyPICName,
			String mapType, String mapAPIKey, String activationDate, String expirationDate, String companyLogo, String expectedResult) throws InterruptedException {
		
		Use.SendKeys(searchCompanyInput, companyName);
		Thread.sleep(2000);
		Use.Click(editCompanyButton);
		Use.SendKeys(companyNameInput, companyName);
		Use.SendKeys(companyContactNumberInput, companyContactNumber);
		Use.SendKeys(companyAddressTextarea, companyAddress);
		Use.SendKeys(companyDescriptionTextarea, companyDescription);
		if(!companyPICName.equals("")) {			
			Use.Click(PICButton);
			PICSelect = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='select2-pic-results']/li[text()='"+companyPICName+"']")));
			Use.Click(PICSelect);
		}
		if(mapType.equals("googlemap")) {
			if(!googleMapCheckbox.isSelected()) {				
				Use.Click(googleMapCheckbox);
				Use.Click(OKButton);
			}
			Use.SendKeys(mapAPIKeyInput, mapAPIKey);
		}else {
			Use.Click(openStreetCheckbox);
		}
		
		Use.SendKeys(activationDateInput, activationDate);
		Use.SendKeys(expiratationDateInput, expirationDate);
		Use.uploadImage(companyLogoInput, Utilize.getAbsolutePath(companyLogo));
		Use.Click(saveButton);
		companyAlert = getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//*[contains(text(),'"+expectedResult+"')]"))));
		Assert.assertEquals(companyAlert.getText(), expectedResult);
	}
	
	
}
