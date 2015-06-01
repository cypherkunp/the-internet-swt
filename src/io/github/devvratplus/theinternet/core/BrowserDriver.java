package io.github.devvratplus.theinternet.core;

import io.github.devvratplus.theinternet.exceptions.InvalidProfileException;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.opera.core.systems.OperaDriver;

public class BrowserDriver {
	
	

	public static WebDriver setup(Browser browserName, String profileName) throws InvalidProfileException{
		
		WebDriver driver = null;
		
		switch(browserName){
		
		case FIREFOX:
						if(profileName!=null){
							
							FirefoxProfile fProfile = (new ProfilesIni().getProfile(profileName));
							driver = new FirefoxDriver(fProfile);
						} else {
							
							throw new InvalidProfileException("Invalid Profile Name");
							
						}
			
		case CHROME:
	
		case SAFARI:
		case INTERNETEXPLORER:
		case OPERA:
			
		}
		
		
		return driver;
		
	}
	
	
public static WebDriver setup(Browser browserName){
		
		WebDriver driver = null;
		
		switch(browserName){
		
		case FIREFOX: 
						driver = new FirefoxDriver();
						break;	
						
						
		case CHROME: 
						System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/drivers/chromedriver_win32.exe");
						driver = new ChromeDriver();
						break;
					
		case SAFARI: 
						driver = new SafariDriver();
						break;
									
		case INTERNETEXPLORER:    	
			
						DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
						capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
						File file = new File("C:\\Program Files\\Internet Explorer\\iexplore.exe");
						System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
						driver = new InternetExplorerDriver(capabilities);
						break;
			
		case OPERA:     driver = new OperaDriver();
						break;
			
		}
		
		
		return driver;
		
	}

}
