package com.agilegroup4.src.test;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.agilegroup4.src.*;

public class MainMenuProfileNavigation extends ActivityInstrumentationTestCase2<MainMenuActivity> {

	public MainMenuProfileNavigation() {
		super("com.agilegroup4.src", MainMenuActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp(); 
	}
	
	/**
	 * Test user profile button navigation.
	 */
	
	public void testOpenUserProfileActivity() {
		// Start monitor on intended activity.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(UserProfileActivity.class.getName(), null, false);

		// Open main menu activity that includes the navigation options.
		MainMenuActivity myActivity = getActivity();
		// Get the navigation option for user profile activity
		final Button button = (Button) myActivity.findViewById(com.agilegroup4.src.R.id.button_profile);
		myActivity.runOnUiThread(new Runnable() {
		  @Override
		  public void run() {
			// Click button and open next activity.
		    button.performClick();
		  }
		});

		// Next activity is opened and casted to intended activity type.
		UserProfileActivity userProfileActivity = (UserProfileActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
		
		// We assert that the cast and fetch has gone right.
		assertNotNull("Failed to go to user profile activity after navigation test.", userProfileActivity);
		userProfileActivity.finish();
	}

}
