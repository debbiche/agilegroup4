package com.agilegroup4.src;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainMenuActivity extends Activity {
	
	/*
     * The "constructor" for this activity
     * @param instanceState The instance state.
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//super.setHeader(R.string.title_activity_main_menu);
		//super.setContentResourceID(R.layout.activity_main_menu);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}

	/*
     * The eventhandler for the phone menu-button pressed
     * @param menu The menu
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// The menu option is not available in this activity
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onSearchRequested()
	 */
	@Override 
	public boolean onSearchRequested() {
	    System.out.println("search pressed");
	    return super.onSearchRequested();
	}

	
	public void handleMenuItemOnClick(View view) {
		Intent intent;
		switch (view.getId()) {
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
		case R.id.button_search:
			onSearchRequested();
			break;
		case R.id.button_settings:
			intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			break;
		case R.id.button_logout:
			// Check if the user really wants to log out.
			checkLogout();
			break;
		}
	}

	private void checkLogout() {
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					// Yes button clicked
					SharedPreferences settings = getSharedPreferences(
							LoginActivity.PREFS_NAME, 0);
					SharedPreferences.Editor editor = settings.edit();
					editor.putInt("userID", 0);
					editor.commit();
					Intent intent = new Intent(getThis(), LoginActivity.class);
					intent.putExtra("action", "Logout");
					startActivity(intent);
					break;

				case DialogInterface.BUTTON_NEGATIVE:
					// No button clicked
					break;
				}
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to log out?")
				.setPositiveButton("Log Out", dialogClickListener)
				.setNegativeButton("Cancel", dialogClickListener).show();
	}
	
	private MainMenuActivity getThis(){
		return this;
	}
}
