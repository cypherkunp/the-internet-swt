package io.github.devvratplus.theinternet.testcases;
import io.github.devvratplus.theinternet.core.TestCase;
import io.github.devvratplus.theinternet.core.TestUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HandlingIFrames extends TestCase{

	@BeforeMethod
	public void setUp(){
		launchSelenium();
	}
	
	@AfterMethod
	public void tearDown(){
		quitSelenium();
	}
	
	@Test
	public void testHandlingIFrames(){
		
		// Click Frames link
		findElementByLinkTest("Frames").click();
		
		// Click iFrame link
		findElementByLinkTest("iFrame").click();
		
		switchToFrameWithNameORId("mce_0_ifr");
	
		//Get the text displayed in the text box
		WebElement editor = findElementById("tinymce");
		String defaultText = "Your content goes here.";
		checkPoint().assertEquals(editor.getText(), defaultText);
		
		// Clear text
		editor.clear();
		checkPoint().assertEquals(editor.getText(), "");
		
		// Add your text
		String addYourText = "Howdy editor, it's me Devvrat!";
		editor.sendKeys(addYourText);
		checkPoint().assertEquals(editor.getText(), addYourText);
		
		checkPoint().assertAll();
	}
	
	@Test
	public void testNestedIFrames(){

		// Click Frames link
		findElementByLinkTest("Frames").click();
		
		// Click iFrame link
		findElementByLinkTest("Nested Frames").click();
		
		// Switch to top frame which has left, right and middle frame nested
		// inside it
		switchToFrameWithNameORId("frame-top");
		System.out.println("Switched to top frame.");
		
		switchToFrameWithNameORId("frame-left");
		String leftFrametext = findElementByXpath("html/body").getText();
		System.out.println("Switched to the " + leftFrametext + " frame");
		checkPoint().assertEquals(leftFrametext, "LEFT");
		
		//switch to the main page
		selenium().switchTo().defaultContent();
		
		switchToFrameWithNameORId("frame-top");
		switchToFrameWithNameORId("frame-middle");
		String middleFrametext = findElementByXpath("html/body").getText();
		System.out.println("Switched to the " + middleFrametext + " frame");
		checkPoint().assertEquals(middleFrametext, "MIDDLE");

		//switch to the main page
		selenium().switchTo().defaultContent();
		
		switchToFrameWithNameORId("frame-top");
		switchToFrameWithNameORId("frame-right");
		String rightFrametext = findElementByXpath("html/body").getText();
		System.out.println("Switched to the " + rightFrametext + " frame");
		checkPoint().assertEquals(rightFrametext, "RIGHT");

		//switch to the main page
		selenium().switchTo().defaultContent();
		
		// Switch to down frame
		switchToFrameWithNameORId("frame-bottom");
		String downFrametext = findElementByXpath("html/body").getText();
		System.out.println("Switched to the " + downFrametext + " frame");
		checkPoint().assertEquals(downFrametext, "BOTTOM");

		checkPoint().assertAll();
	}
	
}
