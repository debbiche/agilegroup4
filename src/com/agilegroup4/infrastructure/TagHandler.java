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
	public static ArrayList<Tag> getRelatedTags(Tag tag) {
		ArrayList<Tag> relatedTags = new ArrayList<Tag>();
		Cursor tagsImportance = db.rawQuery("SELECT * FROM imp INNER JOIN tags ON imp.tag = tags.tag WHERE imp.tag = ? ORDER BY weight DESC",
				new String[]{tag.getTagName()});
				
		tagsImportance.moveToFirst();
		while (!tagsImportance.isAfterLast()) {
			tag.addRelatedTag(new Tag(tagsImportance.getInt(3), tagsImportance.getString(1)));
			tagsImportance.moveToNext();
		}
		tagsImportance.close();
		return relatedTags;
	} 

}
