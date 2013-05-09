package com.agilegroup4.model;

import java.util.ArrayList;

/*
 * Represents a tag in the database.
 */
public class Tag {

	private int tagId;
	private String tagName;
	private ArrayList<String> relatedTags;

	/*
	 * Creates a new Tag
	 * @param tagId The tag id
	 * @param tagName The name for the tag.
	 */
	public Tag(int tagId, String tagName) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
		relatedTags = new ArrayList<String>();
	}

	/*
	 * Gets the tag id.
	 * @returns Tag id.
	 */
	public int getTagId() {
		return tagId;
	}
	/*
	 * Sets the tag id.
	 * @param id Tag id.
	 */
	public void setTagId(int id) {
		this.tagId = id;
	}
	/*
	 * Gets the tag name.
	 * @returns Tag name.
	 */
	public String getTagName() {
		return tagName;
	}
	/*
	 * Sets the tag name.
	 * @param Tag name.
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	/*
	 * Gets the related tags.
	 * @returns Related tags.
	 */
	public ArrayList<String> getRelatedTags() {
		return relatedTags;
	}
	
	/*
	 * Gets the related tags.
	 * @param n the max number of tags returned
	 * @returns Related tags.
	 */
	public ArrayList<String> getRelatedTags(int n) {
		int nrToFetch = n < relatedTags.size() ? n : relatedTags.size();
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < nrToFetch; i++)
			ret.add(relatedTags.get(i));
		return ret;
	}
	
	/*
	 * Adds related tags.
	 * @param tag Related tag.
	 */
	public void addRelatedTag(String tag) {
		this.relatedTags.add(tag);
	}
	/*
	 * Removed a related tag.
	 * @param tag Related tag to remove.
	 */
	public void removeRelatedTag(String tag) {
		for(int i = 0; i < this.relatedTags.size(); i++){
			if(tag.equals(this.relatedTags.get(i)))
				this.relatedTags.remove(i);
		}
	}

}

