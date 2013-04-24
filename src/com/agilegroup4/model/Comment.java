package com.agilegroup4.model;

import java.util.ArrayList;

public class Comment {

	private int id;
	private String text;
	private int parent_id;
	
	public Comment(int id, String text){
		this.id = id;
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
