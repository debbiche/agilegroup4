package com.agilegroup4.src;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * This class is responsible for loading the database when 
 * the application is started for the first time
 */
public class DatabaseLoader extends SQLiteOpenHelper {

	private SQLiteDatabase db;
	private Context context;
	private String dbPath = "data/data/com.agilegroup4.src/databases";
	private String dbName = "StackOverflow";
	private InputStream inputDB;
	private OutputStream outputDB;

	public DatabaseLoader(Context context, String name, CursorFactory factory,
			int version) throws Exception {
		super(context, name, factory, version);
		this.context = context;
		loadDB();
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
					SQLiteDatabase.OPEN_READONLY
							| SQLiteDatabase.NO_LOCALIZED_COLLATORS);
			addIndexes();
			return;
		} else {
			System.out.println("Database not loaded, loading it now...");
			this.getWritableDatabase(); // open empty DB
			inputDB = context.getAssets().open("so.sqlite"); // load db file
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
					SQLiteDatabase.OPEN_READONLY
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

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	private void addIndexes(){
		Cursor indexes = db.rawQuery("CREATE INDEX PIndex ON posts " +
				"(parent_id, post_type_id,title,body)", null);
		indexes.close();
	}

}
