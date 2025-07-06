package mobilePages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import bases.BaseConfig;
import io.appium.java_client.Location;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilization.MUse;

public class CheckInOutPage extends BaseConfig{
	public CheckInOutPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getMDriver()), this);
    }
	
	@FindBy (xpath="//android.widget.ImageView[contains(@content-desc,'Schedule')]")
	WebElement scheduleMenuButton;
	
	WebElement cardButton;
	List<WebElement> cardButtonList;
	
	WebElement statusButton;
	
	@FindBy (xpath="//android.view.View[contains(@content-desc,'Take a picture')]")
	WebElement checkInPhotoButton;
	
	@FindBy (xpath="//android.widget.ImageView")
	WebElement takeAPictureButton;
	
	@FindBy (xpath="//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]")
	WebElement confirmTakeAPictureButton;
	
	@FindBy (xpath="//android.widget.Button[@content-desc='Check In']")
	WebElement checkInButton;
	
	@FindBy (xpath="//android.widget.Button[@content-desc='Cancel Schedule']")
	WebElement cancelCheckInButton;
	
	//check out
	@FindBy (xpath="//android.widget.ScrollView/android.view.View[15]")
	WebElement productSelectButton;
	
	WebElement productSelect;
	
	@FindBy (xpath="//android.widget.ScrollView/android.widget.EditText[1]")
	WebElement quantityProductInput;

	@FindBy (xpath="//android.widget.ScrollView/android.widget.EditText[2]")
	WebElement priceProductInput;
	
	@FindBy (xpath="//android.view.View[@content-desc='Add more']")
	WebElement addMoreProductButton;
	
	@FindBy (xpath="//android.view.View[contains(@content-desc,'Check Out Photo')]")
	WebElement checkOutPhotoButton;
	
	@FindBy (xpath="//android.view.View[contains(@content-desc,'Select File')]")
	WebElement selectFileButton;
	
	@FindBy (xpath="//android.widget.ImageButton[@content-desc='Show roots']")
	WebElement burgerMenuButton;
	
	@FindBy (xpath="//android.widget.TextView[@resource-id='android:id/title' and @text='Downloads']")
	WebElement downloadMenuButton;
	
	@FindBy (xpath="//android.widget.ImageView[@resource-id='com.google.android.documentsui:id/icon_thumb']")
	WebElement selectFile;
	
	@FindBy (xpath="//android.widget.Button[@content-desc='Check Out']")
	WebElement checkOutButton;
	
	@FindBy (xpath="//android.widget.Button[@content-desc='Submit']")
	WebElement submitButton;
	
	@FindBy (xpath="//android.widget.Button[@content-desc='Cancel Schedule']")
	WebElement cancelButton;
	
	@FindBy (xpath="//android.view.View[@content-desc='Selected Reason']")
	WebElement reasonSelectButton;
	
	@FindBy (xpath="//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]")
	WebElement backButton;
	
	WebElement reasonSelect;
	
	Double dLongtitude, dLatitude;
	String productName, quantity, price;
	String[] products, details;
	
	public void checkInOut(String scenario, String longtitude, String latitude, String status, String outlet, String customer, String dataProduct, String attachmentCheckout, String reason) throws InterruptedException {
		Thread.sleep(3500);
		MUse.click(scheduleMenuButton);
		if(!longtitude.equals("") || !latitude.equals("")) {
			dLongtitude = Double.parseDouble(longtitude);
			dLatitude = Double.parseDouble(latitude);
			Location location = new Location(dLongtitude,dLatitude, 1.0); 
			getMDriver().setLocation(location);
			Thread.sleep(10000); //wait for change location succesfully
		}
		
        for (int loop = 0; loop <= 3; loop++) {
        	cardButtonList = getMDriver().findElements(By.xpath("//android.view.View[contains(@content-desc,'" + status + "') " +
        			"and contains(@content-desc,'" + outlet + "') " +
        			"and contains(@content-desc,'" + customer + "')]"));
        	
            if (!cardButtonList.isEmpty()) {
            	System.out.println("Scenario :"+scenario+", status : "+status+", Outlet : "+outlet);
                cardButton = getMDriver().findElement(By.xpath("//android.view.View[contains(@content-desc,'" + status + "') and contains(@content-desc,'" + outlet + "') and contains(@content-desc,'" + customer + "')]"));
                MUse.click(cardButton);
                
                if(scenario.equals("movelocation")) {
                	Thread.sleep(60000);
                }else if(scenario.equals("checkin")) {
                	MUse.click(checkInPhotoButton);
                	Thread.sleep(1000);
                	MUse.click(takeAPictureButton);
                	MUse.click(confirmTakeAPictureButton);
                	MUse.click(checkInButton);
                	MUse.click(submitButton);
                	Thread.sleep(8000);
                }else if(scenario.equals("checkout")) {
                     products = dataProduct.split(";");
        	         for (int i = 0; i < products.length; i++) {
        	             details = products[i].split(",");
        	             productName = details[0].trim();
        	             quantity = details[1].trim();
        	             price = details[2].trim();
        	             
        	             Thread.sleep(1000);
        	             productSelectButton = getWait().until(ExpectedConditions.elementToBeClickable(getMDriver().findElement(By.xpath("(//android.widget.Button[ancestor::android.widget.ScrollView/android.view.View[contains(@content-desc,'Product')]])["+(i+1)+"]"))));
        	             MUse.click(productSelectButton);  
        	             Thread.sleep(1000);
        	     		 productSelect = getWait().until(ExpectedConditions.elementToBeClickable(getMDriver().findElement(By.xpath("//android.view.View[@content-desc='"+productName+"']"))));
        	             MUse.click(productSelect);
        	             Thread.sleep(1000);
        	             quantityProductInput = getWait().until(ExpectedConditions.elementToBeClickable(getMDriver().findElement(By.xpath("//android.widget.EditText["+((2*i)+1)+"]"))));
        	             MUse.sendKeys(quantityProductInput, quantity);
        	             MUse.hideKeyboard();
        	             Thread.sleep(1000);
        	             priceProductInput = getWait().until(ExpectedConditions.elementToBeClickable(getMDriver().findElement(By.xpath("//android.widget.EditText["+(2*(i+1))+"]"))));
        	             MUse.sendKeys(priceProductInput, price);
        	             MUse.hideKeyboard();
        	             if (products.length >1 & i+1 < products.length) {
        	            	 addMoreProductButton.click();
        	            	 Thread.sleep(2000);
        	            	 MUse.swipeUp(470, 1648, 470, 1046);
        	             }
        	         }
        	         MUse.click(checkOutPhotoButton);
        	         MUse.click(takeAPictureButton);
                 	 MUse.click(confirmTakeAPictureButton);
                 	 //MUse.hideKeyboard();
                 	 //MUse.swipeUp(430, 1286, 430, 768);
                 	 Thread.sleep(1000);
                 	 //disable add attachment
        			 /*MUse.sendKeys(selectFileButton, Utilize.getAbsolutePath(attachmentCheckout));
        			 MUse.click(burgerMenuButton); MUse.click(downloadMenuButton);
        			 MUse.click(selectFile);*/
                 	 MUse.click(checkOutButton);
                 	 MUse.click(submitButton);
                 	 Thread.sleep(15000);
                }else if(scenario.equals("cancel")) {
                	MUse.click(cancelButton);
                	MUse.click(reasonSelectButton);
                	reasonSelect = getWait().until(ExpectedConditions.elementToBeClickable(getMDriver().findElement(By.xpath("//android.view.View[@content-desc='"+reason+"']"))));
                	MUse.click(reasonSelect);
                	MUse.click(submitButton);
                	Thread.sleep(8000);
                }
                break;
            }
            MUse.swipeUp(435, 1461, 435, 1009);
            
            if (cardButtonList.isEmpty() && loop==3) {
            	//MUse.click(backButton);
            	sa.assertTrue(false,"test failed, scenario : "+scenario+", outlet :"+outlet+",customer :"+customer);
            	sa.assertAll();
            }
        }
	}
}