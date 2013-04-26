package com.agilegroup4.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {

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
	
	/*
	 * Creates a new answer based on a Parcel.
	 */
	private Answer(Parcel in) {
		this.parent_id = in.readInt();
		this.body = in.readString();
		this.comment_count = in.readInt();
		this.id = in.readInt();
	}
	
	/*
	 * Reads this answer and parce it into a Parcel.
	 */
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(parent_id);
		out.writeString(body);
		out.writeInt(comment_count);
		out.writeInt(id);
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

	/*
	 * Describes the contents of this answer, returning its hash code. 
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
