package com.agilegroup4.src;

import java.util.ArrayList;
import com.agilegroup4.model.Tag;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
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
	public static int createTagsDB = 0; // DO NOT CHANGE THIS OR A KITTY WILL DIE!!

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
	 * 
	 * @param numberOfQuestions
	 *            limits the resulting query
	 * @return ArrayList
	 */
	public static ArrayList<Tag> queryTags(int numberOfQuestions) {

		String rawQuery = "SELECT * FROM tags " + "ORDER BY tag";
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
			Cursor tagsImportance = db.rawQuery("SELECT * FROM imp WHERE tag = ? ORDER BY weight DESC",
						new String[]{tag.getTagName()});
			
			tagsImportance.moveToFirst();
			while (!tagsImportance.isAfterLast()) {
				tag.addRelatedTag(tagsImportance.getString(1));
				tagsImportance.moveToNext();
			}
			tagsImportance.close();
	}

	/*
	 * Only used when we need to recreate the tags db
	 */
	public static void createTagsDB() {
		HashMap<String, String> tagsHash = new HashMap<String, String>();

		ArrayList<String> listOfTags = new ArrayList<String>();
		Cursor curUserID;
		curUserID = DatabaseHandler.db.rawQuery("SELECT tags from POSTS", null);

		curUserID.moveToFirst();
		while (!curUserID.isAfterLast()) {
			listOfTags.add(curUserID.getString(0));
			curUserID.moveToNext();
		}
		curUserID.close();

		HashMap<String, String> dupTagsHash = new HashMap<String, String>();

		// Loop through tags and populate the hashmap for the tags
		int l = 0;
		for (int i = 0; i < listOfTags.size(); i++) { // Main loop
			String[] currentTags = listOfTags.get(i).split(">"); // split tags

			
			//String[] extraOnes = getOthers(currentTags, listOfTags);
			//System.out.println("Did an extra one number: " + l);
			//l++;
			
			for (int k = 0; k < currentTags.length; k++) { // remove the "<"
				
				currentTags[k] = currentTags[k].substring(1);
			}
			
			for (int j = 0; j < currentTags.length; j++) {
				String relatedTags = buildRelatedTags(currentTags,
						currentTags[j]);
				if (tagsHash.get(currentTags[j]) == null
						&& !(relatedTags.equals(""))) { // tag hasn't been
														// inserted before
					tagsHash.put(currentTags[j], relatedTags);

				} else
					dupTagsHash.put(currentTags[j],
							buildRelatedTags(currentTags, currentTags[j]));
			}
		} // end main loop
		
		
		
		// Fix the duplicate tags
//		for (Entry<String, String> dup : dupTagsHash.entrySet()) {
//			if (tagsHash.get(dup.getKey()) != null) { // tag already exists in tag hash (should always be the case)
//				String[] tags = dup.getValue().split(","); // dups to insert
//				
//				for (int j = 0; j < tags.length; j++) {
//					String concernedTag = tagsHash.get(dup.getKey());
//					String newRelatedTags = concernedTag.concat(",");
//					newRelatedTags = newRelatedTags.concat(dup.getValue());
//
//					String[] newRelated = newRelatedTags.split(",");
//					
//					for (int k = 0; k < newRelated.length; k++) {
//						String relatedTags = buildRelatedTags(newRelated,
//								newRelated[j]);
//						tagsHash.remove(newRelated[j]);
//						if (tagsHash.get(newRelated[j]) == null
//								&& !(relatedTags.equals(""))) { // tag hasn't been
//																// inserted before
//							tagsHash.put(newRelated[j], relatedTags);
//
//						}
//					
//					
//					
//					//String currentRelatedTags = tagsHash.get(dup.)
//				}
//				
//				
//			}
//			}
		
		

		// Set the weights for each tag pair
		for (Entry<String, String> tagss : tagsHash.entrySet()) {
			String[] currentTags = tagss.getValue().split(","); // split tags

			// add the weights to the tags db
			for (int j = 1; j < currentTags.length; j++) {
				int weight = getWeight(tagss.getKey(), currentTags[j],
						listOfTags);
				db.execSQL(
						"INSERT INTO imp VALUES (?,?,?)",
						new String[] { tagss.getKey(), currentTags[j],
								Integer.toString(weight) });
			}
		}

		int j = 1;
		for (Entry<String, String> tagss : tagsHash.entrySet()) {
			db.execSQL("INSERT INTO tags VALUES (?,?,?)", new String[] {
					Integer.toString(j), tagss.getKey(), tagss.getValue() });
			j++;
		}
		// db.close();
	}
	

	private static String[] getOthers(String[] currentTags, ArrayList<String> tagsList) {
		
		String  finalArray[] = {""};
		for (int i = 0; i < tagsList.size(); i++) {
			String temp[] = tagsList.get(i).split(">");
			
			if (temp[0].equals(currentTags[0])) 
				finalArray = f(finalArray, temp);
		}
		
		
		return finalArray;
		
	}
	
	
	private static	String[] f(String[] first, String[] second) {
	    List<String> both = new ArrayList<String>(first.length + second.length);
	    Collections.addAll(both, first);
	    Collections.addAll(both, second);
	    return both.toArray(new String[both.size()]);
	}

	private static int getWeight(String currentTag, String relatedTag,
			ArrayList<String> tags) {
		int weight = 0;
		for (int j = 0; j < tags.size(); j++) {
			if (tags.get(j).contains(currentTag)
					&& tags.get(j).contains(relatedTag))
				weight++;
		}
		return weight;
	}

	private static String buildRelatedTags(String[] array, String currentTag) {
		String relatedTags = "";
		int total = 0;
		// if (array.length > 10)
		// total = 10;
		// else
		total = array.length;

		for (int i = 1; i < total; i++) {
			if (!array[i].equals(currentTag)) {
				if (i != 1) {
					relatedTags = relatedTags.concat(",");
					relatedTags = relatedTags.concat(array[i]);
				} else
					relatedTags = relatedTags.concat(array[i]);
			}
		}

		if (relatedTags.startsWith(",")) {
			relatedTags = relatedTags.substring(1);
		}
		return relatedTags;
	}
}
