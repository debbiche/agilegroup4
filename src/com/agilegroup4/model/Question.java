package com.agilegroup4.model;

import java.util.ArrayList;
import java.util.Date;

public class Question{

	private int id;
	//private int postTypeId;
	//private int parentId;
	//private int acceptedAnswerId;
	private Date creationDate;
	private int score;
	private int viewCount;
	private String body;
	//private int ownerUserId;
	//private int lastEditorUserId;
	//private String lastEditorDisplayName;
	//private Date lastEditDate;
	//private Date lastActivityDate;
	//private Date communityOwnedDate;
	//private Date closedDate;
	private String title;
	//private String tags;
	private int answerCount;
	private int commentCount;
	private int favoriteCount;
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	
	public Question(int id, String title, String body, int comment_count){
		this.title = title;
		this.body = body;
		this.commentCount = comment_count;
		this.setId(id);
	}
	
	public int getCommentCount(){
		return commentCount;
	}
	
	public void setCommentCount(int comment_count){
		this.commentCount = comment_count;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}


}
