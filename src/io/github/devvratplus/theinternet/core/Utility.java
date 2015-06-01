package io.github.devvratplus.theinternet.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utility {
	
	/**
	 * Returns a <code>String</code> with Date and Time
	 * formatted as <i>yyyyMMddHHmmss</i>
	 *  
	 * @return String
	 */
	public static String appendDateAndTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		
		return dateFormat.format(cal.getTime());
	}
}
