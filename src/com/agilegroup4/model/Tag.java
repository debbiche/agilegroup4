package com.agilegroup4.model;

import java.util.ArrayList;

public class Tag {
	
	private int id;
	private String tag;
	private ArrayList<Tag> relatedTags;
	
	public Tag(int id, String tag, ArrayList<Tag> relatedTags) {
		super();
		this.id = id;
		this.tag = tag;
		this.relatedTags = relatedTags;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public ArrayList<Tag> getRelatedTags() {
		return relatedTags;
	}
	public void setRelatedTags(ArrayList<Tag> relatedTags) {
		this.relatedTags = relatedTags;
	}

}
