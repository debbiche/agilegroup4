package com.agilegroup4.src;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UserProfileActivity extends Activity {

	private int userId;
	
	/*
     * The "constructor" for this activity
     * @param instanceState The instance state.
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//super.setHeader(R.string.title_activity_user_profile);
		//super.setContentResourceID(R.layout.activity_user_profile);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		//get ownerId from activity that called UserProfileActivity and convert to int
		userId = Integer.parseInt(this.getIntent().getStringExtra("ownerId"));
		
		//TODO: query database with user id and present in some pretty way
		System.out.println("profile of owner id: " + userId);
	
		//Temporary set top text view in activity to user id
        TextView userIdText = (TextView) findViewById(R.id.userIdTitle); 
        userIdText.setText(Integer.toString(userId));

	}

	/*
     * The eventhandler for the phone menu-button pressed
     * @param menu The menu
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}

	/*
     * The eventhandler for pressing one item in the options menu
     * @param item The menu item
     */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menuitem_mainmenu:
				intent = new Intent(this, MainMenuActivity.class);
				startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
