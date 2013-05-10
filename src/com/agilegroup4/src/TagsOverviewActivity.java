package com.agilegroup4.src;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.agilegroup4.infrastructure.QuestionHandler;
import com.agilegroup4.model.QuestionList;
import com.agilegroup4.model.SearchMode;
import com.agilegroup4.model.Tag;
import com.agilegroup4.model.TagList;

public class TagsOverviewActivity extends Activity {

	// workaround for passing tag query to searchableActivity

	public static String tagQuery = "";

	
	// Contains the combined tags in the center button
	public static TagList mainTags;

	ProgressDialog progress;

	// Containing all the tags
	private ArrayList<Tag> tags;

	// The currently displayed tags
	private String topLeft;
	private String topRight;
	private String left;
	private String center;
	private String right;
	private String bottomLeft;
	private String bottomRight;

	// The current buttons
	
	// The current buttons (1-7 = topLeft - bottomRight)
	private Button buttonOne;
	private Button buttonTwo;
	private Button buttonThree;
	private Button buttonFour;
	private Button buttonFive;
	private Button buttonSix;
	private Button buttonSeven;

	private OnLongClickListener longClickListener;

	/*
	 * The "constructor" for this activity
	 * 
	 * @param instanceState The instance state.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// super.setHeader(R.string.title_activity_settings);
		// super.setContentResourceID(R.layout.activity_tags_overview);
		progress = new ProgressDialog(this);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tags_overview);

		buttonOne = (Button) findViewById(R.id.button1);
		buttonTwo = (Button) findViewById(R.id.button2);
		buttonThree = (Button) findViewById(R.id.button3);
		buttonFour = (Button) findViewById(R.id.button4);
		buttonFive = (Button) findViewById(R.id.button5);
		buttonSix = (Button) findViewById(R.id.button6);
		buttonSeven = (Button) findViewById(R.id.button7);

		longClickListener = new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {

				handleTagOnLongClick(v);

				return true;
			}
		};

		buttonOne.setOnLongClickListener(longClickListener);
		buttonTwo.setOnLongClickListener(longClickListener);
		buttonFour.setOnLongClickListener(longClickListener);
		buttonSix.setOnLongClickListener(longClickListener);
		buttonSeven.setOnLongClickListener(longClickListener);

		queryTags();

	}

	private void queryTags() {
		progress.setTitle("Please Wait");
		progress.setMessage("Tags are loading...");
		progress.show();
		new queryTags().execute();

	}

	/**
	 * Invoked when long clicking a tag
	 * 
	 * @param view
	 *            The view object invoking
	 */
	public void handleTagOnLongClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			addCombinedTag(topLeft);
			break;
		case R.id.button2:
			addCombinedTag(topRight);
			break;
		case R.id.button4:
			onSearchRequestedTagsOnly();
			break;
		case R.id.button6:
			addCombinedTag(bottomLeft);
			break;
		case R.id.button7:
			addCombinedTag(bottomRight);
			break;
		default:
			break;
		}
		updateRelatedTags();
	}

	/**
	 * Invoked when tags should be combined
	 * 
	 * @param tagName
	 *            the tag that is to be added.
	 */
	private void addCombinedTag(String tagName) {
		if (mainTags.add(getTagByName(tagName)))
			updateButton(4, mainTags.toString());
	}

	/*
	 * Invoked when clicking a tag
	 * 
	 * @param view The view object invoking.
	 */
	public void handleTagOnClick(View view) {
		final Animation anim;
		switch (view.getId()) {
		case R.id.button1:
			if (getTagByName(topLeft) == null)
				break;

			anim = AnimationUtils.loadAnimation(this, R.anim.button_one_anim);
			buttonOne.startAnimation(anim);
			clearMainTags(topLeft);
			center = topLeft;
			break;
		case R.id.button2:
			if (getTagByName(topRight) == null)
				break;

			anim = AnimationUtils.loadAnimation(this, R.anim.button_two_anim);
			buttonTwo.startAnimation(anim);
			clearMainTags(topRight);
			center = topRight;
			break;
		case R.id.button3:
			if (getTagByName(left) == null)
				break;

			anim = AnimationUtils.loadAnimation(this, R.anim.right_anim);
			buttonThree.startAnimation(anim);
			buttonFour.startAnimation(anim);
			buttonFive.startAnimation(anim);
			clearMainTags(left);
			center = left;
			break;
		case R.id.button4:
			onSearchRequested();
			break;
		case R.id.button5:
			if (getTagByName(right) == null)
				break;

			anim = AnimationUtils.loadAnimation(this, R.anim.left_anim);
			buttonThree.startAnimation(anim);
			buttonFour.startAnimation(anim);
			buttonFive.startAnimation(anim);
			clearMainTags(right);
			center = right;
			break;
		case R.id.button6:
			if (getTagByName(bottomLeft) == null)
				break;

			anim = AnimationUtils.loadAnimation(this, R.anim.button_six_anim);
			buttonSix.startAnimation(anim);
			clearMainTags(bottomLeft);
			center = bottomLeft;
			break;
		case R.id.button7:
			if (getTagByName(bottomRight) == null)
				break;

			anim = AnimationUtils.loadAnimation(this, R.anim.button_seven_anim);
			buttonSeven.startAnimation(anim);
			clearMainTags(bottomRight);
			center = bottomRight;
			break;
		}

		updateButton(4, center);
		updateNextPrevTag();
		updateRelatedTags();
	}

	/**
	 * A tag has been clicked, clear and redraw main tags
	 * 
	 * @param newMainTag
	 *            the new tag that are to be displayed in the middle
	 */
	private void clearMainTags(String newMainTag) {
		mainTags.clear();
		mainTags.add(getTagByName(newMainTag));
	}

	/**
	 * Updates the tags to the left and right of the center.
	 */
	private void updateNextPrevTag(){
		Tag next = mainTags.getNext();
		Tag previous = mainTags.getPrevious();

		if (previous == null) {
			buttonThree.setVisibility(View.INVISIBLE);
			if (tags.size() == 1) {
				buttonFive.setVisibility(View.INVISIBLE);
			} else {
				right = next.getTagName();
				updateButton(5, right);
				buttonFive.setVisibility(View.VISIBLE);
			}
		} else if (next == null) {
			left = previous.getTagName();
			updateButton(3, left);
			buttonThree.setVisibility(View.VISIBLE);
			buttonFive.setVisibility(View.INVISIBLE);
		} else {
			left = previous.getTagName();
			right = next.getTagName();
			updateButton(3, left);
			updateButton(5, right);
			buttonThree.setVisibility(View.VISIBLE);
			buttonFive.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * Updates the (maximum four) related tags for the center tag(s)
	 */
	private void updateRelatedTags(){
		ArrayList<Tag> relTags = mainTags.getRelatedTags();
		Tag centerTag = mainTags.get(0);

		switch (relTags.size()) {
		case 0:
			buttonOne.setVisibility(View.INVISIBLE);
			buttonTwo.setVisibility(View.INVISIBLE);
			buttonSix.setVisibility(View.INVISIBLE);
			buttonSeven.setVisibility(View.INVISIBLE);
			break;
		case 1:
			topLeft = relTags.get(0).getTagName();
			updateButton(1, topLeft);

			buttonOne.setVisibility(View.VISIBLE);
			buttonTwo.setVisibility(View.INVISIBLE);
			buttonSix.setVisibility(View.INVISIBLE);
			buttonSeven.setVisibility(View.INVISIBLE);

			break;
		case 2:
			topLeft = relTags.get(0).getTagName();
			topRight = relTags.get(1).getTagName();

			updateButton(1, topLeft);
			updateButton(2, topRight);

			buttonOne.setVisibility(View.VISIBLE);
			buttonTwo.setVisibility(View.VISIBLE);
			buttonSix.setVisibility(View.INVISIBLE);
			buttonSeven.setVisibility(View.INVISIBLE);

			break;
		case 3:
			topLeft = relTags.get(0).getTagName();
			topRight = relTags.get(1).getTagName();
			bottomLeft = relTags.get(2).getTagName();

			updateButton(1, topLeft);
			updateButton(2, topRight);
			updateButton(6, bottomLeft);

			buttonOne.setVisibility(View.VISIBLE);
			buttonTwo.setVisibility(View.VISIBLE);
			buttonSix.setVisibility(View.VISIBLE);
			buttonSeven.setVisibility(View.INVISIBLE);

			break;
		default:
			break;
		}
		if (relTags.size() > 3) {
			topLeft = relTags.get(0).getTagName();
			topRight = relTags.get(1).getTagName();
			bottomLeft = relTags.get(2).getTagName();
			bottomRight = relTags.get(3).getTagName();

			updateButton(1, topLeft);
			updateButton(2, topRight);
			updateButton(6, bottomLeft);
			updateButton(7, bottomRight);

			buttonOne.setVisibility(View.VISIBLE);
			buttonTwo.setVisibility(View.VISIBLE);
			buttonSix.setVisibility(View.VISIBLE);
			buttonSeven.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * Updates a button pos is the position of the button (1..7)
	 */
	private void updateButton(int pos, String tagName) {
		switch (pos) {
		case 1:
			buttonOne.setText(tagName);
			break;
		case 2:
			buttonTwo.setText(tagName);
			break;
		case 3:
			buttonThree.setText(tagName);
			break;
		case 4:
			buttonFour.setText(tagName);
			break;
		case 5:
			buttonFive.setText(tagName);
			break;
		case 6:
			buttonSix.setText(tagName);
			break;
		case 7:
			buttonSeven.setText(tagName);
			break;
		default:
			break;
		}
	}

	/*
	 * The eventhandler for the phone menu-button pressed
	 * 
	 * @param menu The menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tags_overview, menu);
		return true;
	}

	@Override
	// invoked when Searchbutton pressed, just for testing
	public boolean onSearchRequested() {
		tagQuery = (String) buttonFour.getText();
		SearchableActivity.SearchMode = SearchMode.QUESTION_WITHTAGS;
		return super.onSearchRequested();
	}

	private void onSearchRequestedTagsOnly() {
		QuestionList searchResultQuestions = QuestionHandler
				.searchForQuestionsByTag("", mainTags,
						SearchableActivity.NUMBER_OF_ALLOWED_QUESTIONS);
		// Creates a bundle and parce the the search result QuestionList
		Bundle b = new Bundle();
		b.putParcelable("questionsData", searchResultQuestions); // Insert list
																	// in a
																	// Bundle
																	// object
		Intent intent = new Intent(this, QuestionOverviewActivity.class);
		// Includes the bundle and parced search result into the intent for
		// search activity.
		intent.putExtras(b);
		startActivity(intent);
	}

	/*
	 * The eventhandler for pressing one item in the options menu
	 * 
	 * @param item The menu item
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		// Handle item selections
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

	/*
	 * Queries the <b>tags</b> object of this class for one Tag by it's name
	 * 
	 * @param name String containing the name of the tag you are looking for
	 * 
	 * @return <b>Tag</b> if a related tag was found OR <b>null</b> if no tag
	 * was found
	 */
	private Tag getTagByName(String name) {
		for (int i = 0; i < this.tags.size(); i++) {
			if (this.tags.get(i).getTagName().equals(name)) {
				return this.tags.get(i);
			}
		}

		return null;
	}

	/**
	 * Initiation of the tags objects asynchronously.
	 */
	private class queryTags extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... params) {
			tags = DatabaseHandlerTagDB.queryTags();
			return null;
		}

		@Override
		protected void onPostExecute(Object params) {
			progress.dismiss();
			mainTags = new TagList(tags);

			if (tags.size() > 0) {
				center = tags.get(0).getTagName();
				updateButton(4, center);
				mainTags.add(tags.get(0));
				if (tags.size() > 1) {
					right = tags.get(1).getTagName();
					updateButton(5, right);
					updateRelatedTags();
					updateNextPrevTag();
				}
			}

		}
	}

}
