package com.agilegroup4.model;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/*
 * An ArrayList extension for ArrayLists of answer.
 */
public class AnswerList extends ArrayList<Answer> implements Parcelable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Creates a new question list.
	 */
	public AnswerList(){

	}

	/*
	 * Creates a new answer list based on a Parcel.
	 */
	public AnswerList(Parcel in){
		readFromParcel(in);
	}

	/*
	 * Creates a new answer list based on a Parcel.
	 */
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public AnswerList createFromParcel(Parcel in) {
			return new AnswerList(in);
		}

		public Object[] newArray(int arg0) {
			return null;
		}
	};

	/*
	 * Reads a parce and creates the list of answers.
	 */
	private void readFromParcel(Parcel in) {
		this.clear();

		//We read the first int that contains the list size.
		int size = in.readInt();

		//We read each question, Answer is parceable and is handled in Answer class.
		for (int i = 0; i < size; i++) {
			this.add((Answer)in.readParcelable(Answer.class.getClassLoader()));
		}
	}

	/*
	 * Describes the contents of this list, returning its hash code. 
	 */
	public int describeContents() {
		return hashCode();
	}

	/*
	 * Reads this answer list and parce it into a Parcel..
	 */
	public void writeToParcel(Parcel dest, int flags) {
		int size = this.size();

		//Write the list size, this is used to create a new list later.
		dest.writeInt(size);

		//We parse each question in the list. Answer in also parceable.
		for (int i = 0; i < size; i++) {
			dest.writeParcelable(this.get(i), flags);
		}
	}

}