package hu.sandor.vizsga.base;

import java.time.Duration;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.PageLoadStrategy;


public class BaseTest {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected String email;
	protected String password;
	
	protected ChromeOptions createChromeOptions() {

	    ChromeOptions options = new ChromeOptions();

	    if (Boolean.getBoolean("headless")) {
	        options.addArguments("--headless=new");
	        options.addArguments("--window-size=1920,1080");
	    }

	    return options;
	}

	@BeforeEach
	protected void setUp() {

	    driver = new ChromeDriver(createChromeOptions());
			
			driver.manage().window().maximize();
			
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			
			email = "vizsga." + UUID.randomUUID() + "@example.com";
			password = "VizsgaTeszt2026!";
			
			driver.get("https://demo.owasp-juice.shop/#/register");
			
			By WelcomeButton =
					By.cssSelector("button[aria-label='Close Welcome Banner']");
			
			wait.until(ExpectedConditions.elementToBeClickable(WelcomeButton))
			       .click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(
					WelcomeButton));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(
					By.cssSelector("mat-dialog-container")));
			
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".cc-dismiss"))).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.cssSelector(".cc-window")));
			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.id("emailControl")))
			          .sendKeys(email);
			
			driver.findElement(By.id("passwordControl"))
		    .sendKeys(password);
			
			driver.findElement(By.id("repeatPasswordControl"))
		    .sendKeys(password);
			
			new WebDriverWait(driver, Duration.ofSeconds(20))
			      .ignoring(ElementClickInterceptedException.class)
			      .until(browser -> {
			    	  browser.findElement(By.id("securityQuestionControl")).click();
			    	  return true;
			      });
			
		
			wait.until(ExpectedConditions.elementToBeClickable(
					By.cssSelector("mat-option"))).click();
			
	        driver.findElement(By.id("securityAnswerControl"))
	                .sendKeys("TesztValasz");
	        wait.until(ExpectedConditions.elementToBeClickable(
	        		By.id("registerButton"))).click();
	        wait.until((ExpectedConditions.urlContains("#/login")));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	        		By.id("email")));
			
	          			  }
	
			
	        
			
		
		@AfterEach
		void tearDown() {
	    	
	    	if (driver !=null) {
	    		driver.quit();
	    		
	    		
		}
	}

}
