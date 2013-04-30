package com.agilegroup4.src;

import com.agilegroup4.view.CustomTitleBarActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class SettingsActivity extends CustomTitleBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.setHeader(R.string.title_activity_settings);
		super.setContentResourceID(R.layout.activity_settings);
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
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
