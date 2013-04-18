package com.agilegroup4.src.test;

import junit.framework.Assert;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.agilegroup4.src.LoginActivity;
import com.agilegroup4.src.QuestionOverviewActivity;

public class LoginTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	// Start monitor on intended activity.
	ActivityMonitor activityMonitor = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);

	// Open login activity
	LoginActivity loginActivity = getActivity();
	
	public LoginTest() {
		super("com.agilegroup4.src", LoginActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp(); 
	}
	
	/**
	 * Tests if userLogin field input is saved correctly
	 */	
	public void testLoginInput() throws Throwable {
		
		//Get the input field from the userlogin activity
		final EditText userInputText = (EditText) loginActivity.findViewById(com.agilegroup4.src.R.id.text_login_username);
		
		//Create test input
		String input = "1";	
		
		//Insert input into input field
		userInputText.setText(input);		
		
		//Assert that input field contains input
		Assert.assertTrue(userInputText.getText().toString().equals(input));	
		
	}
	
	/**
	 * Test ???.
	 */
	public void testLoginActivity() {
		
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

		QuestionOverviewActivity questionOverviewActivity;
		
		// Next activity is opened and casted to intended activity type.
		try{
			questionOverviewActivity = (QuestionOverviewActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
		}catch(java.lang.ClassCastException e){
			questionOverviewActivity = null;
		}
		
		
		// We assert that the cast and fetch has gone right.
		assertNotNull("Failed to go to question overview activity after navigation test.", questionOverviewActivity);
		questionOverviewActivity.finish();
	}

}
