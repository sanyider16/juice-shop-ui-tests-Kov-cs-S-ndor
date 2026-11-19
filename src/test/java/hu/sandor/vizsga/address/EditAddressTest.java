package hu.sandor.vizsga.address;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hu.sandor.vizsga.base.BaseTest;

public class EditAddressTest extends BaseTest {
	
	@Test
	void savedAddressShouldBeUpdated() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("email")))
		.sendKeys(email);
		
		driver.findElement(By.id("password"))
		        .sendKeys(password);
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.id("loginButton"))).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("app-product")));
		
		driver.get("https://demo.owasp-juice.shop/#/address/create");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("input[name$='.fullName']")))
		.sendKeys("Teszt Elek");
		
		driver.findElement(By.cssSelector("input[name$='.country']"))
		    .sendKeys("Hungary");
		
		driver.findElement(By.cssSelector("input[name$='.mobileNum']"))
	    .sendKeys("123456789");
		
		driver.findElement(By.cssSelector("input[name$='.zipCode']"))
	    .sendKeys("1111");
		
		driver.findElement(By.cssSelector("textarea[name$='.streetAddress']"))
	    .sendKeys("Teszt utca 1.");
		
		driver.findElement(By.cssSelector("input[name$='.city']"))
	    .sendKeys("Budapest");
	
		driver.findElement(By.cssSelector("input[name$='.state']"))
	    .sendKeys("Budapest");
		
		new WebDriverWait(driver, Duration.ofSeconds(20))
		.ignoring(ElementClickInterceptedException.class)
		.until(browser -> {
			var button = browser.findElement(By.id("submitButton"));
			
			if (!button.isDisplayed() || !button.isEnabled()) {
				return false;
			}
			
			button.click();
			return true;
		});
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.id("submitButton")));
		
		driver.get("https://demo.owasp-juice.shop/#/address/saved");
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.tagName("body"), "Teszt utca 1."));
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".mat-column-Edit button"))).click();
		
		
		WebElement addressField = 
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.cssSelector("textarea[name$='.streetAddress']")));
		
		new WebDriverWait(driver, Duration.ofSeconds(20))
		.ignoring(ElementClickInterceptedException.class)
		.until(browser -> {
			addressField.click();
			return true;
		});
		
		addressField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		addressField.sendKeys(Keys.BACK_SPACE);
		addressField.sendKeys("Teszt utca 2");
		
		     new WebDriverWait(driver, Duration.ofSeconds(20))
		     .ignoring(ElementClickInterceptedException.class)
		     .until(browser -> {
		    	 var button = browser.findElement(By.id("submitButton"));
		    	 
		    	 if (!button.isDisplayed() || !button.isEnabled()) {
		    		 return false;
		    	 }
		    	 
		    	 button.click();
		    	 return true;
		     });
		     
		     wait.until(ExpectedConditions.invisibilityOfElementLocated(
		    		 By.id("submitButton")));
     
		     driver.get("https://demo.owasp-juice.shop/#/address/saved");
		     
		     wait.until(ExpectedConditions.textToBePresentInElementLocated(
		    		 By.tagName("body"), "Teszt utca 2"));
		     
		     String pageText = driver.findElement(By.tagName("body")).getText();
		     
		    	 
		    	 
		assertTrue(
				pageText.contains("Teszt utca 2"),
				"A módosított címnek meg kell jelennie");
				
				assertFalse(
						pageText.contains("Teszt utca 1."),
						"A régi címnek már nem szabad megjelennie");
				assertTrue(
						pageText.contains("Budapest"),
						"A mentett városnak meg kell jelennie");
			}
	}


