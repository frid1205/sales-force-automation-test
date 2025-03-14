package mobilePages;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bases.BaseConfig;
import io.appium.java_client.Location;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilization.MUse;

public class ClockInOutPage extends BaseConfig{
	public ClockInOutPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getMDriver()), this);
    }
	double dLongtitude;
	double dLatitude;
	
	@FindBy (xpath="//android.widget.ImageView[contains(@content-desc,'Home')]")
	RemoteWebElement homeButton;
	
	@FindBy (xpath="//android.widget.ScrollView/android.widget.ImageView[1]")
	RemoteWebElement clockButton;
	
	@FindBy (xpath="//android.widget.ImageView")
	RemoteWebElement capturePhotoButton;
	
	@FindBy (xpath="//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]")
	RemoteWebElement yesConfirmCapturePhotoButton;
	
	@FindBy (xpath="//android.widget.Button[contains(@content-desc,'Clock')]")
	RemoteWebElement clockInOutButton;
	
	@FindBy (xpath="//android.widget.Button[@content-desc='Submit']")
	RemoteWebElement submitButton;
	
	public void clockInOut(String longtitude, String latitude) throws InterruptedException {
		Double dLongtitude = Double.parseDouble(longtitude);
		Double dLatitude = Double.parseDouble(latitude);
		Location location = new Location(dLongtitude,dLatitude, 1.0); 
        getMDriver().setLocation(location);
        Thread.sleep(10000); //wait for change location succesfully
        MUse.click(homeButton);
        MUse.click(clockButton);
        MUse.click(capturePhotoButton);
        MUse.click(yesConfirmCapturePhotoButton);
        MUse.click(clockInOutButton);
        MUse.click(submitButton);
        Thread.sleep(5000);
	}
}