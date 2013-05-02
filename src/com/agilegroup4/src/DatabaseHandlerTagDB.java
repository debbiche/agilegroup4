package com.agilegroup4.src;

import java.util.ArrayList;
import java.util.Scanner;

import com.agilegroup4.model.QuestionList;
import com.agilegroup4.model.Tag;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * Handles DB access on tag db...
 * */
public class DatabaseHandlerTagDB {
	public static int loaded = 0;
	protected static DatabaseLoaderTagDB dbLoader;
	protected static SQLiteDatabase db;
			
	public DatabaseHandlerTagDB(Context context) {

	}

	public static void initDB(Context context) {
		try {
			if (loaded == 0)
				dbLoader = new DatabaseLoaderTagDB(context, null, null, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		db = dbLoader.getDb();
		loaded = 1;
	}
	
	/*
	 *
	 */
	public static ArrayList<Tag> queryTags(int numberOfQuestions) {
		String[] para = new String[] { Integer.toString(numberOfQuestions) };
		
		String rawQuery = "SELECT * FROM tags " +
				"ORDER BY tag LIMIT ?";
		Cursor cursorQuestions = db.rawQuery(rawQuery, para);
		
		return parseTags(cursorQuestions);
	}
	
	private static ArrayList<Tag> parseTags(Cursor cursor){
		ArrayList<Tag> tags = new ArrayList<Tag>();
		Tag tag;
		String relatedTags;
		
		cursor.moveToFirst();
		//System.out.println(cursor.getString(1));
		
		while(!cursor.isAfterLast()){
			tag = new Tag(cursor.getInt(0), cursor.getString(1));
			relatedTags = cursor.getString(2);
			
			addRelatedTags(tag, relatedTags);
		
			tags.add(tag);
			cursor.moveToNext();
		}
		
		return tags;
	}
	
	// TODO: add error handling
	private static void addRelatedTags(Tag tag, String relatedTags){
		if (relatedTags == null){
			tag.addRelatedTag("");
			return;
		}
		
		Scanner scanner = new Scanner(relatedTags);
					
		while (scanner.hasNext()){
			tag.addRelatedTag(scanner.next().replace(", ", ""));
		}
		return;
	}
}
