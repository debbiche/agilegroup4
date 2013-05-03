package com.agilegroup4.src;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UsersActivity extends Activity {

	/*
     * The "constructor" for this activity
     * @param instanceState The instance state.
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//super.setHeader(R.string.title_activity_users);
		//super.setContentResourceID(R.layout.activity_users);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);
	}

	/*
     * The eventhandler for the phone menu-button pressed
     * @param menu The menu
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.users, menu);
		return true;
	}
	
	/*
     * Invoked when Searchbutton pressed
     * @returns If the search bar should be shown or not.
     */
	@Override
	public boolean onSearchRequested() {
		//getIntent().putExtra("action", "users");
	    return super.onSearchRequested();
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
	        case R.id.menuitem_search:
	        	onSearchRequested();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
