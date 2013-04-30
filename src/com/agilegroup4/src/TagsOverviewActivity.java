package com.agilegroup4.src;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.agilegroup4.model.Tag;

public class TagsOverviewActivity extends Activity implements OnTouchListener{
	
	private final static int START_DRAGGING = 0;
	private final static int STOP_DRAGGING = 1;
	private int status;
	
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
	
	// The current buttons
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
	
		@Override
	    public boolean onTouch(View view, MotionEvent me) {
	   if (me.getAction() == MotionEvent.ACTION_DOWN) {
		   if (view instanceof Button)
			   status = START_DRAGGING;
	
	
	   }
	   if (me.getAction() == MotionEvent.ACTION_UP) {
	       status = STOP_DRAGGING;
	   } else if (me.getAction() == MotionEvent.ACTION_MOVE) {
	       if (status == START_DRAGGING) {
	    	   Tag tagss = new Tag(213, "ERMAGEERD");
	    	   updateButton(4,tagss);
	    	  
	    	   /**
	           LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
	                   50, 50);
	           layoutParams.setMargins((int) me.getRawX() - 25,
	                   (int) me.getRawY() - 50, 0, 0);
	           layout.removeView(btn);
	           layout.addView(btn, layoutParams);
	
	           btn.invalidate();
	           */
	    	   view.invalidate();
	       }
	   }
	   return false;
	}
	
	public void handleTagOnClick(View view){
		switch (view.getId()) {
		case R.id.button1:
			center = topLeft;
			break;
		case R.id.button2:
			center = topRight;
			break;
		case R.id.button3:
			center = left;
			break;
		case R.id.button4:
			// Implement search functionality here
			break;
		case R.id.button5:
			center = right;
			break;
		case R.id.button6:
			center = bottomLeft;
			break;
		case R.id.button7:
			center = bottomRight;
			break;
		}
		
		updateButton(4,center);
		updateNextPrevTag();
		updateRelatedTags();
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
        	buttonOne.setText(tag.getTagName());
        	break;
        case 2:
        	buttonTwo.setText(tag.getTagName());
            break;
        case 3:
        	buttonThree.setText(tag.getTagName());
            break;
        case 4:
        	buttonFour.setText(tag.getTagName());
            break;
        case 5:
        	buttonFive.setText(tag.getTagName());
            break;
        case 6:
        	buttonSix.setText(tag.getTagName());
            break;
        case 7:
        	buttonSeven.setText(tag.getTagName());
            break;
        default:
            break;
	    }
	}
	
	/**
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
		
		t1.addRelatedTag(t2);
		t1.addRelatedTag(t3);
		t1.addRelatedTag(t4);
		t1.addRelatedTag(t5);
		t2.addRelatedTag(t3);
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
