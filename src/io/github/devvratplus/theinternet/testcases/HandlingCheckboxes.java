package io.github.devvratplus.theinternet.testcases;

import io.github.devvratplus.theinternet.core.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class HandlingCheckboxes extends TestCase{

	@BeforeMethod
	public void setUp(){
		launchSelenium();
	}
	
	@AfterMethod
	public void tearDown(){
		quitSelenium();
	}
	
	@Test
	public void checkTheUncheckedboxTest() {

		findElementByLinkTest("Checkboxes").click();;

		// this check box is unchecked by default
		WebElement checkbox1 = findElementByXpath("//*[@id='checkboxes']/input[1]");

		// verifying that it's unchecked
		if (checkbox1.getAttribute("checked") == null) {
			checkbox1.click();
		} else {
			Assert.assertTrue(false, "The checkbox is already checked");
		}

		Assert.assertTrue(checkbox1.getAttribute("checked") != null,
				"Failed to check the checkbox");
	}
	
	@Test
	public void uncheckThecheckedboxTest(){
		
		findElementByLinkTest("Checkboxes").click();

		// this check box is unchecked by default
		WebElement checkbox2 = findElementByXpath("//*[@id='checkboxes']/input[2]");

		// verifying that it's checked
		if (checkbox2.getAttribute("checked") == "") {
			checkbox2.click();
		} else {
				System.out.println("Indidsdasdasd");
		}

		Assert.assertTrue(checkbox2.getAttribute("checked") == "",
				"Failed to uncheck the checkbox2");
	}
	
	@Test
	public void verifyLablesofTheCheckboxes(){
		findElementByLinkTest("Checkboxes").click();
		
		WebElement checkbox1 = findElementByXpath("//*[@id='checkboxes']/input[1]");
		WebElement checkbox2 = findElementByXpath("//*[@id='checkboxes']/input[2]");
		
		String checkBoxLabel1 = checkbox1.getText();
		String checkBoxLabel2 = checkbox2.getText();
		
		Assert.assertEquals(checkBoxLabel1, "checkbox 1", checkBoxLabel1 );
		Assert.assertEquals(checkBoxLabel2, "checkbox 2", checkBoxLabel2);
	}
}
