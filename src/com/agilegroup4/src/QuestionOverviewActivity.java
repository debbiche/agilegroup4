package com.agilegroup4.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.agilegroup4.helpers.AnsweredComparator;
import com.agilegroup4.helpers.ImportantComparator;
import com.agilegroup4.helpers.LatestComparator;
import com.agilegroup4.model.Question;
import com.agilegroup4.view.CustomTitleBarActivity;

public class QuestionOverviewActivity extends CustomTitleBarActivity {
	
	// HashMap for connecting question id with position in the list for the question
	// Used for unit testing
	public HashMap<Integer,Question> idsTestMap;
	
	// Controls the number of questions that are displayed
	public final static int NR_OF_POSTS = 10;

	// This is the Adapter being used to display the list's data
	SimpleCursorAdapter mAdapter;
	
	// Containing the questions in the question overview
	ArrayList<Question> questions;
	
    static final String KEY_QUESTION = "question"; // parent node
    static final String KEY_TITLE = "title";
    static final String KEY_INFO = "answers";
    private static LayoutInflater inflater=null;
    public ArrayList<HashMap<String,String>> data;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.setHeader(R.string.title_activity_question_overview);
		super.setContentResourceID(R.layout.activity_question_overview);
		super.onCreate(savedInstanceState);

		SharedPreferences settings = getSharedPreferences(
				LoginActivity.PREFS_NAME, 0);

		getIntentData();
		displayQuestions();
	}
	
	public void getIntentData(){
	    Intent i = getIntent();
	    if(i != null && i.hasExtra("questionsData")){   
	    	Bundle b = i.getExtras(); //Get the intent's extras
	    	questions = b.getParcelable("questionsData"); //get our list
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_overview, menu);
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

	public void displayQuestions() {
	
		DatabaseHandler.queryQuestions(60);
		//If questions has been instanced before dont do it again.
		if(questions == null)
			questions = DatabaseHandler.getQuestions();
		
		ArrayList<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();
		
		// HashMap for connecting question id with position in the list for the question
		final HashMap<Integer,Question> ids = new HashMap<Integer,Question>();
		final ListView listview = (ListView) findViewById(R.id.listview);
		for (int i = 0; i < NR_OF_POSTS; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(KEY_TITLE, questions.get(i).getTitle());
            map.put(KEY_INFO, "Score: " + Integer.toString(questions.get(i).getScore())
            		+ " Comments: " + Integer.toString(questions.get(i).getCommentCount()));
			ids.put(i,questions.get(i));
			qsList.add(map);
		}
		idsTestMap = ids;
		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, qsList);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				//Intent intent = new Intent(getThis(), QuestionActivity.class);
				// Send along question id to QuestionActivity
				//intent.putExtra("questionId", ids.get((int) id));
				//startActivity(intent);
				//Creates a bundle and parce the the search result QuestionList
				Bundle b = new Bundle();
		        b.putParcelable("question", ids.get((int) id)); //Insert list in a Bundle object
				Intent intent = new Intent(getThis(), QuestionActivity.class);
				//Includes the bundle and parced search result into the intent for search activity.
				intent.putExtras(b);
				startActivity(intent);
			}

		});
	}
	
	private QuestionOverviewActivity getThis(){
		return this;
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
	            vi = inflater.inflate(R.layout.list_row, null);
	 
	        TextView title = (TextView)vi.findViewById(R.id.title); // title
	        TextView info = (TextView)vi.findViewById(R.id.info); // nr of answers
	 
	        HashMap<String, String> song = new HashMap<String, String>();
	        song = data.get(position);
	 
	        // Setting all values in listview
	        title.setText(song.get(QuestionOverviewActivity.KEY_TITLE));
	        info.setText(song.get(QuestionOverviewActivity.KEY_INFO));
	        return vi;
	    }

	}
	
	/*
	 * sort questions in questions object by date
	 * for more information see the LatestComparator class
	 * 
	 * */
	public void filterLatest(View view){
		
		Collections.sort(questions, new LatestComparator());

		// create toast for sorting info  (see CR02)
		Context context = getApplicationContext();
		CharSequence text = "Sorting questions by creation date.";
		int duration = Toast.LENGTH_SHORT;

		Toast.makeText(context, text, duration).show();		
		
		// refresh list
		displayQuestions();
	}
	
	/*
	 * sort questions in questions object by importance
	 * for more information see the ImportantComparator class
	 * 
	 * */
	public void filterImportant(View view){
		
		Collections.sort(questions, new ImportantComparator());
		
		// create toast for sorting info (see CR02)
		Context context = getApplicationContext();
		CharSequence text = "Sorting questions by score, view count and favorite count.";
		int duration = Toast.LENGTH_SHORT;

		Toast.makeText(context, text, duration).show();
		
		// refresh list
		displayQuestions();
	}
	
	/*
	 * sort questions in questions object by amount of answers and comments
	 * and if the answer was accepted
	 * for more information see the AnsweredComparator class
	 * 
	 * */
	public void filterAnswers(View view){
		
		Collections.sort(questions, new AnsweredComparator());

		// create toast for sorting info  (see CR02)
		Context context = getApplicationContext();
		CharSequence text = "Sorting questions by amount of answers, accepted answers and comments.";
		int duration = Toast.LENGTH_LONG;

		Toast.makeText(context, text, duration).show();
		
		// refresh list
		displayQuestions();
	}
	
	public void onBackPressed(){
		// call next activity
		Intent intent = new Intent(this, MainMenuActivity.class);
		startActivity(intent);
	}
	
	@Override //invoked when Searchbutton pressed, just for testing
	public boolean onSearchRequested() {
	    System.out.println("search pressed");
	    return super.onSearchRequested();
	}

}
