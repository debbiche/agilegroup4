package com.agilegroup4.helpers;

import java.util.Comparator;
import com.agilegroup4.model.Question;

/* 
 * Compares two Question objects according to their creation date
 * 
 */
public class LatestComparator implements Comparator<Question> {

	/* 
	 * Compares two Question objects according to their creation date
	 * @param lhs The first question you want to compare
	 * @param rhs The second question you want to compare
	 * @returns 0 if lhs and rhs is equal, <1 if lhs is greater than rhs nad >1 vice versa.
	 */
	@Override
	public int compare(Question lhs, Question rhs) {
		int result = 0;
		try{
			result = rhs.getCreationDate().compareTo(lhs.getCreationDate());
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		return result;
	}

}
