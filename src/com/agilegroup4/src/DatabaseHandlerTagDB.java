package com.agilegroup4.src;

import java.util.ArrayList;
import com.agilegroup4.model.Tag;
import java.util.HashMap;
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

		String[] relatedTagsArray = relatedTags.split(",");

		for (int i = 0; i < relatedTagsArray.length; i++) {
			if (!relatedTagsArray[i].equals(""))
				tag.addRelatedTag(relatedTagsArray[i]);
		}

		return;
	}

	/*
	 * Only used when we need to recreate the tags db
	 */
	public static void createTagsDB() {
		// to be deleted after tags are extracted from DB
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
		for (int i = 0; i < listOfTags.size(); i++) { // Main loop
			String[] currentTags = listOfTags.get(i).split(">"); // split tags

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
