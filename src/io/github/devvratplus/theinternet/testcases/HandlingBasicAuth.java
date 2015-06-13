package io.github.devvratplus.theinternet.testcases;

import io.github.devvratplus.theinternet.core.TestCase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandlingBasicAuth extends TestCase{
	
	@BeforeMethod
	public void setUp() {
		/*
		 * In the test we're loading the page by passing in the username and
		 * password in the URL (e.g., http://admin:admin@). Once it loads we
		 * grab text from the page to make sure we ended up in the right place.
		 */
		launchSelenium("http://admin:admin@localhost:9292/basic_auth");
	}

	@AfterMethod
	public void tearDown(){
		quitSelenium();
	}
	
	@Test
	public void testHandlingBasicAuth(){
		
		Assert.assertEquals(
				"Congratulations! You must have the proper credentials.",
				findElementByXpath("//*[@id='content']/div/p").getText());
	}
}
