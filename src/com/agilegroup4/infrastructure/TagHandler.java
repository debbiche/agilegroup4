package com.agilegroup4.infrastructure;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.agilegroup4.model.Tag;
import com.agilegroup4.src.DatabaseHandlerTagDB;

public class TagHandler extends DatabaseHandlerTagDB {

	public TagHandler(Context context) {
		super(context);
	}
	
	/**
	 * TODO: error handling
	 * 
	 */
	public static ArrayList<Tag> getRelatedTags(String tagName) {
		ArrayList<Tag> relatedTags = new ArrayList<Tag>();
		Cursor tagsImportance = db.rawQuery("SELECT * FROM imp INNER JOIN tags ON imp.tag = tags.tag WHERE imp.tag = ? ORDER BY weight DESC",
				new String[]{tagName});
				
		tagsImportance.moveToFirst();
		while (!tagsImportance.isAfterLast()) {
			System.out.println(tagsImportance.getString(0) + " " + tagsImportance.getString(1) + " " + tagsImportance.getInt(2) + " " + tagsImportance.getInt(3) + " " + tagsImportance.getString(4) + " " + tagsImportance.getString(5));
			relatedTags.add(new Tag(tagsImportance.getInt(3), tagsImportance.getString(1)));
			tagsImportance.moveToNext();
		}
		tagsImportance.close();
		return relatedTags;
	} 
	
	/**
	 * queries for the weight of a tags related tag
	 * 
	 * @author Eumelbert
	 */
	public static int queryWeight(String tagName, String relatedTag) {
		int weight = -1;
		Cursor weightQuery;
		
		weightQuery = db.rawQuery("SELECT weight FROM imp WHERE tag = ? AND related = ?", new String[]{tagName, relatedTag});
		
		// TODO: if query equals empty --> return 0
		
		weightQuery.moveToFirst();
		weight = weightQuery.getInt(0);
		weightQuery.close();
		
		return weight;
	}

}
