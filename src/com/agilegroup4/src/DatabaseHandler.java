package com.agilegroup4.src;

import com.agilegroup4.helper.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* DatabaseHandler handles database queries, inserts, deletes, ... */
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
	
	// returns a user object for a given id
	// returns null if the user doesn't exist
	public User getUserById(int userID){
		
		Cursor cursor = queryUsersTableForID(userID);
			
		if(cursor != null){
			User userHelper = new User();
			cursor.moveToFirst();
			
			// TODO: the integer casts might fail. Fix some day...
			userHelper.setId((int)cursor.getLong(cursor.getColumnIndex("id")));
			userHelper.setAbout_me(cursor.getString(cursor.getColumnIndex("about_me")));
			userHelper.setAge((int)cursor.getLong(cursor.getColumnIndex("age")));
			userHelper.setCreation_date(cursor.getString(cursor.getColumnIndex("creation_date")));
			userHelper.setDisplay_name(cursor.getString(cursor.getColumnIndex("display_name")));
			userHelper.setDown_votes((int)cursor.getLong(cursor.getColumnIndex("down_votes")));
			userHelper.setEmail_hash(cursor.getString(cursor.getColumnIndex("email_hash")));
			userHelper.setLast_access_date(cursor.getString(cursor.getColumnIndex("last_access_date")));
			userHelper.setLocation(cursor.getString(cursor.getColumnIndex("location")));
			userHelper.setReputation((int)cursor.getLong(cursor.getColumnIndex("reputation")));
			userHelper.setUp_votes((int)cursor.getLong(cursor.getColumnIndex("up_votes")));
			userHelper.setViews((int)cursor.getLong(cursor.getColumnIndex("views")));
			userHelper.setWebsite_url(cursor.getString(cursor.getColumnIndex("website_url")));
			
			cursor.close();
			return userHelper;
		}
		
		return null;
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
			return false;
		}
		
		cursor.close();
		return false;
	}
	
	// run a query in the user table and return results as cursor
	private Cursor queryUsersTableForID (int userID){
		Cursor cur = db.rawQuery("select * from users where id = ?", new String[] {Integer.toString(userID)});
		return cur;
	}
}
