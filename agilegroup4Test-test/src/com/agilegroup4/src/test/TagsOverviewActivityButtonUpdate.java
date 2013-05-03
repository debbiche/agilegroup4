package com.agilegroup4.src.test;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.agilegroup4.src.TagsOverviewActivity;

public class TagsOverviewActivityButtonUpdate extends ActivityInstrumentationTestCase2<TagsOverviewActivity> {

	public TagsOverviewActivityButtonUpdate(Class<TagsOverviewActivity> classTest) {
		super(classTest);
	}

	protected void setUp() throws Exception {
		super.setUp(); 
	}
	
	/**
	 * TagsOverviewActivity button update.
	 */
	public void test() throws Throwable {

		// Open TagsOverviewActivity that includes the graphical adventure search tag buttons.
		TagsOverviewActivity myActivity = getActivity();
		
		// Get the middle buttons for TagsOverviewActivity
		final Button button3 = (Button) myActivity.findViewById(com.agilegroup4.src.R.id.button3);
		final Button button4 = (Button) myActivity.findViewById(com.agilegroup4.src.R.id.button4);
		final Button button5 = (Button) myActivity.findViewById(com.agilegroup4.src.R.id.button5);
		
		myActivity.runOnUiThread(new Runnable() {
		  @Override
		  public void run() {
			// Get previous text value on buttons  
			String button3Previousvalue = button3.getText().toString();
			String button4Previousvalue = button4.getText().toString();
			String button5Previousvalue = button5.getText().toString();
		    
			// Click rightmost button and update whole middle row with buttons.
			button5.performClick();
		  
			// Assert that all middle row button values have changed
		    Assert.assertTrue(!(button3.getText().toString().equals(button3Previousvalue)));
		    Assert.assertTrue(!(button4.getText().toString().equals(button4Previousvalue)));
		    Assert.assertTrue(!(button5.getText().toString().equals(button5Previousvalue)));
		  }
		});

		

		
	}

}
