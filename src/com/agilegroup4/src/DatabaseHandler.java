package com.agilegroup4.src;

import java.util.ArrayList;

import com.agilegroup4.helper.Question;
import com.agilegroup4.helper.User;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* DatabaseHandler handles database queries, inserts, deletes, ... */
public class DatabaseHandler {
	
	private DatabaseLoader dbLoader;
	private SQLiteDatabase db;
	private ArrayList<Question> questions = new ArrayList<Question>();

	public DatabaseHandler(Context context) {
		try {
			dbLoader = new DatabaseLoader(context, null, null, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db = dbLoader.getDb();	
	}
	
	public ArrayList<Question> getQuestions(){
		return questions;
	}
	
	/* returns a user object for a given id
	 * returns null if the user doesn't exist */
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
	
	// check if a user with this ID exists in the DB
	public boolean userExists(int userID){
		
		Cursor cursor = queryUsersTableForID(userID);
		
		if(cursor != null){
			cursor.moveToFirst();
		
			if (cursor.getCount() == 1){
				cursor.close();
				return true;
			} 	
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
	
	/*
	 * Call this method then you can just just loop over the questions list 
	 * object created here and use get the title of each questions to put in the
	 * list view elements.
	 */
	public void queryQuestions(){		
		Cursor questionsCursor = db.rawQuery("SELECT id,title,body FROM posts" +
											 " WHERE title <> \"NULL\"", null);
		questionsCursor.moveToFirst();
		while(questionsCursor.isAfterLast() == false){
			//System.out.println(cur.getString(0));
			questions.add(new Question(questionsCursor.getInt(0), 
									   questionsCursor.getString(1),
					                   questionsCursor.getString(2)));
			
//			Cursor answersCursor = db.rawQuery("SELECT id,body " +
//											   "FROM posts WHERE parent_id = ?",
//												new String[] {Integer.toString(questionsCursor.getInt(0))});
//			
//			answersCursor.moveToFirst();
//			while(answersCursor.isAfterLast() == false){
//				System.out.println("Answer: " + answersCursor.getString(1));
//				answersCursor.moveToNext();
//			}
			//System.out.println("###############################");
			//System.out.println("Answers found for " + questionsCursor.getInt(0) + " is " + answersCursor.getCount() );
			questionsCursor.moveToNext();
			
		}
		
	}
}
