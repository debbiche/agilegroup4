package com.agilegroup4.src.test;

import java.sql.Date;

import junit.framework.TestCase;

import com.agilegroup4.helpers.AnsweredComparator;
import com.agilegroup4.model.Question;

public class AnsweredComparatorComparingTest extends TestCase {

	private AnsweredComparator aComp;
	private Question q1;
	private Question q2;
	
	public AnsweredComparatorComparingTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		aComp = new AnsweredComparator();
		
		//Question(id, title, body, comment count, date of creation, score, view count, favorite count)
		q1 = new Question(0,"","", 1000,new Date(0), 0,0,0, "");
		q2 = new Question(1,"","", 1,new Date(0), 0,0,0, "");
		q1.setAnswerCount(1000);
		q2.setAnswerCount(1);
	}
	
	/**
	 * Test AnsweredComparator returning correct comparison.
	 */
	public void testAnsweredComparator() {
		//In the comparator we want a descending order in number of answers and comments.
		assertTrue("The comparison was done wrong", aComp.compare(q1, q2) < 0);
	}

}
