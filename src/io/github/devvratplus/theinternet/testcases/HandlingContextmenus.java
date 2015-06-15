package io.github.devvratplus.theinternet.testcases;

import io.github.devvratplus.theinternet.core.TestCase;

import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Note as if now Context Menus are only supported in Firefox
 * 
 * @author Devvrat Shukla
 *
 */
public class HandlingContextmenus extends TestCase {

	@BeforeMethod
	public void setUp() {
		FirefoxProfile fprofile = new FirefoxProfile();
		fprofile.setAcceptUntrustedCertificates(true);
		launchSelenium(fprofile);
	}

	@AfterMethod
	public void tearDown() {
		quitSelenium();
	}

	@Test
	public void testHandlingContextmenus() {
		findElementByLinkTest("Context Menu").click();

		Actions doAction = doAction();
		doAction.contextClick(findElementById("hot-spot")).sendKeys(Keys.DOWN)
				.sendKeys(Keys.RETURN).build().perform();

		checkPoint().assertEquals(findElementByXpath("//*[@id='content']/h2"),
				"Available Examples");
	}

}
