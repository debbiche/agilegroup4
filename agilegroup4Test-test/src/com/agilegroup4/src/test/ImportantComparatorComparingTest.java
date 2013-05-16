package com.agilegroup4.src.test;

import java.sql.Date;

import junit.framework.TestCase;

import com.agilegroup4.helpers.ImportantComparator;
import com.agilegroup4.model.Question;

public class ImportantComparatorComparingTest extends TestCase {

	private ImportantComparator aComp;
	private Question q1;
	private Question q2;
	
	public ImportantComparatorComparingTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		aComp = new ImportantComparator();
		
		//Question(id, title, body, comment count, date of creation, score, view count, favorite count)
		q1 = new Question(0,"","", 0,new Date(0), 1,2,3, "", 0); //added owner id as 0
		q2 = new Question(1,"","", 0,new Date(0), 1,2,3, "",  0); //added owner id as 0
	}
	
	/**
	 * Test AnsweredComparator returning correct comparison.
	 */
	public void testAnsweredComparator() {
		assertTrue("The test questions didn't weigh equally", aComp.compare(q1, q2) == 0);
	}

}
