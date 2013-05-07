package com.agilegroup4.src;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * handler for loading the tag database
 * */
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
	
	/**
	 * Checks if the db has been previously loaded. If not then it is loaded
	 * into a newly (empty) created database named by the attributes <b>dbPath</b> and <b>dbName</b>.
	 */
	private void loadDB() throws Exception {
		if (dbExists()) {
			System.out
					.println("Tags database already exists, not loading it again!");
			this.db = SQLiteDatabase.openDatabase(dbPath + dbName, null,
					SQLiteDatabase.OPEN_READWRITE
							| SQLiteDatabase.NO_LOCALIZED_COLLATORS);
			addIndexes();
			return;
		} else {
			System.out.println("Tags database not loaded, loading it now...");
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

	/**
	 * Checks if the db exists or not.
	 * @return <b>true</b> if the db exists already; <b>false</b> otherwise
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
}
