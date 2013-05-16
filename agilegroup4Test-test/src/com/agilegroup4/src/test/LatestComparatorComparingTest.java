package com.agilegroup4.src.test;

import java.util.Date;

import junit.framework.TestCase;

import com.agilegroup4.helpers.LatestComparator;
import com.agilegroup4.model.Question;

public class LatestComparatorComparingTest extends TestCase {

	private LatestComparator aComp;
	private Question q1;
	private Question q2;
	
	public LatestComparatorComparingTest(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	protected void setUp() throws Exception {
		aComp = new LatestComparator();
		
		//Question(id, title, body, comment count, date of creation, score, view count, favorite count)
		q1 = new Question(0,"","", 0,new Date(2013, 1, 1, 1, 1, 1), 0,0,0, "", 0); //added owner id as 0
		q2 = new Question(1,"","", 0,new Date(2014, 1, 1, 1, 1, 1), 0,0,0, "", 0); //added owner id as 0
	}
	
	/**
	 * Test AnsweredComparator returning correct comparison.
	 */
	public void testAnsweredComparator() {
		//We want the reversed result because weighting latest the newest would be greater than the latest.
		assertTrue("The comparison was done wrong", aComp.compare(q1, q2) > 0);
	}

}
