package hu.sandor.vizsga.login;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


import hu.sandor.vizsga.base.BaseTest;

public class LoginTest extends BaseTest {
	
	@Test
	void userShouldLoginSuccesfully() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("email")))
		.sendKeys(email);
		driver.findElement(By.id("password"))
		         .sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(
				By.id("loginButton"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("app-product")));
		wait.until(ExpectedConditions.elementToBeClickable(
				By.id("navbarAccount"))).click();
		WebElement logoutButton =
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("navbarLogoutButton")));
				
		assertTrue(
				logoutButton.isDisplayed(),
				"Sikeres belépés után a kilépés gombnak látszania kell"
				);
				
				
		
		
	}

}
