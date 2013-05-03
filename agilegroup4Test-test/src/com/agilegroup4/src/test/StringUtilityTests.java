package com.agilegroup4.src.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class StringUtilityTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(StringUtilityTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(StringUtility_ConvertStringToDate.class);
		suite.addTestSuite(StringUtility_RemoveBackslashFromString.class);
		suite.addTestSuite(StringUtility_RemoveHTMLFromString.class);
		//$JUnit-END$
		return suite;
	}

}
