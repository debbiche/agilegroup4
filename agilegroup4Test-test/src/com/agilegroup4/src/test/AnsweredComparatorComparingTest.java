package com.agilegroup4.src.test;

import java.sql.Date;

import android.test.ActivityInstrumentationTestCase2;

import com.agilegroup4.helpers.ImportantComparator;
import com.agilegroup4.model.Question;
import com.agilegroup4.src.LoginActivity;

public class AnsweredComparatorComparingTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	private ImportantComparator aComp;
	private Question q1;
	private Question q2;
	
	public AnsweredComparatorComparingTest() {
		super("com.agilegroup4.src", LoginActivity.class);
	}

	protected void setUp() throws Exception {
		aComp = new ImportantComparator();
		
		//Question(id, title, body, comment count, date of creation, score, view count, favorite count)
		q1 = new Question(0,"","", 11,new Date(0), 0,0,0);
		q2 = new Question(1,"","", 1,new Date(0), 0,0,0);
	}
	
	/**
	 * Test AnsweredComparator returning correct comparison.
	 */
	public void testAnsweredComparator() {
		assertTrue("The comparison was done wrong", aComp.compare(q1, q2) > 0);
	}

}
