package com.agilegroup4.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment {

	private int id;
	private String text;
	private int parent_id;
	
	public Comment(int id, String text){
		this.id = id;
		this.text = text;
	}
	
	/*
	 * Creates a new comment based on a Parcel.
	 */
	private Comment(Parcel in) {
		this.id = in.readInt();
		this.parent_id = in.readInt();
		this.text = in.readString();
	}
	
	/*
	 * Reads this comment and parce it into a Parcel.
	 */
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeInt(parent_id);
		out.writeString(text);
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/*
	 * Describes the contents of this comment, returning its hash code. 
	 */
	public int describeContents() {
		return this.hashCode();
	}

	/*
	 * Creates a new comment based on a Parcel.
	 */
	public static final Parcelable.Creator<Comment> CREATOR = 
		new Parcelable.Creator<Comment>() { 
		public Comment createFromParcel(Parcel in) { 
			return new Comment(in);        
		}

		@Override
		public Comment[] newArray(int size) {
			return new Comment[size];
		}
	};

	
}
