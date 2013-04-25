package com.agilegroup4.model;

import java.util.ArrayList;
import java.util.Comparator;

public class Question implements Comparator<Question>{

	private int id;
	private String title;
	private String body;
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	
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

	@Override
	public int compare(Question arg0, Question arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
