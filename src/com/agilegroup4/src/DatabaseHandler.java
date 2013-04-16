package com.agilegroup4.src;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
		queryForUserID(13);
		dbLoader.close();
	}
	
	public void queryForUserID (int userID){
		Cursor cur = db.rawQuery("select * from users where id = ?", new String[] {Integer.toString(userID)});
		System.out.println("Query result: " + cur.getCount());
	}
	
		
	
}
