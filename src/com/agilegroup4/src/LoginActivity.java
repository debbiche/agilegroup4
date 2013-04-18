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

/*
 * TODO: the return button at this screen should always close the application*/
public class LoginActivity extends Activity {
	public final static String EXTRA_USERNAME = "USERNAME";
	public static final String PREFS_NAME = "SETTINGS";
	public static DatabaseHandler dbHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		dbHandler = new DatabaseHandler(getApplicationContext());
		
		// Get loggedin state
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		int userId = settings.getInt("userID", 0);
		
		
		// Check if already logged in
		if(userId != 0) {
			Intent intent = new Intent(this, QuestionOverviewActivity.class);
			startActivity(intent);
		}
		
		// Check if we just logged out 
		if (getIntent().getExtras() == null)  
			return;
		
		String action = getIntent().getStringExtra("action");
		
		if (action.equals("Logout")){
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Success!");
			alert.setMessage("Logged out.");
			
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}});	
			
			alert.show();
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
		Intent intent;
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
		String inputText = editText.getText().toString();
		int userID = 0;
		
		// check for username input not being empty
		if(!inputText.equals("") && inputText != null){
			userID = Integer.parseInt(inputText);
		}
		
		// check for username in DB
		if(dbHandler.userExists(userID)){
			
			// login successful --> open Question Overview Activity and hand over "user data"
			
			// save userID to skip further logins
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putInt("userID", userID);
		    editor.commit();
			
		    // call next activity
			Intent intent = new Intent(this, QuestionOverviewActivity.class);
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
