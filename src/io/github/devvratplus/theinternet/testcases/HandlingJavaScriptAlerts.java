package io.github.devvratplus.theinternet.testcases;

import io.github.devvratplus.theinternet.core.TestCase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandlingJavaScriptAlerts extends TestCase {

	@BeforeMethod
	public void setUp(){
		launchSelenium();
	}
	
	@AfterMethod
	public void tearDown(){
		quitSelenium();
	}
	
	@Test
	public void testJSAlert() {
		findElementByLinkTest("JavaScript Alerts").click();

		Assert.assertEquals(findElementByXpath("//*[@id='content']/div/h3")
				.getText(), "JavaScript Alerts");

		findElementByXpath("//button[@onclick='jsAlert()']").click();
		Alert jsAlert = selenium().switchTo().alert();
		checkPoint().assertEquals(jsAlert.getText(), "I am a JS Alert");
		jsAlert.accept();

		String resultText = findElementByXpath("//p[@id='result']").getText();

		checkPoint().assertEquals(resultText,
				"You successfuly clicked an alert");

		checkPoint().assertAll();
	}

	@Test
	public void testJSConfirm() {
		findElementByLinkTest("JavaScript Alerts").click();

		Assert.assertEquals(findElementByXpath("//*[@id='content']/div/h3")
				.getText(), "JavaScript Alerts");

		WebElement alertButton = findElementByXpath("//button[@onclick='jsConfirm()']");
		alertButton.click();

		Alert jsAlert = switchToAlert();
		checkPoint().assertEquals(jsAlert.getText(), "I am a JS Confirm");

		jsAlert.accept();
		String resultText = findElementByXpath("//p[@id='result']").getText();
		checkPoint().assertEquals(resultText, "You clicked: Ok");

		alertButton.click();
		jsAlert = switchToAlert();
		jsAlert.dismiss();
		resultText = findElementByXpath("//p[@id='result']").getText();
		checkPoint().assertEquals(resultText, "You clicked: Cancel");

		checkPoint().assertAll();

	}

	@Test
	public void testJSPrompt() {
		findElementByLinkTest("JavaScript Alerts").click();

		Assert.assertEquals(findElementByXpath("//*[@id='content']/div/h3")
				.getText(), "JavaScript Alerts");

		WebElement alertButton = findElementByXpath("//button[@onclick='jsPrompt()']");
		alertButton.click();

		Alert jsAlert = switchToAlert();
		checkPoint().assertEquals(jsAlert.getText(), "I am a JS prompt");

		// Just accept the alert and verify the result
		jsAlert.accept();
		String resultText = findElementByXpath("//p[@id='result']").getText();
		checkPoint().assertEquals(resultText, "You entered:");

		// Enter some test on the prompt and accept the alert and verify the
		// result
		String enterTextInPrompt = "Hello, it's me Devvrat!";
		alertButton.click();
		jsAlert.sendKeys(enterTextInPrompt);
		jsAlert.accept();
		resultText = findElementByXpath("//p[@id='result']").getText();
		checkPoint().assertEquals(resultText,
				"You entered: " + enterTextInPrompt);

		// dismiss the alert and verify the result
		alertButton.click();
		jsAlert.dismiss();
		resultText = findElementByXpath("//p[@id='result']").getText();
		checkPoint().assertEquals(resultText, "You entered: null");

		checkPoint().assertAll();
	}
}