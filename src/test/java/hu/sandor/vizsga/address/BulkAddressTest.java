package hu.sandor.vizsga.address;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hu.sandor.vizsga.base.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import javax.sql.RowSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BulkAddressTest extends BaseTest {
	
	@Test
	void addressesFromCsvShouldBeSaved() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("email"))).sendKeys(email);
		
		driver.findElement(By.id("password"))
		       .sendKeys(password);
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.id("loginButton"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("app-product")));
		
		var input = getClass().getResourceAsStream("/addresses.csv");
		
		assertNotNull(input, "Az addresses.csv fájl nem található");
		
		List<String> rows;
		try (var reader = new BufferedReader(
		new InputStreamReader(input, StandardCharsets.UTF_8))) {
			
			rows = reader.lines()
					.skip(1)
					.filter(line -> !line.isBlank())
					.toList();
		}
		
		assertFalse(rows.isEmpty(), " A CSV me, tartalmaz címadatokat");
		String baseUrl = driver.getCurrentUrl().split("#", 2)[0];
		for (String row : rows) {
			String[] data = row.split(",", -1);
			assertEquals(7, data.length,
			   "A CSV minden sorában 7 adatnak kell lennie");
			
			driver.get(baseUrl + "#/address/create");
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector("input[name$='.fullName']")))
			.sendKeys(data[0]);
			
			driver.findElement(By.cssSelector("input[name$='.country']"))
			.sendKeys(data[1]);
			
			driver.findElement(By.cssSelector("input[name$='.mobileNum']"))
			.sendKeys(data[2]);
			
			driver.findElement(By.cssSelector("input[name$='.zipCode']"))
			.sendKeys(data[3]);
			
			driver.findElement(By.cssSelector("textarea[name$='.streetAddress']"))
			.sendKeys(data[4]);
			
			driver.findElement(By.cssSelector("input[name$='.city']"))
			.sendKeys(data[5]);
			
			driver.findElement(By.cssSelector("input[name$='.state']"))
			.sendKeys(data[6]);
			
			new WebDriverWait(driver, Duration.ofSeconds(20))
			.ignoring(ElementClickInterceptedException.class)
			.until(browser -> {
			var Button = browser.findElement(
					By.id("submitButton"));
			
			if (!Button.isDisplayed() || !Button.isEnabled()) {
				return false;
			}
			
			Button.click();
			return true;
			});
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(
					By.id("submitButton")));
			
			driver.get(baseUrl + "#/address/saved");
			
			wait.until(ExpectedConditions.textToBePresentInElementLocated(
					By.tagName("body"), data[4]));
		}
		
		By deleteButtons = By.cssSelector(".mat-column-Remove button");
		
		wait.until(ExpectedConditions.numberOfElementsToBe(deleteButtons, rows.size()));
		
		assertEquals(rows.size(), driver.findElements(deleteButtons).size(),
				"Minden CSV-sorhoz egy mentett címnek kell tartoznia");
		
		}
		
		
		
		
	}


