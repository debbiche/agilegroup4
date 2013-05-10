package com.agilegroup4.model;

import java.util.ArrayList;

public class TagList extends ArrayList<Tag> {
	
	private int MAX_NR_OF_COMBINATIONS = 4;
	private ArrayList<Tag> _allTags;
	
	public TagList(ArrayList<Tag> allTags){
		_allTags = allTags;
	}
	
	/**
	 * @return next tag in order
	 */
	public Tag getNext(){
		int returnPos = _allTags.indexOf(this.get(0));
		if (returnPos == _allTags.size()-1)
			return null;
		return _allTags.get(returnPos + 1);
	}
	
	/**
	 * @return previous tag in order
	 */
	public Tag getPrevious(){
		int returnPos = _allTags.indexOf(this.get(0));
		if (returnPos == 0)
			return null;
		return _allTags.get(returnPos - 1);
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Tag tag : this)
			sb.append(tag.getTagName()).append("/");
		return sb.deleteCharAt(sb.length()-1).toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@Override
	public boolean add(Tag tag){
		if (this.size() >= MAX_NR_OF_COMBINATIONS || this.contains(tag))
			return false;
		return super.add(tag);
	}
	
	/**
	 * @return the related names of the tags.
	 */
	public ArrayList<String> getRelatedTags(){
		if(this.size() > MAX_NR_OF_COMBINATIONS || this.size() == 0)
			return new ArrayList<String>();
		ArrayList<String> tags = new ArrayList<String>();
		for (Tag tag : this)
			tags.addAll(tag.getRelatedTags(MAX_NR_OF_COMBINATIONS/this.size(), this));
		return tags;
	}
}
