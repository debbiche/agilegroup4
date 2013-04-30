package com.agilegroup4.model;

import java.util.ArrayList;

public class Tag {

	private int tagId;
	private String tagName;
	private ArrayList<Tag> relatedTags;

	public Tag(int tagId, String tagName) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
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
	public ArrayList<Tag> getRelatedTags() {
		return relatedTags;
	}
	public void addRelatedTag(Tag tag) {
		this.relatedTags.add(tag);
	}
	public void removeRelatedTag(Tag tag) {
		for(int i = 0; i < this.relatedTags.size(); i++){
			if(tag.getTagName().equals(this.relatedTags.get(i).getTagName()))
				this.relatedTags.remove(i);
		}
	}

}

