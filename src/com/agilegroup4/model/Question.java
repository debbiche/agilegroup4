package com.agilegroup4.model;

import java.util.ArrayList;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Represents a question in the database.
 */
public class Question implements Parcelable {

	private int id;
	private Date creationDate;
	private int score;
	private int viewCount;
	private String body;
	private String title;
	private int answerCount;
	private int commentCount;
	private int favoriteCount;
	private String tags;
	private AnswerList answers = new AnswerList();
	private boolean queriedAnswers = false;
	private int ownerUserId;
	
	/*
	 * Creates a new question
	 * @param id The question id
	 * @param body The body
	 * @param comment_count The number of comments
	 * @param creation_date The creation date
	 * @param score The score
	 * @param viewCount The number of views for this question
	 * @param favoriteCount The number of favorites for this question
	 * @param tag The tag for this question
	 */
	public Question(int id, String title, String body, int comment_count,
					Date creation_date, int score, int viewCount, int favoriteCount, String tags, int ownerUserId){
		this.title = title;
		this.body = body;
		this.commentCount = comment_count;
		this.setId(id);
		this.creationDate = creation_date;
		this.score = score;
		this.favoriteCount = favoriteCount;
		this.tags = tags;
		this.ownerUserId = ownerUserId;
	}
	
	/*
	 * Creates a new question based on a Parcel.
	 *  @param in The parcel.
	 */
	private Question(Parcel in) {
		this.title = in.readString();
		this.body = in.readString();
		this.commentCount = in.readInt();
		this.id = in.readInt();
		this.score = in.readInt();
		this.viewCount = in.readInt();
		this.favoriteCount = in.readInt();
		this.answers = in.readParcelable(Answer.class.getClassLoader());
		this.tags = in.readString();
		this.ownerUserId = in.readInt();
	}
	
	/*
	 * Reads this question and parce it into a Parcel.
	 * @param out The parcel
	 * @flags Eventual flags for the writing of the parcel.
	 */
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(title);
		out.writeString(body);
		out.writeInt(commentCount);
		out.writeInt(id);
		out.writeInt(score);
		out.writeInt(viewCount);
		out.writeInt(favoriteCount);
		out.writeParcelable(answers, flags);
		out.writeString(tags);
		out.writeInt(ownerUserId);
	}
	

	/*
	 * Gets the number of comments for this question.
	 * @returns number of comments.
	 */
	public int getCommentCount(){
		return commentCount;
	}
	
	/*
	 * Sets the number of comments for this question.
	 * @param comment_count number of comments.
	 */
	public void setCommentCount(int comment_count){
		this.commentCount = comment_count;
	}
	
	/*
	 * Gets the title for this question.
	 * @returns question title.
	 */
	public String getTitle() {
		return title;
	}
	
	/*
	 * Sets the title for this question.
	 * @param title question title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/*
	 * Gets the body for this question.
	 * @returns question body.
	 */
	public String getBody() {
		return body;
	}
	
	/*
	 * Sets the body for this question.
	 * @param body The question body.
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/*
	 * Gets the answers for this question.
	 * @returns question answers.
	 */
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	
	/*
	 * Sets the answers for this question.
	 * @param answers The question answers.
	 */
	public void setAnswers(AnswerList answers) {
		this.answers = answers;
	}

	/*
	 * Gets the id for this question.
	 * @returns question id.
	 */
	public int getId() {
		return id;
	}

	/*
	 * Sets the id for this question.
	 * @param id The question id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * Gets the creation date for this question.
	 * @returns question creation date.
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/*
	 * Sets the creation date for this question.
	 * @param creationDate question creation date.
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/*
	 * Gets the score for this question.
	 * @returns question score.
	 */
	public int getScore() {
		return score;
	}

	/*
	 * Sets the score for this question.
	 * @param score The question scores.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/*
	 * Gets the view count for this question.
	 * @returns question view count.
	 */
	public int getViewCount() {
		return viewCount;
	}

	/*
	 * Sets the view count for this question.
	 * @param viewCount question view count.
	 */
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	/*
	 * Gets the answer count for this question.
	 * @returns question answer count.
	 */
	public int getAnswerCount() {
		return answerCount;
	}

	/*
	 * Gets the answer count for this question.
	 * @param answerCount question answers count.
	 */
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	/*
	 * Gets the favorite count for this question.
	 * @returns question favorite count.
	 */
	public int getFavoriteCount() {
		return favoriteCount;
	}

	/*
	 * Sets the favorite count for this question.
	 * @param favoriteCount question favorite count.
	 */
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	
	/*
	 * Gets the tags for this question.
	 * @returns question tags.
	 */
	public String getTags() {
		return tags;
	}
	
	/*
	 * Sets the tags for this question.
	 * @param tags The question tags.
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/*
	 * Describes the contents of this question, returning its hash code. 
	 * @returns Hashtag for this question.
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
	
	public int getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(int ownerUserId) {
		this.ownerUserId = ownerUserId;
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
