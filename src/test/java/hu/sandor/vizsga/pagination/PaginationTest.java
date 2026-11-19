package hu.sandor.vizsga.pagination;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import hu.sandor.vizsga.base.BaseTest;

public class PaginationTest extends BaseTest{
	
	@Test
	void nextPageShouldShowDifferentProducts() {
		driver.get("https://demo.owasp-juice.shop/#/search");
		
		By product = By.cssSelector("app-product");
		
		By nextPageButton = By.cssSelector("button.mat-mdc-paginator-navigation-next");
		
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(product));
		
		wait.until(ExpectedConditions.refreshed(browser -> !browser.findElement(product).getText().isBlank()));
		
		String firstPageProduct = driver.findElement(product).getText();
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).ignoring(ElementClickInterceptedException.class)
		     .until(browser -> {
		    	 var button = browser.findElement(nextPageButton);
		    	 
		    	 if(!button.isDisplayed() || !button.isEnabled()) {
		    		 return false;
		    	 }
		    	 
		    	 button.click();
		    	 return true; });
		
		wait.until(ExpectedConditions.refreshed(browser -> {
			String currentProduct =
	       browser.findElement(product).getText();
			
			return !currentProduct.isBlank()
					&& !currentProduct.equals(firstPageProduct);
		}));
		    	 
		    String secondPageProduct = 
		    		driver.findElement(product).getText();
		    
		     assertNotEquals(			 
	                     firstPageProduct,
	                     secondPageProduct,
	                     "Lapozás után másik terméknek kell elsőként szerepelni.");

			}

	}


