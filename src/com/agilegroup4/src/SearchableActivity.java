package com.agilegroup4.src;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import com.agilegroup4.model.Question;

public class SearchableActivity extends ListActivity {

	// List of questions from QuestionOverview
	private ArrayList<Question> questions;
	private ArrayList<Question> searchResultQuestions;

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
		//if(intent == null || intent.getExtras() == null){
			//return;
		//}
		//System.out.println(intent.getStringExtra("action"));
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			doSearch(query);
			/*Intent intent2 = new Intent(this, QuestionOverviewActivity.class);
			intent.putExtra("questionsData", searchResultQuestions);
			startActivity(intent2);
			 */
		}
	}
	//Searches questions titles and body for input search query and returns ArrayList of questions
	//to QuestionsOverview for presentation
	private void doSearch(String query) { 
		searchResultQuestions = new ArrayList<Question>();
		questions =  DatabaseHandler.getQuestions();
		for(int i = 0; i < questions.size(); i++){
			if(questions.get(i).getTitle().toLowerCase().contains(query.toLowerCase()) 
			|| questions.get(i).getBody().toLowerCase().contains(query.toLowerCase()))
			{
				//System.out.println("Found question with title: " + questions.get(i).getTitle());
				System.out.println("test");
				searchResultQuestions.add(questions.get(i));
				System.out.println("Found question with title: " + searchResultQuestions.get(0).getTitle());
			}
		}
	
	}
		

}