package hu.sandor.vizsga.export;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import hu.sandor.vizsga.base.BaseTest;

public class DataExportTest extends BaseTest {
	
	@Override
	protected ChromeOptions createChromeOptions() {
		
		ChromeOptions options = super.createChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		return options;
	
	}
	
	@Test
	void accountDataShouldBeExported() throws Exception{
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("email"))).sendKeys(email);
		
		driver.findElement(By.id("password"))
		.sendKeys(password);
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.id("loginButton"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.id("navbarAccount"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("navbarLogoutButton")));
		
		driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.cssSelector(".cdk-overlay-backdrop")));
		
		String baseUrl = driver.getCurrentUrl().split("#", 2)[0];
		
		driver.get(baseUrl + "#/privacy-security/data-export");
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.tagName("body"), "Request Data Export"));
		
		var originalWindows = driver.getWindowHandles();
		

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//label[normalize-space()='JSON']"))).click();
		

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(.,'Request')]"))).click();
		
		wait.until(browser ->
		     browser.getWindowHandles().size() > originalWindows.size());
		
		for (String window : driver.getWindowHandles()) {
			if (!originalWindows.contains(window)) {
			driver.switchTo().window(window);
			break;
		}
		
		}
	

	wait.until(ExpectedConditions.textToBePresentInElementLocated(
			By.tagName("body"), email));
	
	String exportedData = driver.findElement(By.tagName("body"))
			.getText();
	
	assertTrue(exportedData.contains(email),
	"Az exportnak tartalmaznia kell a felhasználó email címét");
	
	Path exportFolder = Path.of("target", "exports");
	Files.createDirectories(exportFolder);
	
	Path exportFile = Files.createTempFile(exportFolder, "account-export-", ".json");
	
	Files.writeString(exportFile, exportedData, StandardCharsets.UTF_8);
	
	String savedData = Files.readString(exportFile, StandardCharsets.UTF_8);
	
	assertEquals(exportedData, savedData,
			"A mentett fjl tartalma egyezzen meg az exportált adatokkal");
	
	System.out.println("Export helye: " + exportFile.toAbsolutePath());
		}
		
		
	}


