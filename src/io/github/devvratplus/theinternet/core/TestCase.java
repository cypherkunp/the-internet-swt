package io.github.devvratplus.theinternet.core;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
/**
 * TestCase class that every test must extend.
 * 
 * @author Devvrat Shukla
 * 
 */
public abstract class TestCase extends TestBase {

	private WebDriver driver = null;
	private SoftAssert softAssert = null;
	
	@BeforeClass
	public void validateTestSetup() {
		if (!HttpResponseCodes
				.checkForResponseCode200(getSeleniumProperty("url"))) {

			fail("Webpage is not accessible");
		}
	}

	@BeforeMethod
	public void initializeSoftAssert() {
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
	 * 
	 * Initializes <b>Selenium WebDriver</b> object using the values specified
	 * in the selenium.properties file and starts the browser using the specified
	 * url.
	 * 
	 * @param url The url to the test page
	 *            
	 */
	protected void launchSelenium(String url) {
		driver = initializeSelenium();
		driver.get(url);
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
	
	protected Actions doAction(){
		return new Actions(driver);
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
	 * Finds the WebElement via partial linkText
	 * 
	 * @param linkText 
	 * @return WebElement
	 */
	protected WebElement findElementByPartialLinkText(String linkText) {

		return selenium().findElement(By.partialLinkText(linkText));
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
	 * Change focus to the parent context. If the current context is the top
	 * level browsing context, the context remains unchanged.
	 *
	 * @return This driver focused on the parent frame
	 */
	protected WebDriver switchToParentFrame() {
		return selenium().switchTo().parentFrame();
	}
	
	/**
	 * Selects either the first frame on the page, or the main document when a
	 * page contains iframes.
	 *
	 * @return This driver focused on the top window/first frame.
	 */
	protected WebDriver switchToDefaultContent() {
		return selenium().switchTo().defaultContent();
	}
	
	/**
	 * Switches to the currently active modal dialog for this particular driver
	 * instance.
	 *
	 * @return A handle to the dialog.
	 * @throws NoAlertPresentException
	 *             If the dialog cannot be found
	 */
	protected Alert switchToAlert() {
		return selenium().switchTo().alert();
	}

	/**
	 * The title of the current page.
	 *
	 * @return The title of the current page, with leading and trailing
	 *         whitespace stripped, or null if one is not already set
	 */
	protected String getTitle() {
		return selenium().getTitle();
	}
	
	/**
     * Refresh the current page
     */
	protected void reloadPage(){
		selenium().navigate().refresh();
	}
	
	/**
	 * Get a string representing the current URL that the browser is looking at.
	 *
	 * @return The URL of the page currently loaded in the browser
	 */
	protected String getCurrentURL() {
		return selenium().getCurrentUrl();
	}
	
	/**
	 * Checks whether an element is displayed on the webpage or not
	 * 
	 * @param locator
	 * @return true if the element is found on the webpage
	 */
	protected boolean isWebElementPresent(By locator) {
		try {
			selenium().findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	/**
	 * Reloads a page until an element is present
	 * 
	 * @param locator
	 * @param reloadNumberOfTime
	 *            Reloads the web page specified number of times
	 * @return true if the element was visible after page reload
	 */
	protected boolean reloadPageUntilAnElementIsVisibleOnThePage(By locator,
			int reloadNumberOfTime) {
		int counter = 0;
		while (!isWebElementPresent(locator)) {
			reloadPage();
			System.out.println("Reloading page, element not found");
			counter++;
			if (counter > reloadNumberOfTime) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return a set of window handles which can be used to iterate over all open
	 * windows of this WebDriver instance.
	 *
	 * @return A set of window handles which can be used to iterate over all
	 *         open windows.
	 */
	protected Set<String> getWindowHandles() {
		return selenium().getWindowHandles();

	}
	  
	/**
	 * Switch the focus of future commands for this driver to the window with
	 * the given name/handle.
	 *
	 * @param nameOrHandle
	 *            The name of the window or the handle as returned by
	 *            {@link WebDriver#getWindowHandle()}
	 * @return This driver focused on the given window
	 * @throws NoSuchWindowException
	 *             If the window cannot be found
	 */
	protected WebDriver switchToWindow(String nameOrHandle) {
		return selenium().switchTo().window(nameOrHandle);
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
