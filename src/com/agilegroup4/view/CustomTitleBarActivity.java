package com.agilegroup4.view;

import com.agilegroup4.src.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.widget.TextView;

public class CustomTitleBarActivity extends Activity {

	private String _activityTitle;
	private int _layoutResID;
	
	protected void setContentResourceID(int layoutResID)
	{
		_layoutResID = layoutResID;
	}
	
	protected void setHeader(int resourceStringID)
	{
		_activityTitle = getResources().getString(resourceStringID);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(_layoutResID);


        if ( customTitleSupported ) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.activity_custom_title_bar);
            }

        final TextView myTitleText = (TextView) findViewById(R.id.myTitle);
        if ( myTitleText != null ) {
        	String headerTitle = _activityTitle != null ? _activityTitle : getResources().getString(R.string.customTitleBarHeaderDefault);
            myTitleText.setText(headerTitle);
        }
	}

}
