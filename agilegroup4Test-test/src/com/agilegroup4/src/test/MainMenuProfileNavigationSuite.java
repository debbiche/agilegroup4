package com.agilegroup4.src.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class MainMenuProfileNavigationSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				MainMenuProfileNavigationSuite.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(MainMenuProfileNavigation.class);
		
	    for (int i = 0; i < 10; i++) {
	    	suite.addTest(new MainMenuProfileNavigation());
	    }
		
		//$JUnit-END$
		return suite;
	}

}
