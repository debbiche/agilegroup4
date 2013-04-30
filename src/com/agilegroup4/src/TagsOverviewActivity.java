package com.agilegroup4.src;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.agilegroup4.model.Tag;

public class TagsOverviewActivity extends Activity {
	
	// Containing all the tags
	private ArrayList<Tag> tags;
	
	// The currently displayed tags
	private Tag topLeft;
	private Tag topRight;
	private Tag left;
	private Tag center;
	private Tag right;
	private Tag bottomLeft;
	private Tag bottomRight;
	
	// The current buttons hej
	private Button buttonOne;
	private Button buttonTwo;
	private Button buttonThree;
	private Button buttonFour;
	private Button buttonFive;
	private Button buttonSix;
	private Button buttonSeven;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tags_overview);
		
		buttonOne = (Button) findViewById(R.id.button1);
		buttonTwo = (Button) findViewById(R.id.button2);
		buttonThree = (Button) findViewById(R.id.button3);
		buttonFour = (Button) findViewById(R.id.button4);
		buttonFive = (Button) findViewById(R.id.button5);
		buttonSix = (Button) findViewById(R.id.button6);
		buttonSeven = (Button) findViewById(R.id.button7);
		
		tags = createTestTags();
		
		if (tags.size() > 0) {
			center = tags.get(0);
			updateButton(4,center);
			if (tags.size() > 1) {
				right = tags.get(1);
				updateButton(5,right);
			}
		}
				
		updateRelatedTags();
		updateNextPrevTag();
		
	}
	
	private void updateNextPrevTag(){
		int index = tags.indexOf(center);
		int tagsSize = tags.size();
		
		if (index == 0){
			buttonThree.setVisibility(View.INVISIBLE);
			if (tagsSize == 1){
				buttonFive.setVisibility(View.INVISIBLE);
			}
			else {
				right = tags.get(index+1);
				updateButton(5,right);
				buttonFive.setVisibility(View.VISIBLE);
			}
		}
		else if (index == tags.size()-1){
			left = tags.get(index-1);
			updateButton(3,left);
			buttonThree.setVisibility(View.VISIBLE);
			buttonFive.setVisibility(View.INVISIBLE);
		}
		else {
			left = tags.get(index-1);
			right = tags.get(index+1);
			updateButton(3,left);
			updateButton(5,right);
			buttonThree.setVisibility(View.VISIBLE);
			buttonFive.setVisibility(View.VISIBLE);
		}
	}
	
	private void updateRelatedTags(){
		int relatedSize = center.getRelatedTags().size();
		
	    switch (relatedSize) {
        case 0:
        	buttonOne.setVisibility(View.INVISIBLE);
        	buttonTwo.setVisibility(View.INVISIBLE);
        	buttonSix.setVisibility(View.INVISIBLE);
        	buttonSeven.setVisibility(View.INVISIBLE);
        	break;
        case 1:
        	topLeft = center.getRelatedTags().get(0);
        	updateButton(1, topLeft);
        	
        	buttonOne.setVisibility(View.VISIBLE);
        	buttonTwo.setVisibility(View.INVISIBLE);
        	buttonSix.setVisibility(View.INVISIBLE);
        	buttonSeven.setVisibility(View.INVISIBLE);
        	

            break;
        case 2:
        	topLeft = center.getRelatedTags().get(0);
        	topRight = center.getRelatedTags().get(1);
        	
        	updateButton(1, topLeft);
        	updateButton(2, topRight);
        	
        	buttonOne.setVisibility(View.VISIBLE);
        	buttonTwo.setVisibility(View.VISIBLE);
        	buttonSix.setVisibility(View.INVISIBLE);
        	buttonSeven.setVisibility(View.INVISIBLE);

            break;
        case 3:
        	topLeft = center.getRelatedTags().get(0);
        	topRight = center.getRelatedTags().get(1);
        	bottomLeft = center.getRelatedTags().get(2);
        	
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
	    if (relatedSize > 3) {
        	topLeft = center.getRelatedTags().get(0);
        	topRight = center.getRelatedTags().get(1);
        	bottomLeft = center.getRelatedTags().get(0);
        	bottomRight = center.getRelatedTags().get(1);
	    	
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
	private void updateButton(int pos, Tag tag){
	    switch (pos) {
        case 1:
        	buttonOne.setText(tag.getTag());
        	break;
        case 2:
        	buttonTwo.setText(tag.getTag());
            break;
        case 3:
        	buttonThree.setText(tag.getTag());
            break;
        case 4:
        	buttonFour.setText(tag.getTag());
            break;
        case 5:
        	buttonFive.setText(tag.getTag());
            break;
        case 6:
        	buttonSix.setText(tag.getTag());
            break;
        case 7:
        	buttonSeven.setText(tag.getTag());
            break;
        default:
            break;
	    }
	}
	
	/**
	 * Used for test purpose only
	 */
	private ArrayList<Tag> createTestTags(){
		ArrayList<Tag> ret = new ArrayList<Tag>();
		
		return ret;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tags_overview, menu);
		return true;
	}
	
	@Override //invoked when Searchbutton pressed, just for testing
	public boolean onSearchRequested() {
	    System.out.println("search pressed");
	    return super.onSearchRequested();
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
	        case R.id.menuitem_search:
	        	onSearchRequested();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
