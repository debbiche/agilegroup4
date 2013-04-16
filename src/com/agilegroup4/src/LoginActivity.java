package com.agilegroup4.src;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
	public final static String EXTRA_USERNAME = "com.agilegroup4.USERNAME";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		try {
			DatabaseLoader h = new DatabaseLoader(getApplicationContext(), null, null, 1);
			h.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("Testinggggg");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void loginButton(View view){
		// read username and save in String userID
		EditText editText = (EditText) findViewById(R.id.text_login_username);
		int userID = Integer.getInteger(editText.getText().toString());
		
		// pn = (EditText) findViewById(R.id.add_p_number);
    	//int pnumber = Integer.getInteger(pn.getText().toString());
		
		// TODO: check for username in DB
		
		// login successful --> open Question Overview Activity and hand over "user data"
		Intent intent = new Intent(this, QuestionOverviewActivity.class);
		intent.putExtra(EXTRA_USERNAME, userID);	// TODO: this might be a different field and should be obtained from DB
		startActivity(intent);
	}
	
}
