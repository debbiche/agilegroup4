package com.agilegroup4.model;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionList extends ArrayList<Question> implements Parcelable{

	public QuestionList(){

	}

	public QuestionList(Parcel in){
		readFromParcel(in);
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public QuestionList createFromParcel(Parcel in) {
			return new QuestionList(in);
		}

		public Object[] newArray(int arg0) {
			return null;
		}
	};

	private void readFromParcel(Parcel in) {
		this.clear();

		//First we have to read the list size
		int size = in.readInt();

		//Reading remember that we wrote first the Name and later the Phone Number.
		//Order is fundamental
		for (int i = 0; i < size; i++) {
			this.add((Question)in.readParcelable(Question.class.getClassLoader()));
		}
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		int size = this.size();

		//We have to write the list size, we need him recreating the list
		dest.writeInt(size);

		//We decided arbitrarily to write first the Name and later the Phone Number.
		for (int i = 0; i < size; i++) {
			dest.writeParcelable(this.get(i), flags);
		}
	}

}