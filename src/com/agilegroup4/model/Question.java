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
	private int answer_count;
	private int comment_count;
	private int favorite_count;
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	
	public Question(int id, String title, String body, int comment_count,
					Date creation_date, int score, int viewCount, int favoriteCount){
		this.title = title;
		this.body = body;
		this.comment_count = comment_count;
		this.setId(id);
		this.creationDate = creation_date;
		this.score = score;
		this.viewCount = viewCount;
		this.favorite_count = favoriteCount;
		
		
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

	public int getAnswer_count() {
		return answer_count;
	}

	public void setAnswer_count(int answer_count) {
		this.answer_count = answer_count;
	}

	public int getComment_count() {
		return comment_count;
	}

	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

	public int getFavorite_count() {
		return favorite_count;
	}

	public void setFavorite_count(int favorite_count) {
		this.favorite_count = favorite_count;
	}

}
