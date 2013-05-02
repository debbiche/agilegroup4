package com.agilegroup4.src.test;

import com.agilegroup4.infrastructure.QuestionHandler;
import com.agilegroup4.model.QuestionList;

import junit.framework.TestCase;

public class QuestionHandler_SearchByEmptyText_NoResultsExpected extends TestCase {

	private String emptyStringTerm;
	
	protected void setUp() throws Exception {
		super.setUp();
		emptyStringTerm = "";
	}

	public void testSearchByFreeText() {
		QuestionList searchResultQuestions = QuestionHandler.searchForQuestions(emptyStringTerm, 60);
		
		assertTrue("Results where returned when searching by empty term, empty list expected.", searchResultQuestions.isEmpty());
	}
	
}
