package com.agilegroup4.src;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.agilegroup4.infrastructure.QuestionHandler;
import com.agilegroup4.model.QuestionList;
import com.agilegroup4.model.SearchMode;
import com.agilegroup4.model.Tag;

/*
 * The searchable activity handles all the search bar searches in this application.
 */
public class SearchableActivity extends ListActivity {

	public static SearchMode SearchMode;
	public static final int NUMBER_OF_ALLOWED_QUESTIONS = 100;
	// TODO MAYBE: Setting NUMBER_OF_ALLOWED_QUESTIONS to 200 or 210 (maybe other values as well of course,
	// will on a search for "help" return you to the main menu, and not execute the search... Really weird.
	
	/*
     * The "constructor" for this activity
     * @param instanceState The instance state.
     */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handleIntent(getIntent());
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onNewIntent(android.content.Intent)
	 */
	public void onNewIntent(Intent intent) { 
		setIntent(intent); 
		handleIntent(intent); 
	} 

	/*
	 * Gets the intent, verifys the action and get the query
	 * @param intent The intent for this activity
	 */
	private void handleIntent(Intent intent) {
		
		if (!Intent.ACTION_SEARCH.equals(intent.getAction()))
			return;
			
		String query = intent.getStringExtra(SearchManager.QUERY);
		
		switch(SearchMode){
			case QUESTION_FREETEXT:
				searchQuestionFreeText(query);
				break;
			case QUESTION_WITHTAGS:
				searchQuestionByTags(query, TagsOverviewActivity.mainTags); //provide tag query when middle tag button pressed
				break;
			default:
				searchQuestionFreeText(query);
				break;
		}
	}
	
	/*
	 * Searches questions by freetext and sends QuestionList of questions
	 * to QuestionsOverview for presentation
	 * @param query The search query.
	 */
	private void searchQuestionFreeText(String query) { 
		QuestionList searchResultQuestions = QuestionHandler.searchForQuestions(query, NUMBER_OF_ALLOWED_QUESTIONS);
		//Creates a bundle and parce the the search result QuestionList
		Bundle b = new Bundle();
        b.putParcelable("questionsData", searchResultQuestions); //Insert list in a Bundle object
		Intent intent = new Intent(this, QuestionOverviewActivity.class);
		//Includes the bundle and parced search result into the intent for search activity.
		intent.putExtras(b);
		startActivity(intent);
	}
		
	/*
	 * Searches questions by freetext and tag and sends QuestionList of questions
	 * to QuestionsOverview for presentation
	 * @param query The search query.
	 * @param tag The tag
	 */
	private void searchQuestionByTags(String query, ArrayList<Tag> tags) { 
		
		if (query.equals("0"))
			query = "";
		
		QuestionList searchResultQuestions = QuestionHandler.searchForQuestionsByTag(query, tags, NUMBER_OF_ALLOWED_QUESTIONS);
		//Creates a bundle and parce the the search result QuestionList
		Bundle b = new Bundle();
        b.putParcelable("questionsData", searchResultQuestions); //Insert list in a Bundle object
		Intent intent = new Intent(this, QuestionOverviewActivity.class);
		//Includes the bundle and parced search result into the intent for search activity.
		intent.putExtras(b);
		startActivity(intent);
	}

}