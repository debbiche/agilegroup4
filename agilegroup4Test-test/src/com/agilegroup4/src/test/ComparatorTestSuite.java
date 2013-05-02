package com.agilegroup4.src.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ComparatorTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(ComparatorTestSuite.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(AnsweredComparatorComparingTest.class);
		suite.addTestSuite(ImportantComparatorComparingTest.class);
		suite.addTestSuite(LatestComparatorComparingTest.class);
		//$JUnit-END$
		return suite;
	}

}
