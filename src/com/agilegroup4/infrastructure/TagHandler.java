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
			relatedTags.add(new Tag(tagsImportance.getInt(3), tagsImportance.getString(1)));
			tagsImportance.moveToNext();
		}
		tagsImportance.close();
		return relatedTags;
	} 
	
	/**
	 * queries for the weight of a tags related tag
	 * 
	 * @returns -1 on query exception
	 * @returns -2 if <b>tagName</b> is not related to <b>relatedTag</b>
	 * 
	 * @author Eumelbert
	 */
	public static int queryWeight(String tagName, String relatedTag) {
		int weight = -1;
		Cursor weightQuery;
		
		weightQuery = db.rawQuery("SELECT weight FROM imp WHERE tag = ? AND related = ?", new String[]{tagName, relatedTag});

		if (weightQuery == null) {
			return -1;
		}
		
		weightQuery.moveToFirst();
		if(weightQuery.getCount() == 0){
			return -2;
		} else {
			weight = weightQuery.getInt(0);
			weightQuery.close();
		}
		return weight;
	}

}
