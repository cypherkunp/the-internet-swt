package io.github.devvratplus.theinternet.core;

import java.io.File;

public class VerifyThat {

	public static boolean wasDownloadSuccessful(){
		
		String filePathString = null;
		if(CheckOS.isWindows()){
			 filePathString = "C:\\Users\\D\\Downloads\\25106.jpg";
		}
		
		File f = new File(filePathString); 
		return f.exists();
	}
	
}
