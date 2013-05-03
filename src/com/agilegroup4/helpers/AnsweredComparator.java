package com.agilegroup4.helpers;

import java.util.Comparator;
import com.agilegroup4.model.Question;

/* 
 * Compares two Question objects according to their:
 * - comment count			1 = 1 weight point
 * - answer count			1 = 10 weight points
 * 
 * TODO: fails for large values (int times 10...)
 * */
public class AnsweredComparator implements Comparator<Question> {

	/* 
	 * Compares two Question objects according to their:
	 * - comment count			1 = 1 weight point
	 * - answer count			1 = 10 weight points
	 * @param lhs The first question you want to compare
	 * @param rhs The second question you want to compare
	 * @returns 0 if lhs and rhs is equal, <1 if lhs is greater than rhs nad >1 vice versa.
	 */
	@Override
	public int compare(Question lhs, Question rhs) {
		int scoreLhs, scoreRhs;
		
		// add comment count to score (one comment equals to 1/2 answer)
		scoreLhs = lhs.getCommentCount();
		scoreRhs = rhs.getCommentCount();
		
		// add answer count to score
		scoreLhs += (lhs.getAnswerCount()*10);
		scoreRhs += (rhs.getAnswerCount()*10);
		
		// is there an accepted answer? (if yes add 100 score points)
		//scoreLhs += (lhs. * 100);
		//scoreRhs += (rhs. * 100);
		
		return scoreRhs - scoreLhs;
	}
}