package utilization;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import bases.BaseConfig;

public class Use extends BaseConfig {
	private static int maxAttempt = 10;
	
	public static void SendKeys(WebElement element, String keysToSend) throws InterruptedException {
		scrollIntoView(getWait().until(ExpectedConditions.visibilityOf(element)));
		
		if(!keysToSend.isEmpty()) {
			try {
				if(!element.getAttribute("value").isEmpty()) {
					element.clear();
				}else if(!element.getText().equals("")) {
					element.clear();
				}
			}catch(Exception e) {
				System.out.println("Value already empty : "+e);
			}
			int attempt = 0;
			while(attempt < maxAttempt) {
				try {
					getWait().until(ExpectedConditions.elementToBeClickable(element));
					element.sendKeys(keysToSend);
					break;
				} catch(StaleElementReferenceException ex) {
					System.out.println("waitClickableThenClick StaleElementReferenceException");
					if (attempt == maxAttempt) {
						Assert.assertEquals(true, false, "Element cannot be clicked");
					}
				}
				attempt++;
			}
		}
	}
	
	public static void uploadImage(WebElement element, String keysToSend) {
		if(!keysToSend.isEmpty()) {
			int attempt = 0;
			while(attempt < maxAttempt) {
				try {
					
					element.sendKeys(keysToSend);
					break;
				}
				catch(ElementNotInteractableException ex) {
					System.out.println("waitClickableThenClick StaleElementReferenceException");
					if (attempt == maxAttempt) {
						Assert.assertEquals(true, false, "Element cannot be found");
					}
				}
				attempt++;
			}
		}
	}
	
	public static void waitElement(WebElement element) throws InterruptedException {
		int i= 0;
		boolean stop = false;
		while(stop == false) {
			try{
				Thread.sleep(1000);
				getWait().until(ExpectedConditions.visibilityOf(element));
				stop = true;
			}catch(Exception e) {
				Thread.sleep(1000);
				stop = false;
				i++;
				if(i==maxAttempt) 
				{
					Assert.assertTrue(false,"failed to find element "+element);
					break;
				}
			}
		}
	}
	
	public static void Click(WebElement element) throws InterruptedException {
		try {
			scrollIntoView(getWait().until(ExpectedConditions.elementToBeClickable(element)));
		}catch(ElementClickInterceptedException e) {
			scrollIntoView(getWait().until(ExpectedConditions.visibilityOf(element)));
		}catch(ElementNotInteractableException e) {
			scrollIntoView(element);
		}
		
		int attempt = 1;
		while(attempt <= maxAttempt) {
			try {
				Thread.sleep(500);
				getWait().until(ExpectedConditions.elementToBeClickable(element)).click();

				break;
			} catch(StaleElementReferenceException ex) {
				System.out.println(ex);
				if (attempt == maxAttempt) {
					Assert.assertEquals(true, false, "Element cannot be clicked");
				}
			} catch(ElementClickInterceptedException ex) {
				System.out.println(ex);
				if (attempt == maxAttempt) {
					Assert.assertEquals(true, false, "Element cannot be clicked");
				}
			} catch(ElementNotInteractableException ex) {
				System.out.println(ex);
				if (attempt == maxAttempt) {
					Assert.assertEquals(true, false, "Element cannot be clicked");
				}
			}
			attempt++;
		}
	}
	
	public static void selectOptionByVisibleText(WebElement element, String keysToSend) {
		if(!keysToSend.equals("")) {
			scrollIntoView(getWait().until(ExpectedConditions.visibilityOf(element)));
			Select select = new Select(element);
	        select.selectByVisibleText(keysToSend);
		}
	}
	
	public static void selectOptionByValue(WebElement element, String keysToSend) {
		if(!keysToSend.equals("")) {
			scrollIntoView(getWait().until(ExpectedConditions.visibilityOf(element)));
			Select select = new Select(element);
			select.selectByValue(keysToSend);
		}
	}
	
	public static void multipleSelect(WebElement element, String keysToSends) throws InterruptedException {
		int i=0;
		String[] keysToSend = keysToSends.split(";");
		WebElement selectUl;
		while(i < keysToSend.length) {
			scrollIntoView(getWait().until(ExpectedConditions.visibilityOf(element)));
			Use.Click(element);
			selectUl = getDriver().findElement(By.xpath("//li[contains(text(),'"+keysToSend[i]+"')]"));
			Use.Click(selectUl);
			i++;
		}
		//getActions().sendKeys(Keys.TAB).perform();
	}
	
	public static void checkBox(WebElement element, String keysToSend) throws InterruptedException {
		if(keysToSend.equals("yes") || keysToSend.equals("no")) {			
			scrollIntoView(getWait().until(ExpectedConditions.visibilityOf(element)));
			if ((element.isSelected()==true) && keysToSend.equals("no")) {
				Use.Click(element);
			} else if ((!element.isSelected()==true) && keysToSend.equals("yes")) {
				Use.Click(element);
			}
		}else {
			System.out.println("String parameter should yes or no");
		}
		//getActions().sendKeys(Keys.TAB).perform();
	}
	
	public static void scrollIntoView(WebElement element) {
		 JavascriptExecutor js = (JavascriptExecutor) getDriver();
		 js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
	}
}
