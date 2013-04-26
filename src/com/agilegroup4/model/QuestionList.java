package com.agilegroup4.model;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * An ArrayList extensions for ArrayLists of questions.
 */
public class QuestionList extends ArrayList<Question> implements Parcelable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Creates a new question list.
	 */
	public QuestionList(){

	}

	/*
	 * Creates a new question list based on a Parcel.
	 */
	public QuestionList(Parcel in){
		readFromParcel(in);
	}

	/*
	 * Creates a new question list based on a Parcel.
	 */
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public QuestionList createFromParcel(Parcel in) {
			return new QuestionList(in);
		}

		public Object[] newArray(int arg0) {
			return null;
		}
	};

	/*
	 * Reads a parce and creates the list of questions.
	 */
	private void readFromParcel(Parcel in) {
		this.clear();

		//We read the first int that contains the list size.
		int size = in.readInt();

		//We read each question, Question is parceable and is handled in question class.
		for (int i = 0; i < size; i++) {
			this.add((Question)in.readParcelable(Question.class.getClassLoader()));
		}
	}

	/*
	 * Describes the contents of this list, returning its hash code. 
	 */
	public int describeContents() {
		return hashCode();
	}

	/*
	 * Reads this question list and parce it into a Parcel..
	 */
	public void writeToParcel(Parcel dest, int flags) {
		int size = this.size();

		//Write the list size, this is used to create a new list later.
		dest.writeInt(size);

		//We parse each question in the list. Question in also parceable.
		for (int i = 0; i < size; i++) {
			dest.writeParcelable(this.get(i), flags);
		}
	}

}