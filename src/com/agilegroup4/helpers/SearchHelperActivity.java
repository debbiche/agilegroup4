package com.agilegroup4.helpers;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.agilegroup4.model.Question;
import com.agilegroup4.src.DatabaseHandler;

public class SearchHelperActivity extends ListActivity {

	// List of questions from QuestionOverview
	private ArrayList<Question> questions;
	private ArrayList<Question> searchResultQuestions;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handleIntent(getIntent());
	}

	public void onNewIntent(Intent intent) { 
		setIntent(intent); 
		handleIntent(intent); 
	} 

	public void onListItemClick(ListView l, View v, int position, long id) { 
		// Call detail activity for clicked entry
	}

	// Get the intent, verify the action and get the query
	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			doSearch(query);
		}
	}
	private void doSearch(String query) { 
		questions =  DatabaseHandler.getQuestions();
		for(int i = 0; i < 3; i++){ //i < questions.size()
			if(questions.get(i).getTitle().contains(query))
			{
				System.out.println("Found question with title: " + questions.get(i).getTitle());
				searchResultQuestions.add(questions.get(i));
			}
		}
	}
	/* possible solution, send extra content to questionsOverview

		Intent intent = new Intent(getThis(), QuestionsOverview.class);
		intent.putExtra("action", "SearchHits");
		startActivity(intent);

	 */

}