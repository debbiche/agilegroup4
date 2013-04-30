package com.agilegroup4.src;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
		
		tags = createTestTags();
		
		if (tags.size() > 0) {
			center = tags.get(0);
			if (tags.size() > 1) {
				right = tags.get(1);
			}
		}
		
		addRelatedTags();
	}
	
	private void addRelatedTags(){
		int relatedSize = center.getRelatedTags().size();
		
	    switch (relatedSize) {
        case 0:
            break;
        case 1:

            break;
        case 2:

            break;
        case 3:

            break;
        default:
            break;
	    }
	    if (relatedSize > 3) {
	    	
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
