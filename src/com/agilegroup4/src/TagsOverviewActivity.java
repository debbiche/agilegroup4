package com.agilegroup4.src;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.agilegroup4.model.SearchMode;
import com.agilegroup4.model.Tag;
import com.agilegroup4.model.TagList;

public class TagsOverviewActivity extends Activity {
	
	//workaround for passing tag query to searchableActivity

	public static String tagQuery ="";
	
	public static TagList mainTags;
	
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
     * @param instanceState The instance state.
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//super.setHeader(R.string.title_activity_settings);
		//super.setContentResourceID(R.layout.activity_tags_overview);
		
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
		buttonSix.setOnLongClickListener(longClickListener);
		buttonSeven.setOnLongClickListener(longClickListener);
		
		//tags = createTestTags();
		tags = DatabaseHandlerTagDB.queryTags(680);
		
		mainTags = new TagList(tags);
		
		if (tags.size() > 0) {
			center = tags.get(0).getTagName();
			updateButton(4,center);
			mainTags.add(tags.get(0));
			if (tags.size() > 1) {
				right = tags.get(1).getTagName();
				updateButton(5,right);
			}
		}
		
		updateRelatedTags();
		updateNextPrevTag();
		
	}
	
	/**
	 * Invoked when long clicking a tag
	 * @param view The view objekt invoking
	 */
	public void handleTagOnLongClick(View view){
		switch (view.getId()) {
		case R.id.button1:
			if(!mainTags.contains(getTagByName(topLeft))) 
				addCombinedTag(topLeft);
			break;
		case R.id.button2:
			if(!mainTags.contains(getTagByName(topRight))) 
				addCombinedTag(topRight);
			break;
		case R.id.button6:
			if(!mainTags.contains(getTagByName(bottomLeft))) 
				addCombinedTag(bottomLeft);
			break;
		case R.id.button7:
			if(!mainTags.contains(getTagByName(bottomRight))) 
				addCombinedTag(bottomRight);
			break;
		default:
			break;
		}
		updateRelatedTags();
	}
	
	/**
	 * Invoked when tags should be combined
	 * @param tagName the tag that is to be added.
	 */
	private void addCombinedTag(String tagName){
		if(mainTags.add(getTagByName(tagName)))
			updateButton(4,mainTags.toString());
	}
	
	/*
	 * Invoked when clicking a tag
	 * @param view The view object invoking.
	 */
	public void handleTagOnClick(View view){
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
		
		updateButton(4,center);
		updateNextPrevTag();
		updateRelatedTags();
	}
	
	/**
	 * A tag has been clicked, clear and redraw main tags
	 * @param newMainTag the new tag that are to be displayed in the middle
	 */
	private void clearMainTags(String newMainTag){
		mainTags.clear();
		mainTags.add(getTagByName(newMainTag));
	}
	
	
	private void updateNextPrevTag(){
		Tag next = mainTags.getNext();
		Tag previous = mainTags.getPrevious();
		
		if(previous == null){
			buttonThree.setVisibility(View.INVISIBLE);
			if (tags.size() == 1){
				buttonFive.setVisibility(View.INVISIBLE);
			}
			else {
				right = next.getTagName();
				updateButton(5,right);
				buttonFive.setVisibility(View.VISIBLE);
			}
		}
		else if (next == null){
			left = previous.getTagName();
			updateButton(3,left);
			buttonThree.setVisibility(View.VISIBLE);
			buttonFive.setVisibility(View.INVISIBLE);
		}
		else{
			left = previous.getTagName();
			right = next.getTagName();
			updateButton(3,left);
			updateButton(5,right);
			buttonThree.setVisibility(View.VISIBLE);
			buttonFive.setVisibility(View.VISIBLE);
		}
	}
	
	private void updateRelatedTags(){
		ArrayList<String> relTags = mainTags.getRelatedTags();
		Tag centerTag = mainTags.get(0);
		
	    switch (relTags.size()) {
        case 0:
        	buttonOne.setVisibility(View.INVISIBLE);
        	buttonTwo.setVisibility(View.INVISIBLE);
        	buttonSix.setVisibility(View.INVISIBLE);
        	buttonSeven.setVisibility(View.INVISIBLE);
        	break;
        case 1:
        	topLeft = relTags.get(0);
        	updateButton(1, topLeft);
        	
        	buttonOne.setVisibility(View.VISIBLE);
        	buttonTwo.setVisibility(View.INVISIBLE);
        	buttonSix.setVisibility(View.INVISIBLE);
        	buttonSeven.setVisibility(View.INVISIBLE);
        	

            break;
        case 2:
        	topLeft = relTags.get(0);
        	topRight = relTags.get(1);
        	
        	updateButton(1, topLeft);
        	updateButton(2, topRight);
        	
        	buttonOne.setVisibility(View.VISIBLE);
        	buttonTwo.setVisibility(View.VISIBLE);
        	buttonSix.setVisibility(View.INVISIBLE);
        	buttonSeven.setVisibility(View.INVISIBLE);

            break;
        case 3:
        	topLeft = relTags.get(0);
        	topRight = relTags.get(1);
        	bottomLeft = relTags.get(2);
        	
        	updateButton(1, topLeft);
        	updateButton(2,topRight);
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
        	topLeft = relTags.get(0);
        	topRight = relTags.get(1);
        	bottomLeft = relTags.get(2);
        	bottomRight = relTags.get(3);
	    	
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
	 * Updates a button
	 * pos is the position of the button (1..7)
	 */
	private void updateButton(int pos, String tagName){
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
	 * Used for test purpose only, statically assign related tags
	 */
	private ArrayList<Tag> createTestTags(){
		Tag t1 = new Tag(1, "java");
		Tag t2 = new Tag(2, "android");
		Tag t3 = new Tag(3, "html");
		Tag t4 = new Tag(4, "javascript");
		Tag t5 = new Tag(5, "css");
		Tag t6 = new Tag(6, "xml");
		Tag t7 = new Tag(7, "c++");
		Tag t8 = new Tag(8, "python");
		Tag t9 = new Tag(9, "ios");
		
		t1.addRelatedTag("android");
		t1.addRelatedTag("html");
		t1.addRelatedTag("javascript");
		t1.addRelatedTag("css");
		t2.addRelatedTag("xml");
		// html is not related to java
		
		ArrayList<Tag> ret = new ArrayList<Tag>();
		ret.add(t1);
		ret.add(t2);
		ret.add(t3);
		ret.add(t4);
		ret.add(t5);
		ret.add(t6);
		ret.add(t7);
		ret.add(t8);
		ret.add(t9);
		
		
		return ret;
	}

	/*
     * The eventhandler for the phone menu-button pressed
     * @param menu The menu
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tags_overview, menu);
		return true;
	}
	
	@Override //invoked when Searchbutton pressed, just for testing
	public boolean onSearchRequested() {
		tagQuery = (String) buttonFour.getText();
		SearchableActivity.SearchMode = SearchMode.QUESTION_WITHTAGS;
	    return super.onSearchRequested();
	}
	
	/*
     * The eventhandler for pressing one item in the options menu
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
	 * @param name String containing the name of the tag you are looking for
	 * @return <b>Tag</b> if a related tag was found OR <b>null</b> if no tag was found
	 */
	private Tag getTagByName(String name){
		for(int i = 0; i < this.tags.size(); i++){
			if (this.tags.get(i).getTagName().equals(name)){
				return this.tags.get(i);
			}
		}
		
		return null;
	}
	
}
