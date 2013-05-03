package com.agilegroup4.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.agilegroup4.model.Tag;

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
	
	/*
	 * 
	 * */
	private static ArrayList<Tag> parseTags(Cursor cursor){
		ArrayList<Tag> tags = new ArrayList<Tag>();
		Tag tag;
		String relatedTags;
		
		cursor.moveToFirst();
		
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
	/*
	 * 
	 * */
	private static void addRelatedTags(Tag tag, String relatedTags){
		if (relatedTags == null){
			return;
		}
		
		String [] relatedTagsArray = relatedTags.split(",");
		
		for(int i = 0; i < relatedTagsArray.length; i++){
			tag.addRelatedTag(relatedTagsArray[i]);
		}
		
		return;
	}
	

	public static void createTagsDB(){
		// to be deleted after tags are extracted from DB
				HashMap<String, String> tagsHash = new HashMap<String, String>();
				
				ArrayList<String> listOfTags = new ArrayList<String>();
				String tags, tag;
				Cursor curUserID;
				Scanner scanner;

				curUserID = DatabaseHandler.db.rawQuery("SELECT tags from POSTS", null);
				
				curUserID.moveToFirst();
				while(!curUserID.isAfterLast()){
					//tags = curUserID.getString(0);
					//scanner = new Scanner(tags);
					listOfTags.add(curUserID.getString(0));
					
//					while(scanner.hasNext()){
//						tag = scanner.next();
//						System.out.println(tag);
//					}
					//hashi.put(tag, related);
					curUserID.moveToNext();
				}
				System.out.println("Size of tags array list: " + listOfTags.size());
				curUserID.close();
				// delete above until comment
				
				for (int i = 0; i < listOfTags.size(); i++){
					String[] currentTags = listOfTags.get(i).split(">");
					currentTags[0] = currentTags[0].substring(1);
					if (tagsHash.get(currentTags[0]) == null) {
						tagsHash.put(currentTags[0], buildRelatedTags(currentTags));
					} else {
						String relatedAndroid =  tagsHash.get(currentTags[0]).
									concat(buildRelatedTags(currentTags));
						tagsHash.remove(tagsHash.get(currentTags[0]));
						tagsHash.put(currentTags[0], relatedAndroid);
						
					}
				}
				
//				for (Entry<String, String> tagss : tagsHash.entrySet()){
//					System.out.println("Tag: " + tagss.getKey() + " with related tags: " +  tagss.getValue());
//
//				}
//				
			
				HashMap<String, String> tagsHash2 = new HashMap<String, String>();
				
				for (Entry<String, String> tagss : tagsHash.entrySet()){
					String[] currentTags = tagss.getValue().split(",");
					//System.err.println("tags size: " + currentTags.length);
					int total =0;
					
					if (currentTags.length > 10) total = 10 ; 
					else total = currentTags.length;
					
					for (int i =1; i < total; i++){
						if (!tagsHash.containsKey(currentTags[i])) {
							//currentTags[i] = currentTags[i].substring(1);
							if (!(i == currentTags.length-1))
							tagsHash2.put(currentTags[i], buildRelatedTags2(currentTags));
							//System.out.println("Added hash object in new hash!");
						}
						
						
					}
					//System.out.println("Looped!");
				}
					System.out.println("Size of tags hash2:" + tagsHash2.size());
				
				System.out.println("Hash tags before merging: " + tagsHash.size());
				tagsHash.putAll(tagsHash2);
				System.out.println("Hash tags after merging: " + tagsHash.size());

//				
						
				int j = 1;
				for (Entry<String, String> tagss : tagsHash.entrySet()){
//					System.out.println("Tag: " + tagss.getKey() + " with related tags: " +  tagss.getValue());
					db.execSQL("INSERT INTO tags VALUES (?,?,?)", new String[] { Integer.toString(j), tagss.getKey(), tagss.getValue() } );
				//	t.close();
					j++;
				}
				
				for (Entry<String, String> tagss : tagsHash.entrySet()){
					System.out.println("Tag: " + tagss.getKey() + " with related tags: " +  tagss.getValue());
				}
				
				
			//	Cursor t = db.rawQuery("SELECT * from tags", null);
				//System.out.println("Database tags size: " + t.getCount());
				
				System.out.println("Created tags DB!!");
				
			//	DatabaseLoaderTagDB.copyDBToSDCard();
				
//				db.close();
	}

	
	private static String buildRelatedTags2(String[] array){
		String relatedTags = "";
		int total = 0;
		if (array.length > 10 ) total = 10; 
		else total = array.length;
		for (int i = 1; i < total; i++){
			//array[i] = array[i].substring(1);
			if (i!=1){
			relatedTags = relatedTags.concat(",");
			relatedTags = relatedTags.concat(array[i]);
			} else relatedTags = relatedTags.concat(array[i]);

			
		}
		return relatedTags;
	}
	
	private static String buildRelatedTags(String[] array){
		String relatedTags = "";
		int total = 0;
		if (array.length > 10 ) total = 10; 
		else total = array.length;
		for (int i = 1; i < total; i++){
			array[i] = array[i].substring(1);
			if (i!=1){
			relatedTags = relatedTags.concat(",");
			relatedTags = relatedTags.concat(array[i]);
			} else relatedTags = relatedTags.concat(array[i]);

			
		}
		return relatedTags;
	}
}
