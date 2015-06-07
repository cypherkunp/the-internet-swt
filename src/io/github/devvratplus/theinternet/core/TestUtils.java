package io.github.devvratplus.theinternet.core;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestUtils {
	
	/**
	 * Returns a <code>String</code> with Date and Time formatted as
	 * <i>yyyyMMddHHmmss</i>
	 * 
	 * @return String
	 */
	public static String appendDateAndTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();

		return dateFormat.format(cal.getTime());
	}

	/**
	 * Creates a temporary directory.
	 * 
	 * @return File
	 * @throws IOException
	 */
	public static File createTempDirectory() throws IOException {
		final File temp;

		temp = File.createTempFile("temp", Long.toString(System.nanoTime()));

		if (!(temp.delete())) {
			throw new IOException("Could not delete temp file: "
					+ temp.getAbsolutePath());
		}

		if (!(temp.mkdir())) {
			throw new IOException("Could not create temp directory: "
					+ temp.getAbsolutePath());
		}

		return (temp);
	}
}
