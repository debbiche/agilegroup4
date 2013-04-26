package com.agilegroup4.helpers;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.text.Html;
@SuppressLint("SimpleDateFormat")
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

	public static Date stringToDate(String date) {

		DateFormat newDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return newDate.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

}
