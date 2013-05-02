package com.agilegroup4.model;

import java.util.ArrayList;

public class Tag {

	private int tagId;
	private String tagName;
	private ArrayList<String> relatedTags;

	public Tag(int tagId, String tagName) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
		relatedTags = new ArrayList<String>();
	}

	public int getTagId() {
		return tagId;
	}
	public void setTagId(int id) {
		this.tagId = id;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public ArrayList<String> getRelatedTags() {
		return relatedTags;
	}
	public void addRelatedTag(String tag) {
		this.relatedTags.add(tag);
	}
	public void removeRelatedTag(String tag) {
		for(int i = 0; i < this.relatedTags.size(); i++){
			if(tag.equals(this.relatedTags.get(i)))
				this.relatedTags.remove(i);
		}
	}

}

