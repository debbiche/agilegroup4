package com.agilegroup4.model;

import java.util.ArrayList;

public class Answer {

	private int id;
	//private String title;
	private String body;
	private int parent_id;
	private int comment_count;
	
	public Answer(int id, String body, int comment_count){
		//this.title = title;
		this.parent_id = parent_id;
		this.body = body;
		this.comment_count = comment_count;
		this.setId(id);
	}
	
	public int getCommentCount(){
		return comment_count;
	}
	
	public void setCommentCount(int comment_count){
		this.comment_count = comment_count;
	}
	
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
