package com.agilegroup4.src;

import java.util.ArrayList;

public class Question {

	private int id;
	private String title;
	private String body;
	private ArrayList<String> answers = new ArrayList<String>();
	
	public Question(int id, String title, String body){
		this.title = title;
		this.body = body;
		this.setId(id);
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
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
