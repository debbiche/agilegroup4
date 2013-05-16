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
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.agilegroup4.helpers.StringUtility;
import com.agilegroup4.model.Comment;

/*
 * The comments activity showing the provided comments.
 */
public class CommentsActivity extends Activity {
	
	// The current comments
	private ArrayList<Comment> comments;
	
    static final String KEY_COMMENT = "comment";
    static final String KEY_USERNAME = "username";
    static final String KEY_USERID = "userid";
    private static LayoutInflater inflater=null;
    public ArrayList<HashMap<String,String>> data;
	
    /*
     * The "constructor" for this activity
     * @param instanceState The instance state.
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//super.setHeader(R.string.title_activity_comments);
		//super.setContentResourceID(R.layout.activity_comments);
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comments);
		
		int id = getIntent().getIntExtra("id", 0);
		comments = DatabaseHandler.getComments(id);
		displayComments();
	}

	/*
     * The eventhandler for the phone menu-button pressed
     * @param menu The menu
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comments, menu);
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
	        case R.id.menuitem_search:
	        	onSearchRequested();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/*
     * Invoked when Searchbutton pressed
     * @returns If the search bar should be shown or not.
     */
	@Override
	public boolean onSearchRequested() {
	    return super.onSearchRequested();
	}
	
	/*
     * Shows and fetches the comments into the GUI
     */
	public void displayComments(){

		ArrayList<HashMap<String, String>> csList = new ArrayList<HashMap<String, String>>();

		final ListView listview = (ListView) findViewById(R.id.listview);
		for (int i = 0; i < comments.size(); i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(KEY_COMMENT, StringUtility.convertHTMLtoString(comments.get(i).getText()));
		    map.put(KEY_USERNAME, DatabaseHandler.getUserById(comments.get(i).getOwnerId()).getDisplay_name());
	        map.put(KEY_USERID, Integer.toString(comments.get(i).getOwnerId()));
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
		
		/*
		 * Gets the number of items
		 * @returns The number of ites in the list view.
		 */
		public int getCount() {
	        return data.size();
	    }
		
		/*
		 * Gets an item in the list view at position
		 * @returns The item.
		 * @param position The position
		 */
		public Object getItem(int position) {
	        return position;
	    }

		/*
		 * Gets the item id in the list view at position
		 * @returns The item id.
		 * @param position The position
		 */
		@Override
		public long getItemId(int position) {
			return position;
		}

		/*
		 * Check if the set has stable ids.
		 * @returns True if stable ids..
		 */
		@Override
		public boolean hasStableIds() {
			return true;
		}
		
		/*
		 * Gets the list view graphical represntation
		 * @param position The current item position
		 * @param convertView The view to convert
		 * @param parent The parent item.
		 * @returns The view
		 */
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View vi=convertView;
	        if(convertView==null)
	            vi = inflater.inflate(R.layout.list_row_comments, null);
	 
	        TextView text = (TextView)vi.findViewById(R.id.text);
	        Button bt = (Button) vi.findViewById(R.id.gotoCommentsProfileButton); 
	 
	        HashMap<String, String> q = new HashMap<String, String>();
	        q = data.get(position);
	        bt.setText(q.get(KEY_USERNAME));
	 
	        // Setting all values in listview
	        text.setText(q.get(KEY_COMMENT));
	        
	        final String user_id = q.get(KEY_USERID);
	        
	        bt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
		        	Intent intent = new Intent(getThis(), UserProfileActivity.class);
					intent.putExtra("ownerId", user_id);
					startActivity(intent);					
				}

			
			});
	        
	        return vi;
	    }
	    
	    
	
	}
	/*
	 * Returns the current comments activity object.
	 */
	private CommentsActivity getThis() {
		return this;
	}
}
