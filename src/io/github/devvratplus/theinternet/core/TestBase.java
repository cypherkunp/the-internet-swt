package io.github.devvratplus.theinternet.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	// Property file locations
	protected static final String SELENIUM_PROPERTY_FILE = System
			.getProperty("user.dir") + "\\config\\selenium.properties";

	// Driver locations
	protected static final String CHROMEDRIVER_MACOS = System
			.getProperty("user.dir") + "\\drivers\\chromedriver_mac32";
	protected static final String CHROMEDRIVER_WINDOWS = System
			.getProperty("user.dir") + "\\drivers\\chromedriver_win32.exe";
	protected static final String CHROMEDRIVER_LINUX32 = System
			.getProperty("user.dir") + "\\drivers\\chromedriver_linux32";
	protected static final String CHROMEDRIVER_LINUX64 = System
			.getProperty("user.dir") + "\\drivers\\chromedriver_linux64";

	private static Properties seleniumProperty = null;

	public TestBase(){
		loadSeleniumProperty();
	}

	private void loadSeleniumProperty() {
		seleniumProperty = new Properties();
		try {
			File selePropertyFile = new File(SELENIUM_PROPERTY_FILE);
			FileInputStream loadseleniumProp = new FileInputStream(
					selePropertyFile);
			seleniumProperty.load(loadseleniumProp);

		} catch (FileNotFoundException e) {
			System.out.println("File not found at the default location > "
					+ SELENIUM_PROPERTY_FILE);
		} catch (IOException e) {
			System.out.println("Unable to load the property file located at > "
					+ SELENIUM_PROPERTY_FILE);
		}
	}

	public String getSeleniumProperty(String propertyKey) {

		return seleniumProperty.getProperty(propertyKey);
	}
}
