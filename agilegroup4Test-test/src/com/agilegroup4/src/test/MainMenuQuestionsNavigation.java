package com.agilegroup4.src.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.agilegroup4.src.*;

public class MainMenuQuestionsNavigation extends ActivityInstrumentationTestCase2<MainMenuActivity> {

	public MainMenuQuestionsNavigation() {
		super("com.agilegroup4.src", MainMenuActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp(); 
	}
	
	/**
	 * Test question overview button navigation.
	 */
	public void testOpenQuestionOverviewActivity() {
		// Start monitor on intended activity.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(QuestionOverviewActivity.class.getName(), null, false);

		// Open main menu activity that includes the navigation options.
		MainMenuActivity myActivity = getActivity();
		// Get the navigation option for question overview activity
		final Button button = (Button) myActivity.findViewById(com.agilegroup4.src.R.id.button_questions);
		myActivity.runOnUiThread(new Runnable() {
		  @Override
		  public void run() {
			// Click button and open next activity.
		    button.performClick();
		  }
		});

		// Next activity is opened and casted to intended activity type.
		QuestionOverviewActivity questionOverviewActivity = (QuestionOverviewActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		
		// We assert that the cast and fetch has gone right.
		assertNotNull("Failed to go to question overview activity after navigation test.", questionOverviewActivity);
		questionOverviewActivity.finish();
	}

}
