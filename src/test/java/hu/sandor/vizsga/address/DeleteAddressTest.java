package hu.sandor.vizsga.address;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hu.sandor.vizsga.base.BaseTest;

public class DeleteAddressTest extends BaseTest {
	
	@Test
	void savedAddressShouldBeDeleted () {
		
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
		
		By deleteButton = By.cssSelector(".mat-column-Remove button");
		
		wait.until(ExpectedConditions.elementToBeClickable(
				deleteButton)).click();
		
		wait.until(ExpectedConditions.numberOfElementsToBe(
				deleteButton, 0));
		
		     assertTrue(driver.findElements(deleteButton).isEmpty(),
		    		 "A törlés után a címsornak el kell tnnie");
	}

}
