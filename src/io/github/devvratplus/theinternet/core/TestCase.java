package io.github.devvratplus.theinternet.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.safari.SafariDriver;

public class TestCase extends TestBase {
	
	private WebDriver driver = null;

	public WebDriver initializeSelenium(){
		
	     // Setting the operating system specific system-properties
	     switch(getSeleniumProperty("os")){
	     
	     case "MacOS" 	   : System.setProperty("webdriver.chrome.driver",CHROMEDRIVER_MACOS);
	     					break;
	     					
	     case "Windows"   : System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_WINDOWS);
	     					break;
	     
	     case "Linux32"    : System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LINUX32);
	     					break;
	     
	     case "Linux64"    : System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LINUX64);
	     					break;
	     					
	     default		   : System.out.println("Invalid response. Please choose the correct operating system.");					
	     }
	     
		//Setting up the driver specific driver
		 switch(getSeleniumProperty("browser")){
		 
			 case "Firefox" : 
				 			 if(getSeleniumProperty("firefoxProfile")!="default"){
				 				 if(getSeleniumProperty("acceptUntrustedCertificates")=="true"){
				 					FirefoxProfile fProfile = (new ProfilesIni().getProfile(getSeleniumProperty("firefoxProfile")));
						 			fProfile.setAcceptUntrustedCertificates(true);// this will accept an certificate errors on page in firefox
				 					driver = new FirefoxDriver(fProfile);
				 				 } else {
					 			  FirefoxProfile fProfile = (new ProfilesIni().getProfile(getSeleniumProperty("firefoxProfile")));
					 			  driver = new FirefoxDriver(fProfile);
					 			 }
				 			  } else { 
				 			  driver = new FirefoxDriver();
				 			  }
			 				  break;
			 				 	
			 case "Safari"  : driver = new SafariDriver();
			 				  break;
			 					
			 case "Chrome"  : 
							  driver = new ChromeDriver();
							  break;
								
			 default		: System.out.println("Invalid Browser selected, Tests will be run on firefox by default."); 
			 				  driver = new FirefoxDriver();
		 					
		 }
		 
		 if(!getSeleniumProperty("implicitTimeOut").equals("")){
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
		 
		return driver;					
	}
	
	public void launchSelenium(){
		driver = initializeSelenium();
		driver.get(getSeleniumProperty("url"));
	}
	
	public void quitSelenium(){
		driver.quit();
	}
		
	public WebDriver selenium(){
		return driver;
	}
	
	public void clickOnLink(String linkText){
		
		selenium().findElement(By.linkText(linkText)).click();
	}
	
}
