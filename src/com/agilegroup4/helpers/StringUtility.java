package com.agilegroup4.helpers;

import android.text.Html;

public class StringUtility {

	public StringUtility(){
		
	}
	
	/* converts a string containing HTML tags to a string readable for the android UI
	 * 
	 * */
	public static String convertHTMLtoString (String html) {
		
		String parsedHtml =	removeBackslashNFromString(html) ;
		return Html.fromHtml(parsedHtml).toString();
	}
	
	public static String removeBackslashNFromString(String input){
		return input.replace("\\n", "");
	}
	
}
