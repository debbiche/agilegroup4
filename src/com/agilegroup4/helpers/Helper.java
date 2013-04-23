package com.agilegroup4.helpers;

import android.text.Html;

public class Helper {

	public Helper(){
		
	}
	
	/* converts a string containing HTML tags to a string readable for the android UI
	 * 
	 * */
	public String convertHTMLtoString (String html) {
		
		String parsedHtml =	removeBackslashNFromString(html) ;
		return Html.fromHtml(parsedHtml).toString();
	}
	
	public String removeBackslashNFromString(String input){
		return input.replace("\\n", "");
	}
	
}
