package com.agilegroup4.src.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(AnsweredComparatorComparingTest.class);
		suite.addTest(ComparatorTestSuite.suite());
		suite.addTestSuite(FilterTest.class);
		suite.addTestSuite(ImportantComparatorComparingTest.class);
		suite.addTestSuite(LatestComparatorComparingTest.class);
		suite.addTestSuite(LoginInputTest.class);
		suite.addTestSuite(LoginTest.class);
		suite.addTestSuite(MainMenuProfileNavigation.class);
		suite.addTestSuite(MainMenuQuestionsNavigation.class);
		suite.addTestSuite(QuestionIdTest.class);
		//$JUnit-END$
		return suite;
	}

}
