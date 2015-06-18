package io.github.devvratplus.theinternet.testcases;
import static org.testng.Assert.assertEquals;
import io.github.devvratplus.theinternet.core.TestCase;

import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandlingKeyPresses extends TestCase {
	
	@BeforeMethod
	public void setUp(){
		launchSelenium();
	}
	
	@AfterMethod
	public void tearDown(){
		//quitSelenium();
	}
	
	@Test
	public void testHandlingKeyPresses() {
		findElementByLinkTest("Key Presses").click();

		assertEquals(
				findElementByXpath(".//*[@id='content']/div/h3").getText(),
				"Key Presses");

		findElementByXpath("html/body").sendKeys(Keys.ALT);

		assertEquals(findElementById("result").getText(), "You entered: ALT");
	}
}
