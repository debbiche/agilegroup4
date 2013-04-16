package com.agilegroup4.src;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseLoader extends SQLiteOpenHelper{

	private SQLiteDatabase db;
	private Context context;
	private String dbPath = "data/data/com.agilegroup4.src/databases"; //Android DB folder
	private String dbName = "StackOverflow"; 
	private InputStream inputDB;
	private OutputStream outputDB;
	
	public DatabaseLoader(Context context, String name, CursorFactory factory, int version) throws Exception {
		super(context, name, factory, version);
		this.context = context;
		loadDB();
		super.close();
		 
		// TODO Auto-generated constructor stub
	}

	private void loadDB() throws Exception {
		this.getWritableDatabase(); //open empty DB
    	inputDB = context.getAssets().open("so.sqlite");
     	outputDB = new FileOutputStream(dbPath+dbName);
     	byte[] buffer = new byte[1024];
    	int length = inputDB.read(buffer);
    	while (length>0) {
    		outputDB.write(buffer, 0, length);
    		length = inputDB.read(buffer);
    	}
 
    	//Close the streams
    	//outputDB.flush();
    	inputDB.close();
    	outputDB.close();
 
    	db = SQLiteDatabase.openDatabase(dbPath+dbName, null, SQLiteDatabase.OPEN_READWRITE);
    	db.close();
    	
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
