package com.agilegroup4.src;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TagsDBLoader extends SQLiteOpenHelper{

	private String dbPath = "data/data/com.agilegroup4.src/databases";
	private String dbName = "TagsDB";
	protected static SQLiteDatabase TagsDB;

	
	
	public TagsDBLoader(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		loadDB();
	}
	
	private void loadDB(){
			
		
			System.out.println("Database not loaded, loading it now...");
			this.getWritableDatabase(); // open empty DB
			
			TagsDB = SQLiteDatabase.openOrCreateDatabase(dbName, null);

//			TagsDB = SQLiteDatabase.openDatabase(dbPath + dbName, null,
//					SQLiteDatabase.OPEN_READWRITE
//							| SQLiteDatabase.NO_LOCALIZED_COLLATORS);
//			// db.close();
		
	}
	
	
	public void createTagsDB(){
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
				
				System.out.println("Size of tags hash:" + tagsHash.size());
				
				

				
				for (Entry<String, String> tagss : tagsHash.entrySet()){
					System.out.println("Tag: " + tagss.getKey() + " with related tags: " +  tagss.getValue());

				}		
				
				TagsDB.rawQuery("CREATE TABLE tags(id int,tag text,related text)", null);
				
				System.out.println("Created tags DB!!");
				
	}

	private static String buildRelatedTags(String[] array){
		String relatedTags = "";
		
		for (int i = 1; i < array.length; i++){
			array[i] = array[i].substring(1);
			if (i!=1){
			relatedTags = relatedTags.concat(",");
			relatedTags = relatedTags.concat(array[i]);
			} else relatedTags = relatedTags.concat(array[i]);

			
		}
		return relatedTags;
	}
	
	
	
	
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
