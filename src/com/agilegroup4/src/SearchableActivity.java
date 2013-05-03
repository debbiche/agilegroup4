package com.agilegroup4.src;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.agilegroup4.infrastructure.QuestionHandler;
import com.agilegroup4.model.QuestionList;
import com.agilegroup4.model.SearchMode;

public class SearchableActivity extends ListActivity {

	public static SearchMode SearchMode;

	/*
     * The "constructor" for this activity
     * @param instanceState The instance state.
     */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handleIntent(getIntent());
	}

	public void onNewIntent(Intent intent) { 
		setIntent(intent); 
		handleIntent(intent); 
	} 

	// Get the intent, verify the action and get the query
	private void handleIntent(Intent intent) {
		
		if (!Intent.ACTION_SEARCH.equals(intent.getAction()))
			return;
			
		String query = intent.getStringExtra(SearchManager.QUERY);
		
		switch(SearchMode){
			case QUESTION_FREETEXT:
				searchQuestionFreeText(query);
				break;
			case QUESTION_WITHTAGS:
				searchQuestionByTags(query, TagsOverviewActivity.tagQuery); //provide tag query when middle tag button pressed
				break;
			default:
				searchQuestionFreeText(query);
				break;
		}
	}
	
	//jSearches questions and sends QuestionList of questions
	//to QuestionsOverview for presentation
	private void searchQuestionFreeText(String query) { 
		QuestionList searchResultQuestions = QuestionHandler.searchForQuestions(query, 60);
		//Creates a bundle and parce the the search result QuestionList
		Bundle b = new Bundle();
        b.putParcelable("questionsData", searchResultQuestions); //Insert list in a Bundle object
		Intent intent = new Intent(this, QuestionOverviewActivity.class);
		//Includes the bundle and parced search result into the intent for search activity.
		intent.putExtras(b);
		startActivity(intent);
	}
		
	//Searches questions and sends QuestionList of questions
	//to QuestionsOverview for presentation
	private void searchQuestionByTags(String query, String tag) { 
		
		if (query.equals("0"))
			query = "";
		
		QuestionList searchResultQuestions = QuestionHandler.searchForQuestionsByTag(query, tag, 60);
		//Creates a bundle and parce the the search result QuestionList
		Bundle b = new Bundle();
        b.putParcelable("questionsData", searchResultQuestions); //Insert list in a Bundle object
		Intent intent = new Intent(this, QuestionOverviewActivity.class);
		//Includes the bundle and parced search result into the intent for search activity.
		intent.putExtras(b);
		startActivity(intent);
	}

}