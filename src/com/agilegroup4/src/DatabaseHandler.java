package com.agilegroup4.src;

import android.content.Context;
import android.database.Cursor;
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
		queryUsersTableForID(13);
		dbLoader.close();
	}
	
	// check for a user with this ID exists in the DB
	public boolean userExists(int userID){
		
		Cursor cursor = queryUsersTableForID(userID);
		
		if(cursor != null){
			cursor.moveToFirst();
		
			if (cursor.getCount() == 1){
				cursor.close();
				return true;
			} 	
			//cursor.getString(cursor.getColumnIndex("display_name"));
		} else {
			cursor.close();
			return false;
		}
		
		cursor.close();
		return false;
	}
	
	// 
	public Cursor queryUsersTableForID (int userID){
		Cursor cur = db.rawQuery("select * from users where id = ?", new String[] {Integer.toString(userID)});
		//System.out.println("Query result: " + cur.getCount());
		
		return cur;
	}
}
