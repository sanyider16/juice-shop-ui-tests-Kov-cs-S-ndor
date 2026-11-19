package hu.sandor.vizsga.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import hu.sandor.vizsga.base.BaseTest;

public class HomePageTest extends BaseTest {
	
	
	
	@Test
	void productsShouldAppearOnHomePage() {
		driver.get("https://demo.owasp-juice.shop");
		
		WebDriverWait wait =
				new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(browser ->
		     !browser.findElements(By.cssSelector("app-product")).isEmpty());
		assertFalse(
				driver.findElements(By.cssSelector("app-product")).isEmpty(),
				"A termék listának tartalmaznia kell a termékeket."
				);
				
	}



   
    
    
    	}
    