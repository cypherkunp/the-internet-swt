package io.github.devvratplus.theinternet.testcases;

import io.github.devvratplus.theinternet.core.TestCase;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Devvrat Shukla
 *
 */
public class HandlingDisappearingElements extends TestCase {

	@BeforeMethod
	public void setUp() {
		launchSelenium();
	}

	@AfterMethod
	public void tearDown() {
		quitSelenium();
	}

	@Test
	public void testHandlingDisappearingElements() {
		findElementByLinkTest("Disappearing Elements").click();

		boolean reloadCheck = reloadPageUntilAnElementIsVisibleOnThePage(
				By.linkText("Gallery"), 7);

		if (reloadCheck) {
			findElementByLinkTest("Gallery").click();
			checkPoint().assertTrue(getCurrentURL().contains("gallery"),
					"Routed to incorrect URL");
		} else {
			checkPoint().fail("Unable to locate the Gallery Link");
		}

		checkPoint().assertAll();
	}
}
