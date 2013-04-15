package com.agilegroup4.src;

import android.app.Activity;
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public void handleMenuItemOnClick(View view){
		switch(view.getId()){
		case R.id.button_profile: 
			// Go to profile activity
			break;
		case R.id.button_questions: 
			// Go to questions activity
			break;
		case R.id.button_users: 
			// Go to user activity
			break;
		case R.id.button_tags: 
			// Go to tags activity
			break;
		case R.id.button_settings: 
			// Go to settings activity
			break;
		}
	}

}
