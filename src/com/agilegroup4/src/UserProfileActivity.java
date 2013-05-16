package com.agilegroup4.src;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.agilegroup4.model.User;

public class UserProfileActivity extends Activity {

	private int userId;
	private int loggedInUserId;
	private User currentUser;

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

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if(this.getIntent().getStringExtra("ownerId") != null){
				//get ownerId from activity that called UserProfileActivity with ownerId and convert to int
				userId = Integer.parseInt(this.getIntent().getStringExtra("ownerId"));


				//get model of question/answer/comment -owner from database
				currentUser = DatabaseHandler.getUserById(userId);
				
				//System.out.println("profile of owner id: " + userId);

				//Temporarily display some user information in activity
				TextView userIdText = (TextView) findViewById(R.id.userIdTitle); 
				userIdText.setText(Integer.toString(userId)+ "\nname: " + currentUser.getDisplay_name() + "\nAbout: " + Html.fromHtml(currentUser.getAbout_me()).toString());
			}
			else{
				
				//get model of currentUser from database
				loggedInUserId = Integer.parseInt(getIntent().getStringExtra("loggedInUser"));
				currentUser = DatabaseHandler.getUserById(loggedInUserId);
				
				//Temporarily display some user information in activity
				TextView userIdText = (TextView) findViewById(R.id.userIdTitle); 
				userIdText.setText(Integer.toString(userId)+ "\nname: " + currentUser.getDisplay_name() + "\nAbout: " + Html.fromHtml(currentUser.getAbout_me()).toString());


				//TODO: present in some pretty and grapically nice way

			}
		}
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
