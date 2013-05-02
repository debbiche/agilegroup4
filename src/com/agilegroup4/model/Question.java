package com.agilegroup4.model;

import java.util.ArrayList;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

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
	private String tags;
	private AnswerList answers = new AnswerList();
	private boolean queriedAnswers = false;
	
	public Question(int id, String title, String body, int comment_count,
					Date creation_date, int score, int viewCount, int favoriteCount, String tags){
		this.title = title;
		this.body = body;
		this.commentCount = comment_count;
		this.setId(id);
		this.creationDate = creation_date;
		this.score = score;
		this.favoriteCount = favoriteCount;
		this.tags = tags;
	}
	
	/*
	 * Creates a new question based on a Parcel.
	 */
	private Question(Parcel in) {
		this.title = in.readString();
		this.body = in.readString();
		this.commentCount = in.readInt();
		this.id = in.readInt();
		//this.creationDate = in.readSerializable();
		this.score = in.readInt();
		this.viewCount = in.readInt();
		this.favoriteCount = in.readInt();
		this.answers = in.readParcelable(Answer.class.getClassLoader());
		this.tags = in.readString();
	}
	
	/*
	 * Reads this question and parce it into a Parcel.
	 */
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(title);
		out.writeString(body);
		out.writeInt(commentCount);
		out.writeInt(id);
		//out.writeInt(creationDate);
		out.writeInt(score);
		out.writeInt(viewCount);
		out.writeInt(favoriteCount);
		out.writeParcelable(answers, flags);
		out.writeString(tags);
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
	public void setAnswers(AnswerList answers) {
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
	
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}

	/*
	 * Describes the contents of this question, returning its hash code. 
	 */
	public int describeContents() {
		return this.hashCode();
	}

	public boolean getQueriedAnswers() {
		return queriedAnswers;
	}

	public void setQueriedAnswers(boolean queriedAnswers) {
		this.queriedAnswers = queriedAnswers;
	}

	/*
	 * Creates a new question based on a Parcel.
	 */
	public static final Parcelable.Creator<Question> CREATOR = 
		new Parcelable.Creator<Question>() { 
		public Question createFromParcel(Parcel in) { 
			return new Question(in);        
		}

		@Override
		public Question[] newArray(int size) {
			return new Question[size];
		}
	};
}
