package com.agilegroup4.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.agilegroup4.helpers.Helper;
import com.agilegroup4.model.Answer;
import com.agilegroup4.model.Question;

public class QuestionActivity extends Activity {

	// Current question
	private Question question;
	
	// Current answers for the question
	private ArrayList<Answer> answers;
	
	// List of questions from QuestionOverview
	private ArrayList<Question> questions;
	
	// The max lines of the questionbody
	public final static int MAX_LINES_WITH_COMMENTS = 15;
	public final static int MAX_LINES_WITHOUT_COMMENTS = 20;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		
		int questionId = getIntent().getIntExtra("questionId", 0);
		
		questions = DatabaseHandler.getQuestions();
		question = findQuestion(questionId);
		answers = question.getAnswers();
		displayQuestion();
		displayAnswers();
	}
	
	/**
	 * Displays question title and body
	 */
	private void displayQuestion(){
		TextView title = (TextView) findViewById(R.id.question_title);
		TextView body = (TextView) findViewById(R.id.question_body);
		Button commentsButton = (Button) findViewById(R.id.comments_button);
		title.setText(question.getTitle());
		Helper h = new Helper();
		body.setText(h.convertHTMLtoString(question.getBody()));
		title.setTypeface(null,Typeface.BOLD);
		body.setMovementMethod(new ScrollingMovementMethod());
		
		if (answers.size() > 0)
			body.setMaxLines(MAX_LINES_WITH_COMMENTS);
		else
			body.setMaxLines(MAX_LINES_WITHOUT_COMMENTS);
		
		if (hasComment(question)) {
			commentsButton.setVisibility(View.VISIBLE);
			//int com = size of comments
			int com = 3;
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
	
	public boolean hasComment(Question q){
		return true;
	}
	
	public boolean hasComment(Answer a){
		return true;
	}
	
	
	/**
	 * Display the answers
	 */
	public void displayAnswers() {
		TextView answ = (TextView) findViewById(R.id.question_answers);
		int ans = answers.size();
		String answertext = " answers";
		if (ans == 1)
			answertext = " answer";
		answ.setText(ans + answertext);
		answ.setTypeface(null,Typeface.BOLD);
		
		// HashMap for connecting answer id with position in the list for the question
		final HashMap<Integer,Integer> ids = new HashMap<Integer,Integer>();
		// HashMap needed for displaying the bodies of the answers in the listview
		final ArrayList<String> bodies = new ArrayList<String>();
		final ListView listview = (ListView) findViewById(R.id.listview);
		Helper help = new Helper();
		for (int i = 0; i < answers.size(); i++) {
			bodies.add(help.convertHTMLtoString(answers.get(i).getBody()));
			ids.put(i, answers.get(i).getId());
		}

		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, bodies);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				
				if(hasComment(answers.get((int) id))){
					Intent intent = new Intent(getThis(), CommentsActivity.class);
					// Send along answer id to CommentsActivity
					intent.putExtra("id", ids.get((int) id));
					startActivity(intent);
				}
			}
		});
	}
	
	/**
	 * Search for a question
	 * @param qId question id
	 * @return the question
	 */
	private Question findQuestion(int qId){
		Question q;
		for (int i = 0; i < questions.size(); i++) {
			q = questions.get(i);
			if (q.getId() == qId)
				return q;
		}
		return null;
	}
	
	private QuestionActivity getThis(){
		return this;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question, menu);
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
	        default:
	            return super.onOptionsItemSelected(item);
	    }
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

}
