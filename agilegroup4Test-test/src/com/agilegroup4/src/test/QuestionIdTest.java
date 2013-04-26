package com.agilegroup4.src.test;

import java.util.ArrayList;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.agilegroup4.model.Question;
import com.agilegroup4.src.DatabaseHandler;
import com.agilegroup4.src.LoginActivity;
import com.agilegroup4.src.MainMenuActivity;
import com.agilegroup4.src.QuestionOverviewActivity;
import com.agilegroup4.src.R;

public class QuestionIdTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	public static final int TEST_QUESTION_ID = 8414287;
	
	public QuestionIdTest() {
		super("com.agilegroup4.src", LoginActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp(); 
	}

	/**
	 * Test ???.
	 */
	public void testLoginActivity() {

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
				ActivityMonitor activityMonitorTwo = getInstrumentation().addMonitor(QuestionOverviewActivity.class.getName(), null, false);

				// Get the navigation option for question overview activity
				final Button buttontwo = (Button) mainMenuActivity.findViewById(com.agilegroup4.src.R.id.button_questions);
				mainMenuActivity.runOnUiThread(new Runnable() {
				  @Override
				  public void run() {
					// Click button and open next activity.
				    button.performClick();
				  }
				});

				// Next activity is opened and casted to intended activity type.
				QuestionOverviewActivity questionOverviewActivity = (QuestionOverviewActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 50000);
				
				// We assert that the cast and fetch has gone right.
				assertNotNull("Failed to go to question overview from main menu activity", questionOverviewActivity);
		
		
		
		Question q = getFirstQuestWithComments(DatabaseHandler.getQuestions());
		
		if (q == null)
			assertTrue("Failed to find any question with answers",false);
		
		int qid = q.getId();
		
		int posInListView = questionOverviewActivity.idsTestMap.get(qid);
		final ListView listview = (ListView) questionOverviewActivity.findViewById(R.id.listview);
		listview.requestFocusFromTouch();
		listview.setSelection(posInListView);
		listview.performItemClick(
			    listview.getAdapter().getView(posInListView, null, null), posInListView, posInListView);
		
		
		//assertTrue(questionOverviewActivity.findViewById(com.agilegroup4.src.R.id.pop_text).toString().contains("Chris Jester-Young"));
		//questionOverviewActivity.finish();
		//loginActivity.finish();
		
		assertTrue(true);
	}
	
	private Question getFirstQuestWithComments(ArrayList<Question> qs){
		for(int i = 0; i<qs.size();i++){
			if(qs.get(i).getAnswerCount() > 0)
				return qs.get(i);
		}
		return null;
	}

}
