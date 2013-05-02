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

public class AnsweredComparatorComparingTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	private AnsweredComparator aComp;
	private Question q1;
	private Question q2;
	
	public AnsweredComparatorComparingTest() {
		super("com.agilegroup4.src", LoginActivity.class);
	}

	protected void setUp() throws Exception {
		aComp = new AnsweredComparator();
		
		//Question(id, title, body, comment count, date of creation, score, view count, favorite count)
		q1 = new Question(0,"","", 11,new Date(0), 0,0,0);
		q2 = new Question(1,"","", 1,new Date(0), 0,0,0);
	}
	
	/**
	 * Test AnsweredComparator returning correct comparison.
	 */
	public void testAnsweredComparator() {
		assertTrue(aComp.compare(q1, q2) > 0);
	}

}
