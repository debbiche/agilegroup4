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
import android.view.Gravity;
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

public class QuestionOverviewActivity extends Activity {
	
	// HashMap for connecting question id with position in the list for the question
	// Used for unit testing
	public HashMap<Integer,Question> idsTestMap;
	
	// Controls the number of questions that are displayed
	public final static int NR_OF_POSTS = 10;

	// This is the Adapter being used to display the list's data
	SimpleCursorAdapter mAdapter;
	
	// Containing the questions in the question overview
	ArrayList<Question> questions;
	
	// True if heat mode = ON
	public static boolean HEAT = true;
	public static String RED = "#FF0000";
	public static String YELLOW = "#FFFF00";
	public static String GREEN = "#00FF00";
	public static int REDSCORE = 50;
	public static int YELLOWSCORE = 140;
	
    static final String KEY_QUESTION = "question";
    static final String KEY_TITLE = "title";
    static final String KEY_TAGS = "answers";
    static final String KEY_SCORE = "score";
    static final String KEY_COLOUR = "colour";
    private static LayoutInflater inflater=null;
    public ArrayList<HashMap<String,String>> data;
    
    /*
     * The "constructor" for this activity
     * @param instanceState The instance state.
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//super.setHeader(R.string.title_activity_question_overview);
		//super.setContentResourceID(R.layout.activity_question_overview);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_overview);

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

	/*
     * The eventhandler for the phone menu-button pressed
     * @param menu The menu
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_overview, menu);
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

	public void displayQuestions() {
	
		DatabaseHandler.queryQuestions(SearchableActivity.NUMBER_OF_ALLOWED_QUESTIONS);
		//If questions has been instanced before dont do it again.
		if(questions == null)
			questions = DatabaseHandler.getQuestions();
		
		if(questions.size() == 0) {
			// create toast for sorting info (see CR02)
			Context context = getApplicationContext();
			CharSequence text = "Your search did not return any results, please try something else.";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
		
		ArrayList<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();
		
		// Make sure that we are expecting too many questions
		int nrOfPosts = NR_OF_POSTS;
		if(questions.size() <= nrOfPosts)
			nrOfPosts = questions.size();
		
		// HashMap for connecting question id with position in the list for the question
		final HashMap<Integer,Question> ids = new HashMap<Integer,Question>();
		final ListView listview = (ListView) findViewById(R.id.listview);
		for (int i = 0; i < nrOfPosts; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(KEY_TITLE, questions.get(i).getTitle());
            map.put(KEY_TAGS, "Tags: " + questions.get(i).getTags());
            map.put(KEY_SCORE, "Score: " + Integer.toString(questions.get(i).getScore()));
            map.put(KEY_COLOUR, calculateColour(questions.get(i)));
			ids.put(i,questions.get(i));
			qsList.add(map);
		}
		idsTestMap = ids;
		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, qsList);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,					int position, long id) {
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
			
		/*
		 * Stableadapter for the question elements in the listview.
		 */
		public StableArrayAdapter(Activity a, int textViewResourceId,
				ArrayList<HashMap<String, String>> objects) {
			data = objects;
			inflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		/*
		 * (non-Javadoc)
		 * @see android.widget.Adapter#getCount()
		 */
		public int getCount() {
	        return data.size();
	    }
		
		/*
		 * (non-Javadoc)
		 * @see android.widget.Adapter#getItem(int)
		 */
		public Object getItem(int position) {
	        return position;
	    }

		/*
		 * (non-Javadoc)
		 * @see android.widget.Adapter#getItemId(int)
		 */
		@Override
		public long getItemId(int position) {
			return position;
		}

		/*
		 * (non-Javadoc)
		 * @see android.widget.BaseAdapter#hasStableIds()
		 */
		@Override
		public boolean hasStableIds() {
			return true;
		}
		
		/*
		 * (non-Javadoc)
		 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View vi=convertView;
	        if(convertView==null)
	            vi = inflater.inflate(R.layout.list_row, null);
	 
	        TextView title = (TextView)vi.findViewById(R.id.title); // title
	        TextView tags = (TextView)vi.findViewById(R.id.tags); // tags
	        TextView score = (TextView)vi.findViewById(R.id.score); // score
	 
	        HashMap<String, String> q = new HashMap<String, String>();
	        q = data.get(position);
	 
	        // Setting all values in listview
	        title.setText(q.get(QuestionOverviewActivity.KEY_TITLE));
	        tags.setText(q.get(QuestionOverviewActivity.KEY_TAGS));
	        score.setText(q.get(QuestionOverviewActivity.KEY_SCORE));
	        
	        if(HEAT){
	        	String col = q.get(QuestionOverviewActivity.KEY_COLOUR);
	        	if(col.equals(RED)){
	        		vi.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_bg_red));
	        	}
	        	else if(col.equals(YELLOW)){
	        		vi.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_bg_yellow));
	        	}
	        	else{
	        		vi.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_bg_green));
	        	}
	        }
	        
	        return vi;
	    }

	}
	
	/*
	 * sort questions in questions object by date
	 * for more information see the LatestComparator class
	 */
	public void filterLatest(View view){
		
		Collections.sort(questions, new LatestComparator());

		// create toast for sorting info (see CR02)
		Context context = getApplicationContext();
		CharSequence text = "Sorting questions by creation date.";
		int duration = Toast.LENGTH_LONG;

		Toast.makeText(context, text, duration).show();		
		
		// refresh list
		displayQuestions();
	}
	
	/*
	 * sort questions in questions object by importance
	 * for more information see the ImportantComparator class
	 * @param view The view invoking this method.
	 */
	public void filterImportant(View view){
		
		Collections.sort(questions, new ImportantComparator());
		
		// create toast for sorting info (see CR02)
		Context context = getApplicationContext();
		CharSequence text = "Sorting questions by score, view count and favorite count.";
		int duration = Toast.LENGTH_LONG;

		Toast.makeText(context, text, duration).show();
		
		// refresh list
		displayQuestions();
	}
	
	/*
	 * sort questions in questions object by amount of answers and comments
	 * and if the answer was accepted
	 * for more information see the AnsweredComparator class
	 * @param view The view invoking this method.
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
	
	/**
	 * Turns the heat feature on/off.
	 * @param view The view invoking this method.
	 */
	public void handleHeatOnClick(View view){
		if(HEAT){
			HEAT = false;
			displayQuestions();
		}
		else{
			HEAT = true;
			displayQuestions();
		}
	}
	
	/**
	 * Calculates the colour corresponding to score for a question
	 * @param q the question
	 * @return the score
	 */
	private String calculateColour(Question q){
		int score = 0;
		String colour = GREEN;
		score = q.getViewCount();
		score += (q.getScore() * 50);
		score += (q.getFavoriteCount() * 100);
		if(score<REDSCORE)
			colour = RED;
		else if(score<YELLOWSCORE)
			colour = YELLOW;
		return colour;
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	public void onBackPressed(){
		// call next activity
		Intent intent = new Intent(this, MainMenuActivity.class);
		startActivity(intent);
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onSearchRequested()
	 */
	@Override //invoked when Searchbutton pressed, just for testing
	public boolean onSearchRequested() {
	    return super.onSearchRequested();
	}

}
