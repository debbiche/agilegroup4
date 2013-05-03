package com.agilegroup4.src.test;

import com.agilegroup4.helpers.StringUtility;

import junit.framework.TestCase;

public class StringUtility_RemoveHTMLFromString extends TestCase {

	private String stringWithHTML;
	private String resultString;
	private String stringWithOutHTML;
	
	public StringUtility_RemoveHTMLFromString(String name) {
		super(name);
		stringWithHTML = "<i>Hello\\n world\\n!<b>";
		stringWithOutHTML = "Hello world!";
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testRemoveHTMLFromString() {
		resultString = StringUtility.convertHTMLtoString(stringWithHTML);
		
		assertEquals(resultString, stringWithOutHTML);
	}

}
