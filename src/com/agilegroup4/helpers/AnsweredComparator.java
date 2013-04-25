package com.agilegroup4.helpers;

import java.util.Comparator;
import com.agilegroup4.model.Question;

public class AnsweredComparator implements Comparator<Question> {

	@Override
	public int compare(Question lhs, Question rhs) {
		int scoreLhs, scoreRhs;
		
		// add answer count to score
		scoreLhs = lhs.getAnswerCount();
		scoreRhs = rhs.getAnswerCount();
		
		// add answer count to score (one score point equals 50 views)
		scoreLhs += (lhs.getScore()/2);
		scoreRhs += (rhs.getScore()/2);
		
		// is there an accepted answer? (if yes add 100 score points)
		//scoreLhs += (lhs. * 100);
		//scoreRhs += (rhs. * 100);
		
		return scoreRhs - scoreLhs;
	}
}