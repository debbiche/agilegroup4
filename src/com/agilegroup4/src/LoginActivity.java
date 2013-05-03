package com.agilegroup4.src;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/* The login Activity is responsible for handling the login
 * and as it is always the first Activity to be called
 * it handles all necessary initializations (DB init, SharedPreferences) in the onCreate function
 * */
public class LoginActivity extends Activity {
	public final static String EXTRA_USERNAME = "USERNAME";
	public static final String PREFS_NAME = "SETTINGS";
	ProgressDialog progress;

	//public static DatabaseHandler dbHandler;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//super.setHeader(R.string.text_login_username);
		//super.setContentResourceID(R.layout.activity_login);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		progress = new ProgressDialog(this);
		
		loadDB();
		
		// Get logged in state
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		int userId = settings.getInt("userID", 0);
		
		
		// Check if already logged in
		if(userId != 0) {
			Intent intent = new Intent(this, MainMenuActivity.class);
			startActivity(intent);
		}
		checkLoggedOut();
	}
	
	// Check if we just logged out
	// If the login activity is called because the user logged out
	// a info about the log out shall be displayed
	public boolean checkLoggedOut(){ 
		if (getIntent().getExtras() == null)  
			return false;
		
		String action = getIntent().getStringExtra("action");
		
		if (action.equals("Logout")){
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Success!");
			alert.setMessage("Logged out.");
			
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}});	
			
			alert.show();
			return true;
		}
		else
			return false;
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

	// function called when login button is pressed
	public void loginButton(View view){
		
		// read username from text field and save in String inputText
		// TODO: catch NullPointerException here
		EditText editText = (EditText) findViewById(R.id.text_login_username);
		String inputText = editText.getText().toString();
		int userID = 0;
		
		// check for username input not being empty
		if(!inputText.equals("") && inputText != null){
			userID = Integer.parseInt(inputText);
		}
		
		// check for username in DB
		if(DatabaseHandler.userExists(userID)){
			
			// login successful

			// save userID to skip further logins
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putInt("userID", userID);
		    editor.commit();
			
		    // start next activity
			Intent intent = new Intent(this, MainMenuActivity.class);
			startActivity(intent);
		} else {
			
			// create alert informing the user about non existent user
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Sorry!");
			alert.setMessage("User not found...");
			
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}});	
			
			alert.show();
		}		
			
		}
	
	private void loadDB(){
		if (DatabaseHandler.loaded == 0) {
			progress.setTitle("Please Wait");
			progress.setMessage("Database is loading...");
			progress.show();
			new initDB().execute();
		}
		if (DatabaseHandlerTagDB.loaded == 0) {
			progress.setTitle("Please Wait");
			progress.setMessage("tag database is loading...");
			progress.show();
			new initTagDB().execute();
		}
	}
	
	// the return button at this screen shall always close the application
	public void onBackPressed(){
		System.exit(0);
	}
	
	private class initDB extends AsyncTask<Object, Object, Object>{

		@Override
		protected Object doInBackground(Object... params) {
			DatabaseHandler.initDB(getApplicationContext());
			return null;
		}
	
		@Override
	     protected void onPostExecute(Object params) {
			progress.dismiss();

	     }
	 }
	
	private class initTagDB extends AsyncTask<Object, Object, Object>{

		@Override
		protected Object doInBackground(Object... params) {
			DatabaseHandlerTagDB.initDB(getApplicationContext());
			return null;
		}
	
		@Override
	     protected void onPostExecute(Object params) {
			progress.dismiss();

	     }
	 }
}
