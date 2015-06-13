package io.github.devvratplus.theinternet.testcases;


import io.github.devvratplus.theinternet.core.HttpResponseCodes;
import io.github.devvratplus.theinternet.core.TestCase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyThatTheTestURLIsAccessible extends TestCase {
	
	final String url = getSeleniumProperty("url");
	
	@BeforeMethod
	public void setUp(){
		launchSelenium();
	}
	
	@AfterMethod
	public void tearDown(){
		quitSelenium();
	}

	@Test
	public void testVerifyThatTheTestURLIsAccessible() {
		if (!HttpResponseCodes.checkForResponseCode200(url)) {

			Assert.fail("Webpage is not accessible");
		} else {
			System.out.println("Webpage is accessible!");
		}
	}
}
