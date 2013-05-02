package com.agilegroup4.src.test;

import java.sql.Date;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.agilegroup4.helpers.AnsweredComparator;
import com.agilegroup4.model.Question;
import com.agilegroup4.src.*;

public class ImportantComparatorComparingTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	private AnsweredComparator aComp;
	private Question q1;
	private Question q2;
	
	public ImportantComparatorComparingTest() {
		super("com.agilegroup4.src", LoginActivity.class);
	}

	protected void setUp() throws Exception {
		aComp = new AnsweredComparator();
		
		//Question(id, title, body, comment count, date of creation, score, view count, favorite count)
		q1 = new Question(0,"","", 0,new Date(0), 0,2,3);
		q2 = new Question(1,"","", 0,new Date(0), 150,1,2);
	}
	
	/**
	 * Test AnsweredComparator returning correct comparison.
	 */
	public void testAnsweredComparator() {
		assertTrue("The test questions didn't weigh equally", aComp.compare(q1, q2) == 0);
	}

}
