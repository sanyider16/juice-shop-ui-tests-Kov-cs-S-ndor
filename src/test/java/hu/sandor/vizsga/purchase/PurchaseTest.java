package hu.sandor.vizsga.purchase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import hu.sandor.vizsga.base.BaseTest;

public class PurchaseTest extends BaseTest {
	
	@Test
	void purchaseShouldBeSuccesfull() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("email")))
		         .sendKeys(email);
		
		driver.findElement(By.id("password"))
		.sendKeys(password);
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.id("loginButton"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("app-product")));
		
		assertTrue(driver.findElement(
				By.cssSelector("app-product")).isDisplayed(),
				"Bejelentkezés után meg kell jelennie egy terméknek");
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("app-product button.btn-basket"))).click();
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.tagName("body"), 
				"Placed Apple Juice (1000ml) into basket."));
		
		driver.get("https://demo.owasp-juice.shop/#/basket");
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.tagName("body"), "Apple Juice"));
		
		assertTrue(driver.findElement(
				By.tagName("body"))
				.getText().contains("Apple Juice"),
				"A kiválasztott terméknek szerepelni kell a kosárban");
		
		System.out.println("Az aktuális oldal:" + driver.getCurrentUrl());
		System.out.println(driver.findElement(By.tagName("body")).getText());
		System.out.println("Pénztár aktív: "
				+ driver.findElement(By.id("checkoutButton")).isEnabled());
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.id("checkoutButton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("button[aria-label='Add New Address']")))
		        .click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("input[name$='.fullName']")))
		         .sendKeys("Teszt Elek");
		
		driver.findElement(By.cssSelector("input[name$='.country']"))
				.sendKeys("Hungary");
		driver.findElement(By.cssSelector("input[name$='.mobileNum']"))
		.sendKeys("1234567890");
		
		driver.findElement(By.cssSelector("input[name$='.zipCode']"))
		.sendKeys("1111");
		
		driver.findElement(By.cssSelector("textarea[name$='.streetAddress']"))
		.sendKeys("Teszt utca 1");
		
		driver.findElement(By.cssSelector("input[name$='.city']"))
		.sendKeys("Budapest");
		
		driver.findElement(By.cssSelector("input[name$='.state']"))
		.sendKeys("Budapest");
		
        new WebDriverWait(driver, Duration.ofSeconds(20))
        .ignoring(ElementClickInterceptedException.class)
        .until(browser -> {
        var button = browser.findElement(By.id("submitButton"));
        
        if (!button.isDisplayed() ||!button.isEnabled()) {
        return false; 
        }
        button.click();
        return true;
        });
		wait.until(ExpectedConditions.urlContains("#/address/select"));
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.tagName("body"), "Teszt utca 1"));
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("label:has(input[type='radio']"))).click();
		
		new WebDriverWait(driver, Duration.ofSeconds(20))
		.ignoring(ElementClickInterceptedException.class)
		.until(browser -> {
		var nextButton = browser.findElement(
				By.xpath("//button[contains(.,'Continue')]"));
				
		if (!nextButton.isDisplayed() || !nextButton.isEnabled()) {
			return false;
		}
		
		nextButton.click();
		return true;
		});
		
		wait.until(ExpectedConditions.urlContains("#/delivery-method"));
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("label:has(input[type='radio']"))).click();
		
		
		new WebDriverWait(driver, Duration.ofSeconds(20))
		.ignoring(ElementClickInterceptedException.class)
		.until(browser -> {
		var nextButton = browser.findElement(
				By.xpath("//button[contains(.,'Continue')]"));
		
		if (!nextButton.isDisplayed() || !nextButton.isEnabled()) {
			return false;
		}
		
		nextButton.click();
		return true;
		});
		
		wait.until(ExpectedConditions.urlContains("#/payment/shop"));
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[normalize-space()='Add new card']"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("input[type='text'][required]")))
		         .sendKeys("Teszt Elek");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("input[type='number'][required]")))
		         .sendKeys("4111111111111111");
		
		var expiryMonth = new Select (
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector("select[required]"))));
			
			expiryMonth.selectByVisibleText("12");
			
			var expiryYear = new Select (
					driver.findElements(By.cssSelector("select[required]")).get(1));
					expiryYear.selectByVisibleText("2080");
		
					new WebDriverWait(driver, Duration.ofSeconds(20))
					.ignoring(ElementClickInterceptedException.class)
					.until(browser -> {
					var saveCardButton = browser.findElement(
							By.xpath("//button[contains(.,'Submit')]"));
					
					if (!saveCardButton.isDisplayed() || !saveCardButton.isEnabled()) {
						return false;
					}
					
					saveCardButton.click();
					return true;
					});
					

					wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.cssSelector("label:has(input[type='radio'])"))).click();

					new WebDriverWait(driver, Duration.ofSeconds(20))
					.ignoring(ElementClickInterceptedException.class)
					.until(browser -> {
					var continueButton = browser.findElement(
							By.xpath("//button[contains(.,'Continue')]"));
					
					if (!continueButton.isDisplayed() || !continueButton.isEnabled()) {
						return false;
					}
					
					continueButton.click();
					return true;
					});
					
					wait.until(ExpectedConditions.urlContains("/order-summary"));
			        
					new WebDriverWait(driver, Duration.ofSeconds(20))
					.ignoring(ElementClickInterceptedException.class)
					.until(browser -> {
					var orderButton = browser.findElement(
							By.xpath("//button[contains(.,'Place your order and pay')]"));
					
					if (!orderButton.isDisplayed() || !orderButton.isEnabled()) {
						return false;
					}
					
					orderButton.click();
					return true;
					});
					
					
					wait.until(ExpectedConditions.urlContains("/order-completion/"));
					
					wait.until(ExpectedConditions.textToBePresentInElementLocated(
							By.tagName("body"), "Thank you for your purchase!"));
					
					assertTrue(driver.findElement(By.tagName("body"))
							.getText().contains("Thank you for your purchase!"),
							"Sikeres rendelés után meg kell jelennie a visszaigazolásnak.");
					
					
					
					
		}
		
	}



