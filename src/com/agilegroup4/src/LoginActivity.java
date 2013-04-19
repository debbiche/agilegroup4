package com.agilegroup4.src;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
	public final static String EXTRA_USERNAME = "USERNAME";
	public static final String PREFS_NAME = "SETTINGS";
	public static DatabaseHandler dbHandler;
	public boolean loggedIn;
	public int userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		dbHandler = new DatabaseHandler(getApplicationContext());
		
		// Get loggedin state
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		loggedIn = settings.getBoolean("loggedIn", false);
		
		// Check if already logged in
		if(loggedIn) {
			userId = settings.getInt("userID", 0);
			Intent intent = new Intent(this, QuestionOverviewActivity.class);
			intent.putExtra(EXTRA_USERNAME, userId);	// TODO: this might be a different field and should be obtained from DB
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    	case R.id.menuitem_newuser:
	    		// Implement adding a new user functionality
	    		return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	public void loginButton(View view){
		// read username and save in String userID
		// TODO: catch NullPointerException here
		EditText editText = (EditText) findViewById(R.id.text_login_username);
		String inputTest = editText.getText().toString();
		int userID = Integer.parseInt(inputTest);
		
		// check for username in DB
		if(dbHandler.userExists(userID)){
			
			// login successful --> open Question Overview Activity and hand over "user data"
			
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putBoolean("loggedIn", true);
		    editor.putInt("userID", userID);
		    editor.commit();
						
			Intent intent = new Intent(this, QuestionOverviewActivity.class);
			intent.putExtra(EXTRA_USERNAME, userID);	// TODO: this might be a different field and should be obtained from DB
			startActivity(intent);
		} else {
			
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Sorry!");
			alert.setMessage("User not found...");
			
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}});	
			
			alert.show();
		}	
	}
}
