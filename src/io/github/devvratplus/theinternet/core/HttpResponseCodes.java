package io.github.devvratplus.theinternet.core;

import org.apache.http.client.fluent.Request;


public class HttpResponseCodes {

	
	public static boolean checkForResponseCode200(String url){
		
		try{
			int respCode = Request.Get(url).execute().returnResponse().getStatusLine()
                    .getStatusCode();
			
			if(respCode == 200) return true;
			else return false;
			
		} catch (Exception e){
			return false;	
		}
	}
	
	
	 public static int getResponseCode(String url) {
	        try {
	            return Request.Get(url).execute().returnResponse().getStatusLine()
	                    .getStatusCode();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
