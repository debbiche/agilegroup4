package com.agilegroup4.src;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// The menu option is not available in this activity
		return true;
	}
	
	public void handleMenuItemOnClick(View view){
		Intent intent;
		switch(view.getId()){
		case R.id.button_profile: 
			intent = new Intent(this, UserProfileActivity.class);
			startActivity(intent);
			break;
		case R.id.button_questions: 
			intent = new Intent(this, QuestionOverviewActivity.class);
			startActivity(intent);
			break;
		case R.id.button_users: 
			intent = new Intent(this, UsersActivity.class);
			startActivity(intent);
			break;
		case R.id.button_tags: 
			intent = new Intent(this, TagsOverviewActivity.class);
			startActivity(intent);
			break;
		case R.id.button_settings: 
			intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			break;
		case R.id.button_logout: 
			// Add logout functionality
			break;
		}
	}

}
