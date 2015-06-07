package io.github.devvratplus.theinternet.testcases;

import io.github.devvratplus.theinternet.core.TestCase;
import io.github.devvratplus.theinternet.core.TestUtils;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandlingDownloads extends TestCase {
	
	private static File tempFolder;
	private static FirefoxProfile firefoxProfile;
	private static String fileToBeDownloaded;

	@BeforeClass
	public void setUpDataSet() throws IOException {
		
		tempFolder = TestUtils.createTempDirectory();
		System.out.println("Using temperarory directory > " + tempFolder);

		firefoxProfile = (new ProfilesIni()).getProfile("seleniumuser");
		firefoxProfile.setPreference("browser.download.dir",
				tempFolder.getAbsolutePath());
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("pdfjs.disabled", true);

	}

	@AfterClass
	public void tearDownDataSet() {
		tempFolder.delete();
		if (!tempFolder.exists()) {
			System.out.println("Deleted temperarory directory > " + tempFolder);
		}
	}

	@BeforeMethod
	public void setUp() {
		launchSelenium(firefoxProfile);
	}

	@AfterMethod
	public void tearDown() {
		quitSelenium();
	}

	@Test
	public void testHandlingDownloads() throws IOException {
		fileToBeDownloaded = "avatar.jpg";

		// Click on the File Download Link
		findElementByLinkTest("File Download").click();
		// Click on the file to be downloaded
		findElementByLinkTest(fileToBeDownloaded).click();

		File avatarjpeg = new File(tempFolder.toString() + "\\"
				+ fileToBeDownloaded);
		Assert.assertTrue(avatarjpeg.exists(), "Download failed");

	}

}
