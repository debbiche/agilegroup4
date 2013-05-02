package com.agilegroup4.src.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.DateFormat;

import com.agilegroup4.helpers.StringUtility;

import junit.framework.TestCase;

public class StringUtility_ConvertStringToDate extends TestCase {

	private String dateString = "2013-05-02";
	private Date dateExpected;
	private DateFormat dateFormat;
	private Date resultingDate;
	
	@SuppressWarnings("deprecation")
	public StringUtility_ConvertStringToDate(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		dateString = "2013-05-02";
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateExpected = dateFormat.parse(dateString);
	}
	
	public void testRemoveBackslashFromString() {
		resultingDate = StringUtility.stringToDate(dateString);
		
		assertEquals(resultingDate, dateExpected);
	}

}
