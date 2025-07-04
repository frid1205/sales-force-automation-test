package utilization;

import bases.BaseConfig;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Collections;

public class MUse extends BaseConfig {
	private static int maxAttempt = 3;
	
	//with clicking the element prior
	public static void sendKeys(WebElement element, String keysToSend) throws InterruptedException {
		if(!keysToSend.isEmpty()) {
			int attempt = 0;
			while(attempt < maxAttempt) {
				try {
					getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
					getWait().until(ExpectedConditions.elementToBeClickable(element)).sendKeys(keysToSend);
					break;
				} catch(StaleElementReferenceException ex) {
					System.out.println("waitClickableThenClick StaleElementReferenceException");
					if (attempt == maxAttempt) {
						Assert.assertEquals(true, false, "Element cannot be clicked");
					}
				}
				attempt++;
			}
			//hideKeyboard();
		}
	}
	
	//without clicking the element prior
	public static void sendKeys2(WebElement element, String keysToSend) throws InterruptedException {
		if(!keysToSend.isEmpty()) {
			int attempt = 0;
			while(attempt < maxAttempt) {
				try {
					element.sendKeys(keysToSend);
					break;
				} catch(StaleElementReferenceException ex) {
					System.out.println("waitClickableThenClick StaleElementReferenceException");
					if (attempt == maxAttempt) {
						Assert.assertEquals(true, false, "Element cannot be found");
					}
				}
				attempt++;
			}
			//hideKeyboard();
		}
	}
	
	public static void click(WebElement element) throws InterruptedException {
		try{
			Thread.sleep(1000);
			getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
		}catch(Exception e) {
			Actions actions = new Actions(getMDriver());
			actions.moveToElement(element).click().perform();
		}
	}
	
	public static void tap(WebElement element) throws InterruptedException {
		Actions actions = new Actions(getMDriver());
		actions.moveToElement(element).click().perform();
	}
	
	public static void swipeUp(int startX, int startY, int endX, int endY) {
	    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
	    Sequence swipe = new Sequence(finger, 0);

	    swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
	    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

	    swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));
	    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	    getMDriver().perform(Collections.singletonList(swipe));
	}
	
	public static void hideKeyboard() {
		getMDriver().hideKeyboard();
	}
}