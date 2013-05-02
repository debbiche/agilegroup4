package com.agilegroup4.model;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Represents an answer to a question in the database.
 */
public class Answer implements Parcelable {

	private int id;
	private String body;
	private int parent_id;
	private int comment_count;
	
	/*
	 * Creates a new answer
	 * @param id The answer id
	 * @param body The answer body
	 * @param comment_count The number of comments.
	 */
	public Answer(int id, String body, int comment_count){
		this.body = body;
		this.comment_count = comment_count;
		this.setId(id);
	}
	
	/*
	 * Creates a new answer based on a Parcel.
	 * @param in The parcel to create a new answer from.
	 */
	private Answer(Parcel in) {
		this.parent_id = in.readInt();
		this.body = in.readString();
		this.comment_count = in.readInt();
		this.id = in.readInt();
	}
	
	/*
	 * Reads this answer and parce it into a Parcel.
	 * @param out The parcel to create. Based on the current answer
	 * @param flags Flags for writing parcel.
	 */
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(parent_id);
		out.writeString(body);
		out.writeInt(comment_count);
		out.writeInt(id);
	}
	
	/*
	 * Gets the number of comments for this answer.
	 * @returns number of comments.
	 */
	public int getCommentCount(){
		return comment_count;
	}
	
	/*
	 * Sets the number of comments for this answer.
	 * @param comment_count number of comments.
	 */
	public void setCommentCount(int comment_count){
		this.comment_count = comment_count;
	}
	
	/*
	 * Gets the body for this answer.
	 * @returns the answer body.
	 */
	public String getBody() {
		return body;
	}
	
	/*
	 * Sets the body for this answer.
	 * @param body the answer body.
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/*
	 * Gets the id for this answer.
	 * @returns the answer id.
	 */
	public int getId() {
		return id;
	}

	/*
	 * Sets the id for this answer.
	 * @param id the answer id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * Describes the contents of this answer, returning its hash code. 
	 * @returns hashcode for this answer.
	 */
	public int describeContents() {
		return this.hashCode();
	}

	/*
	 * Creates a new answer based on a Parcel.
	 */
	public static final Parcelable.Creator<Answer> CREATOR = 
		new Parcelable.Creator<Answer>() { 
		public Answer createFromParcel(Parcel in) { 
			return new Answer(in);        
		}

		@Override
		public Answer[] newArray(int size) {
			return new Answer[size];
		}
	};
	
}
