package io.github.devvratplus.theinternet.core;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

/**
 * TestCase class that every test must extend.
 * 
 * @author Devvrat Shukla
 * 
 */
public class TestCase extends TestBase {

	private WebDriver driver = null;
	private SoftAssert softAssert = null;
	
	@BeforeMethod
	public void initializeSoftAssert(){
		softAssert = new SoftAssert();
	}
	
	private WebDriver initializeSelenium() {

		// Setting the operating system specific system-properties
		switch (getSeleniumProperty("os")) {

		case "MacOS":
			System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_MACOS);
			break;

		case "Windows":
			System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_WINDOWS);
			break;

		case "Linux32":
			System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LINUX32);
			break;

		case "Linux64":
			System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LINUX64);
			break;

		default:
			System.out
					.println("Invalid response. Please choose the correct operating system.");
		}

		// Setting up the driver specific driver
		switch (getSeleniumProperty("browser")) {

		case "Firefox":
			if (getSeleniumProperty("firefoxProfile") != "default") {
				if (getSeleniumProperty("acceptUntrustedCertificates") == "true") {
					FirefoxProfile fProfile = (new ProfilesIni()
							.getProfile(getSeleniumProperty("firefoxProfile")));
					// this will accept an certificate errors on page in firefox
					fProfile.setAcceptUntrustedCertificates(true);
					driver = new FirefoxDriver(fProfile);
				} else {
					FirefoxProfile fProfile = (new ProfilesIni()
							.getProfile(getSeleniumProperty("firefoxProfile")));
					driver = new FirefoxDriver(fProfile);
				}
			} else {
				driver = new FirefoxDriver();
			}
			break;

		case "Safari":
			driver = new SafariDriver();
			break;

		case "Chrome":
			driver = new ChromeDriver();
			break;
		case "PhantomJS":
			System.setProperty("phantomjs.binary.path", PHANTOMJSDRIVER);
			driver = new PhantomJSDriver();
			break;

		default:
			System.out
					.println("Invalid Browser selected, Tests will be run on firefox by default.");
			driver = new FirefoxDriver();

		}

		if (!getSeleniumProperty("implicitTimeOut").equals("")) {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}

		return driver;
	}

	/**
	 * 
	 * Initializes <b>Selenium WebDriver</b> object using the values specified
	 * in the selenium.properties file
	 */
	protected void launchSelenium() {
		driver = initializeSelenium();
		driver.get(getSeleniumProperty("url"));
	}

	/**
	 * Initializes the driver with the FirefoxDriver instance with the
	 * preferences set for the firefox profile
	 */
	protected void launchSelenium(FirefoxProfile firefoxProfile) {
		driver = new FirefoxDriver(firefoxProfile);
		driver.get(getSeleniumProperty("url"));
	}

	/**
	 * Quits this driver, closing every associated window.
	 */
	protected void quitSelenium() {
		driver.quit();
	}
	
	/**
	 * Close the current window, quitting the browser if it's the last window
	 * currently open.
	 */
	protected void closeCurrentWindow() {
		driver.close();
	}

	/**
	 * gets you the driver instance
	 * @return WebDriver
	 */
	protected WebDriver selenium() {
		return driver;
	}
	
	/**
	 * Finds the WebElement via linkText
	 * 
	 * @param linkText 
	 * @return WebElement
	 */
	protected WebElement findElementByLinkTest(String linkText) {

		return selenium().findElement(By.linkText(linkText));
	}

	/**
	 * Finds the WebElement via id attribute
	 * 
	 * @param id
	 * @return WebElement
	 */
	protected WebElement findElementById(String id) {

		return selenium().findElement(By.id(id));
	}
	
	/**
	 * Finds the WebElement via className
	 * 
	 * @param className
	 * @return WebElement
	 */
	protected WebElement findElementByClassName(String className) {

		return selenium().findElement(By.className(className));
	}
	
	/**
	 * Finds the WebElement via name attribute
	 * 
	 * @param name
	 * @return WebElement
	 */
	protected WebElement findElementByName(String name) {

		return selenium().findElement(By.name(name));
	}
	
	/**
	 *  Finds the WebElement via xpath expression
	 *  
	 * @param xpathExpression
	 * @return WebElement
	 */
	protected WebElement findElementByXpath(String xpathExpression) {

		return selenium().findElement(By.xpath(xpathExpression));
	}
	
	/**
	 * Finds the WebElement via cssSelector
	 * 
	 * @param selector
	 * @return WebElement
	 */
	protected WebElement findElementByCSSSelector(String selector) {

		return selenium().findElement(By.cssSelector(selector));
	}

	/**
	 * Select a frame by its name or ID. Frames located by matching name
	 * attributes are always given precedence over those matched by ID.
	 * 
	 * @param nameOrId
	 *            the name of the frame window, the id of the &lt;frame&gt; or
	 *            &lt;iframe&gt; element, or the (zero-based) index
	 * @return This driver focused on the given frame
	 * @throws NoSuchFrameException
	 *             If the frame cannot be found
	 */
	protected WebDriver switchToFrameWithNameORId(String nameOrId) {
		return selenium().switchTo().frame(nameOrId);
	}

	/**
	 * Takes screenshot and saves it inside screenshots folder
	 * 
	 * @param prefix
	 *            Screenshot .png will be prefixed with this string
	 */
	protected void takeSnapshot(String prefix) {
		File snap = ((TakesScreenshot) selenium())
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(snap, new File(SAVE_SCREENSHOTS_AT + prefix
					+ "_screenshot_" + TestUtils.appendDateAndTime() + ".png"));
		} catch (IOException e) {
			System.out.println("Unabel to save the screenshot.");
		}
	}

	/**
	 * Returns object reference to the SoftAssert object
	 * 
	 * @return SoftAssert
	 */
	protected SoftAssert checkPoint() {
		return softAssert;
	}
}
