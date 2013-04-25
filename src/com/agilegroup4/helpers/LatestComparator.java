package com.agilegroup4.helpers;

import java.util.Comparator;
import com.agilegroup4.model.Question;

/* Compares two Question objects according to their creation date
 * */
public class LatestComparator implements Comparator<Question> {

	@Override
	public int compare(Question lhs, Question rhs) {
		return lhs.getCreationDate().compareTo(rhs.getCreationDate());
	}

}
