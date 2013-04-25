package com.agilegroup4.helpers;

import java.util.Comparator;

import com.agilegroup4.model.Question;

public class ImportantComparator implements Comparator<Question> {

	@Override
	public int compare(Question lhs, Question rhs) {
		int scoreLhs, scoreRhs;
		
		// add view count to score
		scoreLhs = lhs.getViewCount();
		scoreRhs = rhs.getViewCount();
		
		// add score to score (one score point equals 50 views)
		scoreLhs = lhs.getScore();
		scoreRhs = rhs.getScore();
		
		// add favorite count to score (one favorite point equals 100 views)
		scoreLhs = lhs.getViewCount();
		scoreRhs = rhs.getViewCount();
		
		return scoreRhs - scoreLhs;
	}
}
