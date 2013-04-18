package com.agilegroup4.src;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* DatabaseHandler handles database queries, inserts, deletes, ... uns */
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
		//queryForUserID(13);
		queryQuestions();
		dbLoader.close();
	}
	
	public void queryForUserID (int userID){
		Cursor cur = db.rawQuery("select * from users where id = ?", new String[] {Integer.toString(userID)});
		System.out.println("Query result: " + cur.getCount());
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
