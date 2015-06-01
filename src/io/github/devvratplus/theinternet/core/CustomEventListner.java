package io.github.devvratplus.theinternet.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class CustomEventListner extends AbstractWebDriverEventListener {
	
	 public void afterNavigateBack(WebDriver driver) {
		    System.out.println("Navigated back to the page!");
		  }

}
