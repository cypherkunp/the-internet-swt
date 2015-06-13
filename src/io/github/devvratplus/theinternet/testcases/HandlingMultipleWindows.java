package io.github.devvratplus.theinternet.testcases;

import io.github.devvratplus.theinternet.core.TestCase;

import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandlingMultipleWindows extends TestCase {

	@BeforeMethod
	public void setUp() {
		launchSelenium();
	}

	@AfterMethod
	public void tearDown() {
		quitSelenium();
	}

	@Test
	public void testHandlingMultipleWindows() {
		findElementByLinkTest("Multiple Windows").click();
		System.out.println("On page " + getTitle());

		// Click on the link to launch the new window
		findElementByLinkTest("Click Here").click();
		System.out.println("On page " + getTitle());

		Set<String> windowHandles = getWindowHandles();
		Iterator<String> itr = windowHandles.iterator();

		String firstWindow = itr.next();
		String secondWindows = itr.next();

		//Shift the divers focus to the second window
		switchToWindow(secondWindows);
		System.out.println("On page " + getTitle());

		checkPoint().assertEquals(getTitle(), "New Window");

		closeCurrentWindow();

		// no on getting title will through
		// System.out.println(selenium().getTitle());
		// org.openqa.selenium.NoSuchWindowException:

		// Switch to the first window since the driver is still
		// pointing to the closed window
		switchToWindow(firstWindow);
		System.out.println("Switch to page " + getTitle());
		checkPoint().assertEquals(getTitle(), "The Internet");

		checkPoint().assertAll();
	}

}
