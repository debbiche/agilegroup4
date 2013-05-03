package com.agilegroup4.src;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.agilegroup4.helpers.StringUtility;
import com.agilegroup4.model.Comment;

public class CommentsActivity extends Activity {
	
	// The current comments
	private ArrayList<Comment> comments;
	
    static final String KEY_COMMENT = "comment";
    private static LayoutInflater inflater=null;
    public ArrayList<HashMap<String,String>> data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//super.setHeader(R.string.title_activity_comments);
		//super.setContentResourceID(R.layout.activity_comments);
		setContentView(R.layout.activity_comments);
		
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

		ArrayList<HashMap<String, String>> csList = new ArrayList<HashMap<String, String>>();

		final ListView listview = (ListView) findViewById(R.id.listview);
		for (int i = 0; i < comments.size(); i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(KEY_COMMENT, StringUtility.convertHTMLtoString(comments.get(i).getText()));
			csList.add(map);
		}

		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, csList);
		listview.setAdapter(adapter);
	
	}
	
	/*
	 * Puts the elements in the listview.
	 */
	private class StableArrayAdapter extends BaseAdapter {
				
		public StableArrayAdapter(Activity a, int textViewResourceId,
				ArrayList<HashMap<String, String>> objects) {
			data = objects;
			inflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		public int getCount() {
	        return data.size();
	    }
		
		public Object getItem(int position) {
	        return position;
	    }

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}
		
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View vi=convertView;
	        if(convertView==null)
	            vi = inflater.inflate(R.layout.list_row_comments, null);
	 
	        TextView text = (TextView)vi.findViewById(R.id.text);
	 
	        HashMap<String, String> q = new HashMap<String, String>();
	        q = data.get(position);
	 
	        // Setting all values in listview
	        text.setText(q.get(KEY_COMMENT));
	        
	        return vi;
	    }

	}
}
