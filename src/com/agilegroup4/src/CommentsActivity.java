package com.agilegroup4.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.agilegroup4.helpers.StringUtility;
import com.agilegroup4.model.Comment;
import com.agilegroup4.view.CustomTitleBarActivity;

public class CommentsActivity extends CustomTitleBarActivity {
	
	// The current comments
	private ArrayList<Comment> comments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.setHeader(R.string.title_activity_comments);
		super.setContentResourceID(R.layout.activity_comments);
		super.onCreate(savedInstanceState);

		int id = getIntent().getIntExtra("id", 0);
		comments = DatabaseHandler.getComments(id);
		displayComments();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comments, menu);
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
	        case R.id.menuitem_search:
	        	onSearchRequested();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override //invoked when Searchbutton pressed
	public boolean onSearchRequested() {
	    System.out.println("search pressed");
	    return super.onSearchRequested();
	}
	
	public void displayComments(){

		// HashMap needed for displaying the comments in the listview
		final ArrayList<String> commentbodies = new ArrayList<String>();
		final ListView listview = (ListView) findViewById(R.id.listview);
		for (int i = 0; i < comments.size(); i++){
			commentbodies.add(StringUtility.convertHTMLtoString(comments.get(i).getText()));
		}

		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, commentbodies);
		listview.setAdapter(adapter);
	
	}
	
	
	/*
	 * Puts the elements in the listview.
	 */
	private class StableArrayAdapter extends ArrayAdapter<String> {

		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}
	
	public ArrayList<Comment> createTestComments(){
		ArrayList<Comment> ret = new ArrayList<Comment>();
		Comment one = new Comment(1, "Comment one");
		Comment two = new Comment(2, "Comment two");
		Comment three = new Comment(3, "Comment three");
		ret.add(one);
		ret.add(two);
		ret.add(three);
		return ret;
	}

}
