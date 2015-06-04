package io.github.devvratplus.theinternet.testcases;

import io.github.devvratplus.theinternet.core.TestCase;
import io.github.devvratplus.theinternet.core.TestUtils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandlingDownloads extends TestCase {
	
	File tempFolder;
	FirefoxProfile firefoxProfile;

	@BeforeMethod
	public void setUp() throws IOException {
		tempFolder = TestUtils.createTempDirectory();
		System.out.println("Created temperarory directory > " + tempFolder);

		firefoxProfile = (new ProfilesIni()).getProfile("seleniumuser");
		firefoxProfile.setPreference("browser.download.dir",
				tempFolder.getAbsolutePath());
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("pdfjs.disabled", true);

		launchSelenium(firefoxProfile);
	}

	@AfterMethod
	public void tearDown() {
		tempFolder.delete();
		if (!tempFolder.exists()) {
			System.out.println("Deleted temperarory directory > " + tempFolder);
		}

		quitSelenium();
	}

	@Test
	public void testHandlingDownloads() throws IOException {

		clickOnLink("File Download");
		String downloadFileName = "avatar.jpg";
		selenium().findElement(By.linkText(downloadFileName)).click();

		File avatarjpeg = new File(tempFolder.toString() + "\\"
				+ downloadFileName);
		Assert.assertTrue(avatarjpeg.exists(), "Download failed");

	}
	
}
