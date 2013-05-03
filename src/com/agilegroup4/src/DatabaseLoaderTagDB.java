package com.agilegroup4.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Environment;
import android.util.Log;

public class DatabaseLoaderTagDB extends SQLiteOpenHelper {
	
	private SQLiteDatabase db;
	private Context context;
	private static String dbPath = "data/data/com.agilegroup4.src/databases";
	private String dbName = "StackOverflowTags";
	private InputStream inputDB;
	private OutputStream outputDB;

	public DatabaseLoaderTagDB(Context context, String name, CursorFactory factory, int version) throws Exception {
		super(context, name, factory, version);
		this.context = context;
		loadDB();
		// super.close();

	}
	
	/*
	 * Checks if the db has been previously loaded. If not then it is loaded
	 * into a newly (empty) created database
	 */
	private void loadDB() throws Exception {
		if (dbExists()) {
			System.out
					.println("Database already exists, not loading it again!");
			this.db = SQLiteDatabase.openDatabase(dbPath + dbName, null,
					SQLiteDatabase.OPEN_READWRITE
							| SQLiteDatabase.NO_LOCALIZED_COLLATORS);
			addIndexes();
			return;
		} else {
			System.out.println("Database not loaded, loading it now...");
			this.getWritableDatabase(); // open empty DB
			inputDB = context.getAssets().open("tags.sqlite"); // load db file
			outputDB = new FileOutputStream(dbPath + dbName);
			byte[] buffer = new byte[1024];
			int length = inputDB.read(buffer);
			while (length > 0) { // copy db file contents into the new empty db
				outputDB.write(buffer, 0, length);
				length = inputDB.read(buffer);
			}
			outputDB.flush();
			inputDB.close();
			outputDB.close();

			db = SQLiteDatabase.openDatabase(dbPath + dbName, null,
					SQLiteDatabase.OPEN_READWRITE
							| SQLiteDatabase.NO_LOCALIZED_COLLATORS);
			addIndexes();
		}

	}

	public SQLiteDatabase getDb() {
		return this.db;
	}

	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}

	/*
	 * Checks if the db exists or not
	 */
	private boolean dbExists() {
		try {
			db = SQLiteDatabase.openDatabase(dbPath + dbName, null,
					SQLiteDatabase.OPEN_READONLY
							| SQLiteDatabase.NO_LOCALIZED_COLLATORS);
			if (db != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	private void addIndexes(){
		Cursor indexes = db.rawQuery("CREATE INDEX PIndex ON tags " +
				"(id, tag, related)", null);
		indexes.close();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void copyDBToSDCard() {
	    try {
	        InputStream myInput = new FileInputStream(dbPath+"StackOverflowTags");

	        File file = new File(Environment.getExternalStorageDirectory().getPath()+"/"+"tags.sqlite");
	        
	        if (!file.exists()){
	            try {
	                file.createNewFile();
	            } catch (IOException e) {
	                Log.i("FO","File creation failed for " + file);
	            }
	        }

	        OutputStream myOutput = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()+"/"+"tags.sqlite");

	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = myInput.read(buffer))>0){
	            myOutput.write(buffer, 0, length);
	        }

	        //Close the streams
	        myOutput.flush();
	        myOutput.close();
	        myInput.close();
	        Log.i("FO","copied");

	    } catch (Exception e) {
	        Log.i("FO","exception="+e);
	    }


	}
}
