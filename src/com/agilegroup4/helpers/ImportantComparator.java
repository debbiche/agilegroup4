package com.agilegroup4.helpers;

import java.util.Comparator;

import com.agilegroup4.model.Question;

public class ImportantComparator implements Comparator<Question> {

	@Override
	public int compare(Question lhs, Question rhs) {
		return rhs.getId() - lhs.getId();
	}
}
