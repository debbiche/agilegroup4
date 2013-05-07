package com.agilegroup4.src;

import java.util.ArrayList;
import java.util.Currency;

import com.agilegroup4.model.Tag;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Handles all DB access on tag database.
 */
public class DatabaseHandlerTagDB {
	public static int loaded = 0;
	protected static DatabaseLoaderTagDB dbLoader;
	protected static SQLiteDatabase db;
	public static int createTagsDB = 0;
	
	
	public DatabaseHandlerTagDB(Context context) {

	}

	/**
	 * initialize the database
	 */
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

	/**
	 * queries all tags from the tag database.
	 * @param numberOfQuestions limits the resulting query
	 * @return ArrayList
	 */
	public static ArrayList<Tag> queryTags(int numberOfQuestions) {
		String[] para = new String[] { Integer.toString(numberOfQuestions) };

		
		String rawQuery = "SELECT * FROM tags " +
				"ORDER BY tag";
		Cursor cursorQuestions = db.rawQuery(rawQuery, null);
		
		return parseTags(cursorQuestions);
	}

	/**
	 * 
	 * 
	 */
	private static ArrayList<Tag> parseTags(Cursor cursor) {
		ArrayList<Tag> tags = new ArrayList<Tag>();
		Tag tag;
		String relatedTags;

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			tag = new Tag(cursor.getInt(0), cursor.getString(1));
			relatedTags = cursor.getString(2);

			addRelatedTags(tag, relatedTags);

			tags.add(tag);
			cursor.moveToNext();
		}

		return tags;
	}

	/**
	 * TODO: error handling
	 * 
	 */
	private static void addRelatedTags(Tag tag, String relatedTags) {
		if (relatedTags == null) {
			return;
		}

		String[] relatedTagsArray = relatedTags.split(",");

		for (int i = 0; i < relatedTagsArray.length; i++) {
			tag.addRelatedTag(relatedTagsArray[i]);
		}

		return;
	}

	public static void createTagsDB() {
		// to be deleted after tags are extracted from DB
		HashMap<String, String> tagsHash = new HashMap<String, String>();

		ArrayList<String> listOfTags = new ArrayList<String>();
		String tags, tag;
		Cursor curUserID;
		Scanner scanner;

		curUserID = DatabaseHandler.db.rawQuery("SELECT tags from POSTS", null);

		curUserID.moveToFirst();
		while (!curUserID.isAfterLast()) {
			// tags = curUserID.getString(0);
			// scanner = new Scanner(tags);
			listOfTags.add(curUserID.getString(0));

			// while(scanner.hasNext()){
			// tag = scanner.next();
			// System.out.println(tag);
			// }
			// hashi.put(tag, related);
			curUserID.moveToNext();
		}
		System.out.println("Size of tags array list: " + listOfTags.size());
		curUserID.close();
		// delete above until comment

		
		HashMap<String, String> dupTagsHash = new HashMap<String, String>();

		
		for (int i = 0; i < listOfTags.size(); i++) { // Main loop
			String[] currentTags = listOfTags.get(i).split(">"); // split tags
			
			for (int k = 0; k < currentTags.length; k++) { // remove the "<"
				currentTags[k] = currentTags[k].substring(1);
			}
			
			for (int j = 0; j < currentTags.length; j++) {
				//currentTags[j] = currentTags[j].substring(1); // remove the '<'
				
				if (tagsHash.get(currentTags[j]) == null) { // tag hasn't been inserted before
					tagsHash.put(currentTags[j], buildRelatedTags(currentTags, currentTags[j]));

					} else
						dupTagsHash.put(currentTags[j], buildRelatedTags(currentTags, currentTags[j]));

				
				
				}
		} // end main loop
		
		
		System.out.println("Size of tags hash: " + tagsHash.size());
		System.out.println("Size of duplicate tags hash: " + dupTagsHash.size());

		
//		for (Entry<String, String> tagss : dupTagsHash.entrySet()) {
//			//String[] tagsss = tagss.getValue().split(",");
//			System.out.println("Tag: " + tagss.getKey()
//					+ " with related tags: " + tagss.getValue());
//		}
		
		// Set the weights
		for (Entry<String, String> tagss : tagsHash.entrySet()) {
			String[] currentTags = tagss.getValue().split(","); // split tags
			
			for (int j = 1; j < currentTags.length; j++) {
				//Cursor weights = db.rawQuery("SELECT * FROM posts WHERE CHARINDEX(?,tags)", new String[]{tagss.getKey()});
				
				int weight = getWeight(tagss.getKey(), currentTags[j], listOfTags);
				//System.out.println("Tag " + tagss.getKey() + " has weight " + weight + " with related tag " + currentTags[j]);
					
				db.execSQL("INSERT INTO imp VALUES (?,?,?)", new String[] {
						tagss.getKey(), currentTags[j],  Integer.toString(weight)});
				System.out.println("Inserted imp in db");
				}

			
		}

		
		

		int j = 1;
		for (Entry<String, String> tagss : tagsHash.entrySet()) {
			// System.out.println("Tag: " + tagss.getKey() +
			// " with related tags: " + tagss.getValue());
			db.execSQL("INSERT INTO tags VALUES (?,?,?)", new String[] {
					Integer.toString(j), tagss.getKey(), tagss.getValue() });
			// t.close();
			j++;
		}


		 Cursor t = db.rawQuery("SELECT * from tags", null);
		 System.out.println("Database tags size: " + t.getCount());
		 t.close();
		 Cursor tt = db.rawQuery("SELECT * from imp", null);
		 System.out.println("Database imp size: " + tt.getCount());
		 tt.close();

		System.out.println("Created tags DB!!");

		// DatabaseLoaderTagDB.copyDBToSDCard();

	//	db.close();
	}
	
	private static int getWeight(String currentTag, String relatedTag, ArrayList<String> tags){
		int weight = 0;
		for (int j = 0; j < tags.size(); j++) {
				//Cursor weights = db.rawQuery("SELECT * FROM posts WHERE CHARINDEX(?,tags)", new String[]{tagss.getKey()});
				
				if (tags.get(j).contains(currentTag) && tags.get(j).contains(relatedTag))
					weight++;
				
				
				}
		return weight;
	}

	private static String buildRelatedTags(String[] array, String currentTag) {
		String relatedTags = "";
		int total = 0;
//		if (array.length > 10)
//			total = 10;
//		else
			total = array.length;
	
		
		for (int i = 0; i < total; i++) {
			//array[i] = array[i].substring(1);
		if (!array[i].equals(currentTag)) {
			//array[i] = array[i].substring(1);
			if (i != 0 ) {
				
				relatedTags = relatedTags.concat(",");
				relatedTags = relatedTags.concat(array[i]);
			} else
				relatedTags = relatedTags.concat(array[i]);
		}
		}
		return relatedTags;
	}
}
