package hu.sandor.vizsga.logout;

import org.junit.jupiter.api.Test;

import hu.sandor.vizsga.base.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LogoutTest extends BaseTest {
	
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
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.id("navbarLogoutButton")));
		wait.until(ExpectedConditions.elementToBeClickable(
				By.id("navbarLogoutButton"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(
				By.id("navbarAccount"))).click();
		

		WebElement loginButton =
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.id("navbarLoginButton")));
	   assertTrue(
			   loginButton.isDisplayed(),
			   "Kijelentkezés után a Login gombnak látszania kell"
			   
			   );
		
		
}
}