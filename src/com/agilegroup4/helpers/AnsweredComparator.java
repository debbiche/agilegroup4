package com.agilegroup4.helpers;

import java.util.Comparator;
import com.agilegroup4.model.Question;

/*
 * 
 */
public class AnsweredComparator implements Comparator<Question> {

	@Override
	public int compare(Question lhs, Question rhs) {
		int scoreLhs, scoreRhs;
		
		// add answer count to score
		scoreLhs = lhs.getAnswerCount();
		scoreRhs = rhs.getAnswerCount();
		
		// add comment count to score (one comment equals to 1/2 answer)
		scoreLhs += (lhs.getCommentCount()/2);
		scoreRhs += (rhs.getCommentCount()/2);
		
		// is there an accepted answer? (if yes add 100 score points)
		//scoreLhs += (lhs. * 100);
		//scoreRhs += (rhs. * 100);
		
		return scoreRhs - scoreLhs;
	}
}