package com.agilegroup4.helper;

import android.text.Html;

public class Helper {

	public Helper(){
		
	}
	
	
	public String convertHTMLtoString (String html) {
		
		String parsedHtml =	removeBackslashNFromString(html) ;
		return Html.fromHtml(parsedHtml).toString();
	}
	
	public String removeBackslashNFromString(String input){
		return input.replace("\n", "");
	}
	
}
