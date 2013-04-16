package com.agilegroup4.src;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/* DatabaseHandler handles database queries, inserts, deletes, ... uns */
public class DatabaseHandler {
	
	private DatabaseLoader dbLoader;
	private SQLiteDatabase db;

	public DatabaseHandler(Context context) {
		try {
			dbLoader = new DatabaseLoader(context, null, null, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db = dbLoader.getDb();				
		dbLoader.close();
	}
	
	public void queryForUserID (int userID){
		db.rawQuery("select * from users where id = ?", new String[] {Integer.toString(userID)});
	}
}
