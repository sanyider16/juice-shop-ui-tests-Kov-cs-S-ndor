package hu.sandor.vizsga.privacy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import hu.sandor.vizsga.base.BaseTest;

public class PrivacyPolicyTest extends BaseTest{
	
	@Test
	
	void privacyPolicyShouldBeDisplayed() {
		
		driver.get("https://demo.owasp-juice.shop/#/privacy-security/privacy-policy");
		
		WebElement policy = wait.until(ExpectedConditions.visibilityOfElementLocated(
				   By.cssSelector("app-privacy-policy")));
		
		wait.until(browser -> policy.getText() .toLowerCase().contains("data"));
		
		String policyText = policy.getText();
		
		assertTrue(policyText.contains("Privacy Policy"),
				"Az adatvédelmi tájékoztató címének meg kell jelennie.");
		
		assertTrue(policyText.toLowerCase().contains("data"),
				"A tájékoztató adatkezeléséről szóló szövegének meg kell jelennie.");
	}

}
