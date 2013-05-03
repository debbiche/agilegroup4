package com.agilegroup4.src;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.agilegroup4.helpers.StringUtility;
import com.agilegroup4.model.Answer;
import com.agilegroup4.model.Question;


/*
 * The question activity showing the provided question.
 */
public class QuestionActivity extends Activity {

	ProgressDialog progress;
	
	// Current question
	private Question question;
	
	// Current answers for the question
	private ArrayList<Answer> answers;
	
	// The max lines of the questionbody
	public final static int MAX_LINES_WITH_COMMENTS = 10;
	public final static int MAX_LINES_WITHOUT_COMMENTS = 20;
	
    static final String KEY_ANSWER = "answer"; // parent node
    static final String KEY_NR_OF_COMMENTS = "nr_of_comments";
    private static LayoutInflater inflater=null;
    public ArrayList<HashMap<String,String>> data;
	
    /*
     * The "constructor" for this activity
     * @param instanceState The instance state.
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//super.setHeader(R.string.title_activity_question);
		//super.setContentResourceID(R.layout.activity_question);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);

		progress = new ProgressDialog(this);
		getIntentData();
		loadAnswers();
	}
	
	/*
	 * Gets and handles the intent data when loading this activity.
	 */
	public void getIntentData(){
	    Intent i = getIntent();
	    if(i != null && i.hasExtra("question")){   
	    	Bundle b = i.getExtras(); 
	    	question = b.getParcelable("question"); 
	    }
	}
	
	/*
	 * Invokes a asyncronously method loading answers.
	 * Showing a progress bar during execution.
	 */
	private void loadAnswers(){
		progress.setTitle("Please Wait");
		progress.setMessage("Loading questions...");
		progress.show();
		new loadAnswers().execute();
	}
	
	/*
	 * Displays question title and body
	 */
	private void displayQuestion(){
		TextView title = (TextView) findViewById(R.id.question_title);
		TextView body = (TextView) findViewById(R.id.question_body);
		Button commentsButton = (Button) findViewById(R.id.comments_button);
		title.setText(question.getTitle());
		body.setText(StringUtility.convertHTMLtoString(question.getBody()));
		title.setTypeface(null,Typeface.BOLD);
		body.setMovementMethod(new ScrollingMovementMethod());
		
		if (answers.size() > 0)
			body.setMaxLines(MAX_LINES_WITH_COMMENTS);
		else
			body.setMaxLines(MAX_LINES_WITHOUT_COMMENTS);
		
		if (hasComment(question)) {
			commentsButton.setVisibility(View.VISIBLE);
			int com = question.getCommentCount();
			String buttontext = " comments";
			if (com == 1)
				buttontext = " comment";
			commentsButton.setText(com + buttontext);
			
			
			commentsButton.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View v) {
					Intent intent = new Intent(getThis(), CommentsActivity.class);
					// Send along question id to CommentsActivity
					intent.putExtra("id", question.getId());
					startActivity(intent);
		        }
		    });
		}
		
	}
	
	/*
	 * Does the question have comments?
	 * @param q The question
	 * @returns True if it has comments.
	 */
	public boolean hasComment(Question q){
		return (q.getCommentCount() > 0);
	}
	
	/*
	 * Does the answer have comments?
	 * @param a The answer
	 * @returns True if it has comments.
	 */
	public boolean hasComment(Answer a){
		return (a.getCommentCount() > 0);
	}
	
	
	/*
	 * Displays the answers
	 */
	@SuppressLint("UseSparseArrays")
	public void displayAnswers() {
		TextView answ = (TextView) findViewById(R.id.question_answers);
		int ans = answers.size();
		String answertext = " answers";
		if (ans == 1)
			answertext = " answer";
		answ.setText(ans + answertext);
		answ.setTypeface(null,Typeface.BOLD);
		
		ArrayList<HashMap<String, String>> asList = new ArrayList<HashMap<String, String>>();
		
		// HashMap for connecting answer id with position in the list for the question
		final HashMap<Integer,Integer> ids = new HashMap<Integer,Integer>();
		final ListView listview = (ListView) findViewById(R.id.listview);
		for (int i = 0; i < answers.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(KEY_ANSWER, StringUtility.convertHTMLtoString(answers.get(i).getBody()));
            map.put(KEY_NR_OF_COMMENTS, "Comments: " + Integer.toString(answers.get(i).getCommentCount()));
			asList.add(map);
			ids.put(i, answers.get(i).getId());
		}

		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, asList);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				
				if(hasComment(answers.get((int) id))){
					Intent intent = new Intent(getThis(), CommentsActivity.class);
					// Send along answer id to CommentsActivity
					intent.putExtra("id", ids.get((int) id));
					startActivity(intent);
				}
			}
		});
	}
	
	/*
	 * Returns the current question activity object.
	 */
	private QuestionActivity getThis(){
		return this;
	}

	/*
     * The eventhandler for the phone menu-button pressed
     * @param menu The menu
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question, menu);
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
	        default:
	            return super.onOptionsItemSelected(item);
	    }
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
	            vi = inflater.inflate(R.layout.list_row_question, null);
	 
	        TextView answer = (TextView)vi.findViewById(R.id.answer); 
	        TextView nr_of_comments = (TextView)vi.findViewById(R.id.nr_of_comments);
	 
	        HashMap<String, String> q = new HashMap<String, String>();
	        q = data.get(position);
	 
	        // Setting all values in listview
	        answer.setText(q.get(KEY_ANSWER));
	        nr_of_comments.setText(q.get(KEY_NR_OF_COMMENTS));
	        
	        return vi;
	    }

	}	private class loadAnswers extends AsyncTask<Object, Object, Object>{

		/*
		 * (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected Object doInBackground(Object... params) {
			answers = DatabaseHandler.getAnswers(question);
			System.out.println("answers: " + answers.size());
			return null;
		}
	
		/*
		 * (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
	     protected void onPostExecute(Object params) {
			progress.dismiss();
			displayQuestion();
			displayAnswers();
	     }
	 }

}
