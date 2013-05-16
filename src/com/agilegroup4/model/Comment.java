package com.agilegroup4.model;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * Represents a comment to an answer in the database.
 */
public class Comment {

	private int id;
	private String text;
	private int parent_id;
	private int ownerId;
	
	/*
	 * Creates a new comment
	 * @param id The comment id
	 * @param text The comment
	 */
	public Comment(int id, String text, int user_id){
		this.id = id;
		this.text = text;
		this.ownerId = user_id;
	}
	
	/*
	 * Creates a new comment based on a Parcel.
	 * @param in A parcel to base the new comment on
	 */
	private Comment(Parcel in) {
		this.id = in.readInt();
		this.parent_id = in.readInt();
		this.text = in.readString();
		this.ownerId = in.readInt();
	}
	
	/*
	 * Reads this comment and parce it into a Parcel.
	 * @param out The parcel to create. Based on the current comment.
	 * @param flags Flags for writing parcel.
	 */
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeInt(parent_id);
		out.writeString(text);
		out.writeInt(ownerId);
		}
	
	/*
	 * Gets the comment text.
	 * @returns comment text.
	 */
	public String getText() {
		return text;
	}
	
	/*
	 * Sets the comment text.
	 * @param text The comment text.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * Gets the comment id.
	 * @returns The comment id.
	 */
	public int getId() {
		return id;
	}

	/*
	 * Sets the comment id.
	 * @param text The comment id.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	/*
	 * Describes the contents of this comment, returning its hash code. 
	 * @returns The comment hash code.
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
