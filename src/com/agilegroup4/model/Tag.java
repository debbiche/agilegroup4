package com.agilegroup4.model;

import java.util.ArrayList;

import com.agilegroup4.infrastructure.TagHandler;

/*
 * Represents a tag in the database.
 */
public class Tag implements Comparable<Tag> {

	private int tagId;
	private String tagName;
	private ArrayList<Tag> relatedTags;

	/*
	 * Creates a new Tag
	 * @param tagId The tag id
	 * @param tagName The name for the tag.
	 */
	public Tag(int tagId, String tagName) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
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
	private ArrayList<Tag> getRelatedTags() {
		if(relatedTags == null)
			relatedTags = TagHandler.getRelatedTags(this);
		return relatedTags;
	} 
	
	/*
	 * Gets the related tags.
	 * @param n the max number of tags returned
	 * @returns Related tags.
	 */
	private ArrayList<Tag> getRelatedTags(int n) {
		int nrToFetch = n < getRelatedTags().size() ? n : getRelatedTags().size();
		ArrayList<Tag> ret = new ArrayList<Tag>();
		for (int i = 0; i < nrToFetch; i++)
			ret.add(getRelatedTags().get(i));
		return ret;
	}
	
	/*
	 * Gets the related tags.
	 * @param n the max number of tags returned
	 * @param exceptTags Return a list without provided tags.
	 * @returns Related tags.
	 */
	public ArrayList<Tag> getRelatedTags(int n, ArrayList<Tag> exceptTags) {
		int nrToFetch = n < getRelatedTags().size() ? n : getRelatedTags().size();
		ArrayList<Tag> ret = new ArrayList<Tag>();
		@SuppressWarnings("unchecked")
		ArrayList<Tag> relatedTagsWithoutExcept = new ArrayList<Tag>();
		relatedTagsWithoutExcept.addAll(getRelatedTags());
		relatedTagsWithoutExcept.removeAll(exceptTags);
		for (int i = 0; i < nrToFetch; i++)
			ret.add(relatedTagsWithoutExcept.get(i));
		return ret;
	}
	
	/*
	 * Adds related tags.
	 * @param tag Related tag.
	 */
	public void addRelatedTag(Tag tag) {
		this.relatedTags.add(tag);
	}
	
	/*
	 * Removed a related tag.
	 * @param tag Related tag to remove.
	 */
	public void removeRelatedTag(String tag) {
		for(Tag currentTag : relatedTags){
			if(tag.equals(currentTag.getTagName()))
				this.relatedTags.remove(currentTag);
		}
	}

	public int compareTo(Tag anotherTag) throws ClassCastException {
	    if (!(anotherTag instanceof Tag))
	      throw new ClassCastException("A Tag object expected.");
	    String anotherTagName = ((Tag) anotherTag).getTagName();  
	    return this.getTagName().compareTo(anotherTagName);    
	  }

}

