package com.agilegroup4.src.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.agilegroup4.src.*;

public class FilterTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	public FilterTest(Class<LoginActivity> classTest) {
		super(classTest);
	}

	protected void setUp() throws Exception {
		super.setUp(); 
	}
	
	/**
	 * Test user profile button navigation.
	 */
	public void testFilterFeature() {
		// Start monitor on intended activity.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainMenuActivity.class.getName(), null, false);

		// Open login activity
		LoginActivity loginActivity = getActivity();

		// Get the navigation option for user profile activity
		final Button button = (Button) loginActivity.findViewById(com.agilegroup4.src.R.id.button_login);
		final EditText inputText = (EditText) loginActivity.findViewById(com.agilegroup4.src.R.id.text_login_username);
		loginActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {

				// Enter valid userID
				inputText.setText("13");

				// Click login button
				button.performClick();
			}
		});

		MainMenuActivity mma;

		// Next activity is opened and casted to intended activity type.
		try{
			mma = (MainMenuActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000000);
		}catch(java.lang.ClassCastException e){
			mma = null;
		}

		// We assert that the cast and fetch has gone right.
		assertNotNull("Failed to go to question overview activity after navigation test.", mma);
		
		//assertTrue(questionOverviewActivity.findViewById(com.agilegroup4.src.R.id.pop_text).toString().contains("Chris Jester-Young"));
		mma.finish();
		loginActivity.finish();
	}

}
