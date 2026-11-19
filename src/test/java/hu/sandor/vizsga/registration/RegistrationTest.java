package hu.sandor.vizsga.registration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.module.Browser;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hu.sandor.vizsga.base.BaseTest;

public class RegistrationTest extends BaseTest{
	
	@Test
	void userShouldRegisterSuccessfully() {
		
		WebDriverWait wait =
				new WebDriverWait(driver, Duration.ofSeconds(20));
		
		String email =
				"vizsga." + UUID.randomUUID() + "@example.com";
		String password = "VizsgaTeszt2026!";
		driver.get("https://demo.owasp-juice.shop/#/register");
				
			
		
	
		
		driver.findElement(By.id("emailControl"))
		    .sendKeys(email);
		

		driver.findElement(By.id("passwordControl"))
		    .sendKeys(password);
		

		driver.findElement(By.id("repeatPasswordControl"))
		    .sendKeys(password);
		
		new WebDriverWait(driver, Duration.ofSeconds(20))
        .ignoring(ElementClickInterceptedException.class)
        .until(browser -> {

            var options = browser.findElements(
                    By.cssSelector("mat-option"));

            for (var option : options) {
                if (option.isDisplayed() && option.isEnabled()) {
                    option.click();
                    return true;
                }
            }

            var question = browser.findElement(
                    By.id("securityQuestionControl"));

            if (!"true".equals(question.getAttribute("aria-expanded"))) {
                question.click();
            }

            return false;
        });
		driver.findElement(By.id("securityAnswerControl"))
				.sendKeys("TesztValasz");
		new WebDriverWait(driver, Duration.ofSeconds(20))
                 .ignoring(ElementClickInterceptedException.class)
                 .until(Browser -> {
                	 Browser.findElement(By.id("registerButton")).click();
                	 return true;
                 });
		
		wait.until(ExpectedConditions.urlContains("#/login"));
		
		assertTrue(
				driver.getCurrentUrl().contains("#/login"),
				"Regisztráció után a bejelentkezés oldalnak kell megjelennie.");

}
}