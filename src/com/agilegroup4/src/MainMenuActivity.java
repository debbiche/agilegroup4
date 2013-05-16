package com.agilegroup4.src;

import com.agilegroup4.model.SearchMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/*
 * The main menu activity showing the main menu of this application.
 */
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
	    SearchableActivity.SearchMode = SearchMode.QUESTION_FREETEXT;
	    return super.onSearchRequested();
	}

	/*
	 * Invoked when user clicks a menu button.
	 * @param view The view for fetching the id of the selected button.
	 */
	public void handleMenuItemOnClick(View view) {
		Intent intent;
		switch (view.getId()) {
		case R.id.button_profile:	
			//send id of current logged in user to UserProfileActivity
			SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
			intent = new Intent(this, UserProfileActivity.class);
			intent.putExtra("userID", settings.getInt("userID", 0));
			startActivity(intent);
			break;
		case R.id.button_questions:
			intent = new Intent(this, QuestionOverviewActivity.class);
			startActivity(intent);
			break;
		case R.id.button_tags:
			intent = new Intent(this, TagsOverviewActivity.class);
			startActivity(intent);
			break;
		case R.id.button_search:
			onSearchRequested();
			break;
		case R.id.button_logout:
			// Check if the user really wants to log out.
			checkLogout();
			break;
		}
	}

	/*
	 * Handle log out selection in the menu.
	 */
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
	
	/**
	 * Returns the current main menu activity object.
	 */
	private MainMenuActivity getThis(){
		return this;
	}
}
