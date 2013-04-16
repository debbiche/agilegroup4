package com.agilegroup4.src;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class LoginActivity extends Activity {

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
		 Log.v("Database","I started db");
		 System.out.println("Testinggggg");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public void loginButton(View view){
		String userID;

		
		
		Intent intent = new Intent(this, QuestionOverviewActivity.class);
		startActivity(intent);
	}
	
}
