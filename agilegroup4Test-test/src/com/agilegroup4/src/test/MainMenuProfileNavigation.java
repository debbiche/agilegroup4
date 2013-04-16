package com.agilegroup4.src.test;

import org.junit.Test;

import com.agilegroup4.src.MainMenuActivity;
import com.agilegroup4.src.UserProfileActivity;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public class MainMenuProfileNavigation extends ActivityInstrumentationTestCase2<MainMenuActivity> {

	public MainMenuProfileNavigation() {
		super("com.agilegroup4.src", MainMenuActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp(); 
	}
	
	@Test
	public void testOpenUserProfileActivity() {
		// register next activity that need to be monitored.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(UserProfileActivity.class.getName(), null, false);

		// open current activity.
		MainMenuActivity myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(com.agilegroup4.src.R.id.button_profile);
		myActivity.runOnUiThread(new Runnable() {
		  @Override
		  public void run() {
			// click button and open next activity.
		    button.performClick();
		  }
		});

		UserProfileActivity userProfileActivity = (UserProfileActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
		// next activity is opened and captured.
		assertNotNull(userProfileActivity);
		userProfileActivity .finish();
	}

}
