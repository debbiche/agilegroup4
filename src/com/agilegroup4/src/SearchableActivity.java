package com.agilegroup4.src;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import com.agilegroup4.infrastructure.QuestionHandler;
import com.agilegroup4.model.Question;
import com.agilegroup4.model.QuestionList;

public class SearchableActivity extends ListActivity {

	//The search result of questions.
	private QuestionList searchResultQuestions;

	public ArrayList<Question> getSearchResultQuestions() {
		return searchResultQuestions;
	}

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
		if(intent == null || intent.getExtras() == null){
			return;
		}
		System.out.println(intent.getStringExtra("selectedTag"));
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY).toLowerCase();
			doSearch(query);
			/*Intent intent2 = new Intent(this, QuestionOverviewActivity.class);
			intent.putExtra("questionsData", searchResultQuestions);
			startActivity(intent2);
			 */
		}
	}
	
	//Searches questions and sends QuestionList of questions
	//to QuestionsOverview for presentation
	private void doSearch(String query) { 
		searchResultQuestions = QuestionHandler.searchForQuestions(query, 60);
		//Creates a bundle and parce the the search result QuestionList
		Bundle b = new Bundle();
        b.putParcelable("questionsData", searchResultQuestions); //Insert list in a Bundle object
		Intent intent = new Intent(this, QuestionOverviewActivity.class);
		//Includes the bundle and parced search result into the intent for search activity.
		intent.putExtras(b);
		startActivity(intent);
	}
		

}