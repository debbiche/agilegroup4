package com.agilegroup4.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

<<<<<<< HEAD
import com.agilegroup4.model.Question;
=======
import com.agilegroup4.helper.Question;
>>>>>>> 5f575dac0068c5d9506400eeea8f827c9f8d9648

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class QuestionOverviewActivity extends Activity {
	
	// Controls the number of questions that are displayed
	public final static int NR_OF_POSTS = 10;

	// This is the Adapter being used to display the list's data
	SimpleCursorAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_overview);

		SharedPreferences settings = getSharedPreferences(
				LoginActivity.PREFS_NAME, 0);
		int userID = settings.getInt("userID", 0);

		displayQuestions();
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
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void displayQuestions() {
		DatabaseHandler.queryQuestions();
		ArrayList<Question> questions = DatabaseHandler.getQuestions();
		final HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		final ListView listview = (ListView) findViewById(R.id.listview);
		final ArrayList<String> titles = new ArrayList<String>();
		for (int i = 0; i < NR_OF_POSTS; i++) {
			titles.add(questions.get(i).getTitle());
			map.put(questions.get(i).getTitle(), questions.get(i).getId());
		}

		final StableArrayAdapter adapter = new StableArrayAdapter(this,
				android.R.layout.simple_list_item_1, titles);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				/* Does not work at the moment, need different API level.
				view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
							@Override
							public void run() {
								titles.remove(item);
								adapter.notifyDataSetChanged();
								view.setAlpha(1);
							}
						});*/
				Intent intent = new Intent(getThis(), QuestionActivity.class);
				intent.putExtra("questionId", map.get(adapter.getItem(position)));
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
	
	public void onBackPressed(){
		// call next activity
		Intent intent = new Intent(this, MainMenuActivity.class);
		startActivity(intent);
	}

}
