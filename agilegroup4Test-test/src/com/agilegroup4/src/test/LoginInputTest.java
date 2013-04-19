package com.agilegroup4.src.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.agilegroup4.src.LoginActivity;



public class LoginInputTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	public LoginInputTest() {
		super("com.agilegroup4.src", LoginActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp(); 
	}

	/**
	 * Tests if userLogin field input is saved correctly
	 */	
	public void testLoginInput() throws Throwable {

		// Open login activity
		LoginActivity loginActivity = getActivity();

		//Get the input field from the userlogin activity
		final EditText userInputText = (EditText) loginActivity.findViewById(com.agilegroup4.src.R.id.text_login_username);
		
		//Create test input
		final String input = "1";
		
		loginActivity.runOnUiThread(new Runnable() {
			@Override
			public void run(){

				//Insert input into input field
				userInputText.setText(input);	
				
				//Assert that input field contains input
				Assert.assertTrue(userInputText.getText().toString().equals(input));
			}
		});		
	}
}

	
