package com.agilegroup4.src;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.agilegroup4.helpers.StringUtility;
import com.agilegroup4.model.User;

public class UserProfileActivity extends Activity {

	/*
	 * The "constructor" for this activity
	 * @param instanceState The instance state.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);

		Bundle extras = getIntent().getExtras();
		if (extras == null)
			return;
		
		int userId = extras.getInt("userID", -1);
		
		if (userId < 1)
			return;

		User userObject = DatabaseHandler.getUserById(userId);	
		
		loadControlsWithData(userObject);
	}

	private void loadControlsWithData(User user)
	{
		WebView userAboutWebView = (WebView) findViewById(R.id.userAbout); 
		TextView userDisplayNameTextView = (TextView) findViewById(R.id.userDisplayName); 
		TextView userLocationTextView = (TextView) findViewById(R.id.userLocation); 
		TextView userReputationTextView = (TextView) findViewById(R.id.userReputation); 
		TextView userCreatedTextView = (TextView) findViewById(R.id.userCreated); 
		TextView userLastSeenTextView = (TextView) findViewById(R.id.userLastSeen); 
		ImageView profilePictureImageView = (ImageView) findViewById(R.id.profilePictureImageView); 
		
		userDisplayNameTextView.setText(user.getFriendlyDisplayName());
		
		String userLocation = StringUtility.washStringFromNULLTags(user.getLocation());
		if (userLocation != null && !userLocation.trim().equals(""))
			userLocationTextView.setText(userLocation);
		else
			userLocationTextView.setText("N/A");
		
		userReputationTextView.setText(Integer.toString(user.getReputation()));
		userCreatedTextView.setText(user.getCreation_date());
		userLastSeenTextView.setText(user.getLast_access_date());
		String about = StringUtility.washStringFromNULLTags(StringUtility.removeBackslashNFromString(user.getAbout_me()));
		
		userAboutWebView.loadData(about, "text/html", "UTF-8");
		
		String imageURL = String.format("http://www.gravatar.com/avatar/%s?s=96", user.getEmail_hash());
		Bitmap bitmapPicture = loadBitmap(imageURL);
		profilePictureImageView.setImageBitmap(bitmapPicture);
	}
	
	public Bitmap loadBitmap(String url) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return bitmap;
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


