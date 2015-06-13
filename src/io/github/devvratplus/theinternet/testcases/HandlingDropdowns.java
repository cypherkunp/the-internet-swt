package io.github.devvratplus.theinternet.testcases;

import io.github.devvratplus.theinternet.core.TestCase;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandlingDropdowns extends TestCase{

	List<String> expectedOptionsName = null;

	@BeforeClass
	public void dataSet() {
		expectedOptionsName = new LinkedList<String>();
		expectedOptionsName.add("Please select an option");
		expectedOptionsName.add("Option 1");
		expectedOptionsName.add("Option 2");
	}

	@BeforeMethod
	public void setUp() {
		launchSelenium();
	}

	@AfterMethod
	public void tearDown() {
		quitSelenium();
	}

	@Test
	public void testHandlingDropdowns() {

		findElementByLinkTest("Dropdown").click();

		Select options = new Select(findElementById("dropdown"));
		System.out.println("Is it a multiple select ? " + options.isMultiple());
				
		List<WebElement> optionsList = options.getOptions();
		List<String> actualOptionsName = new LinkedList<String>();

		System.out.println("Select from options----------- ");
		for (WebElement element : optionsList) {
			System.out.println(element.getText());
			actualOptionsName.add(element.getText());
		}
		System.out.println("-------------------------------");
		checkPoint().assertEquals(actualOptionsName, expectedOptionsName);

		String selectOption1 = "Option 1";
		options.selectByVisibleText(selectOption1);
		checkPoint().assertEquals(options.getFirstSelectedOption().getText(),
				selectOption1);

		checkPoint().assertAll();
	}
}
