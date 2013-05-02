package com.agilegroup4.src.test;

import com.agilegroup4.helpers.StringUtility;
import junit.framework.TestCase;

public class StringUtility_RemoveBackslashFromString extends TestCase {

	private String stringWithBackslash;
	private String resultString;
	private String stringWithOutBackslash;
	
	protected void setUp() throws Exception {
		super.setUp(); 
		stringWithBackslash = "Hello\\n world\\n!";
		stringWithOutBackslash = "Hello world!";
	}
	
	public void testRemoveBackslashFromString() {
		resultString = StringUtility.removeBackslashNFromString(stringWithBackslash);
		
		assertEquals(resultString, stringWithOutBackslash);
	}
	
	
}
