package io.github.devvratplus.theinternet.testcases;

import io.github.devvratplus.theinternet.core.TestCase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandlingUploads extends TestCase {

	@BeforeMethod
	public void setUp() {
		launchSelenium();
		
	}

	@AfterMethod
	public void tearDown() {
		quitSelenium();
	}

	@Test
	public void handlingUploadsTest() {
		clickOnLink("File Upload");
		String fileLocation = System.getProperty("user.dir")
				+ "\\screenshots\\";
		String fileName = "ScreenshotsAreSavedHereByDefault.jpg";

		// Uploading the file
		selenium().findElement(By.xpath("//input[@id='file-upload']"))
				.sendKeys(fileLocation + fileName);

		selenium().findElement(By.xpath("//input[@id='file-submit']")).click();

		String uploadedFileName = selenium().findElement(
				By.xpath("//*[@id='uploaded-files']")).getText();

		Assert.assertEquals(fileName, uploadedFileName, "Upload Failed : "
				+ uploadedFileName);

	}
}
