package com.agilegroup4.model;

import java.util.ArrayList;

public class Question{

	private int id;
	private String title;
	private String body;
	private int comment_count;
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	private String creationDate;
	
	public Question(int id, String title, String body, int comment_count){
		this.title = title;
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
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}	
}
