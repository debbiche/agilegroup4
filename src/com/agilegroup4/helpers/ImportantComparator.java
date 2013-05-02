package com.agilegroup4.helpers;

import java.util.Comparator;

import com.agilegroup4.model.Question;

/* 
 * Compares two Question objects according to their:
 * - view count			1 = 1 weight point
 * - score				1 = 50 weight points
 * - favorite count		1 = 100 weight points
 * 
 * TODO: fails for large values (int times 100...)
 * */
public class ImportantComparator implements Comparator<Question> {

	/* 
	 * Compares two Question objects according to their:
	 * - view count			1 = 1 weight point
	 * - score				1 = 50 weight points
	 * - favorite count		1 = 100 weight points
	 * @param lhs The first question you want to compare
	 * @param rhs The second question you want to compare
	 * @returns 0 if lhs and rhs is equal, <1 if lhs is greater than rhs and >1 vice versa.
	 */
	@Override
	public int compare(Question lhs, Question rhs) {
		int scoreLhs, scoreRhs;
		
		// add view count to score
		scoreLhs = lhs.getViewCount();
		scoreRhs = rhs.getViewCount();
		
		// add score to score (one score point equals 50 views)
		scoreLhs += (lhs.getScore() * 50);
		scoreRhs += (rhs.getScore() * 50);
		
		// add favorite count to score (one favorite point equals 100 views)
		scoreLhs += (lhs.getFavoriteCount() * 100);
		scoreRhs += (rhs.getFavoriteCount() * 100);
		
		return scoreRhs - scoreLhs;
	}
}
