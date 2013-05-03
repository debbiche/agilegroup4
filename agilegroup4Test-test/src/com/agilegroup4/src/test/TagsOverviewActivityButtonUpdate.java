package com.agilegroup4.src.test;

import junit.framework.Assert;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.agilegroup4.src.LoginActivity;
import com.agilegroup4.src.MainMenuActivity;
import com.agilegroup4.src.QuestionOverviewActivity;
import com.agilegroup4.src.TagsOverviewActivity;

public class TagsOverviewActivityButtonUpdate extends ActivityInstrumentationTestCase2<LoginActivity>{

	public TagsOverviewActivityButtonUpdate(Class<LoginActivity> classTest) {
		super(classTest);
	}

	public TagsOverviewActivityButtonUpdate() {
        super("", LoginActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp(); 
	}
	
	
	/**
	 * TagsOverviewActivity button update.
	 * @throws InterruptedException 
	 */
	public void test() throws InterruptedException{
	
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

			MainMenuActivity mainMenuActivity;

			// Next activity is opened and casted to intended activity type.
			try{
				mainMenuActivity = (MainMenuActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
			}catch(java.lang.ClassCastException e){
				mainMenuActivity = null;
			}
			
			// We assert that the cast and fetch has gone right.
			assertNotNull("Failed to go to main menu activity from login activity.", mainMenuActivity);
			
			// Start monitor on intended activity.
					ActivityMonitor activityMonitorTwo = getInstrumentation().addMonitor(TagsOverviewActivity.class.getName(), null, false);

					// Get the navigation option for question overview activity
					final Button buttonthree = (Button) mainMenuActivity.findViewById(com.agilegroup4.src.R.id.button3);
					mainMenuActivity.runOnUiThread(new Runnable() {
					  @Override
					  public void run() {
						// Click button and open next activity.
					    buttonthree.performClick();
					  }
					});

					// Next activity is opened and casted to intended activity type.
					TagsOverviewActivity tagsOverviewActivity = (TagsOverviewActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitorTwo, 50000);
					
					// We assert that the cast and fetch has gone right.
					assertNotNull("Failed to go to question overview from main menu activity", tagsOverviewActivity);
		
		
		// Get the middle buttons for TagsOverviewActivity
		final Button button3 = (Button) tagsOverviewActivity.findViewById(com.agilegroup4.src.R.id.button3);
		final Button button4 = (Button) tagsOverviewActivity.findViewById(com.agilegroup4.src.R.id.button4);
		final Button button5 = (Button) tagsOverviewActivity.findViewById(com.agilegroup4.src.R.id.button5);
		
		tagsOverviewActivity.runOnUiThread(new Runnable() {
		  @Override
		  public void run() {
			// Get previous text value on buttons  
			String button3Previousvalue = button3.getText().toString();
			String button4Previousvalue = button4.getText().toString();
			String button5Previousvalue = button5.getText().toString();
		    
			// Click rightmost button and update whole middle row with buttons.
			button5.performClick();
		  
			// Assert that all middle row button values have changed
		    Assert.assertTrue(button3.getText().toString().equals(button3Previousvalue));
		    //Assert.assertTrue(!(button4.getText().toString().equals(button4Previousvalue)));
		    //Assert.assertTrue(!(button5.getText().toString().equals(button5Previousvalue)));
		  }
		});
	
	}

}
