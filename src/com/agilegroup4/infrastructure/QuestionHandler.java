package com.agilegroup4.infrastructure;

import android.content.Context;
import android.database.Cursor;

import com.agilegroup4.helpers.StringUtility;
import com.agilegroup4.model.Answer;
import com.agilegroup4.model.Question;
import com.agilegroup4.model.QuestionList;
import com.agilegroup4.model.User;
import com.agilegroup4.src.DatabaseHandler;

/*
 * Question handler that is used to query questions in the database.
 */
public class QuestionHandler extends DatabaseHandler {

	//Holds the base query for relating questions and anwers.
	protected static String baseQuestionRawQuery = "SELECT R.id AS id, " +
			"R.title AS title, " +
			"R.body AS question, "  +
			"D.body AS answer, " +
			"R.answer_count, " +
			"D.parent_id AS parentid, " +
			"R.comment_count, " +
			"D.id AS answer_id, " +
			"D.comment_count, " +
			"R.creation_date, " + 
			"R.score, " +
			"R.view_count, " +
			"R.favorite_count " +
			"FROM posts R INNER JOIN posts D ON " +
			"D.parent_id = R.id";
	
	/*
	 * Creates a new question handler that is used to query questions in the database.
	 */
	public QuestionHandler(Context context) {
		super(context);
	}

	/*
	 * Returns a list of question that matches the searchTerm. 
	 * Answer body, Question body and Title will be searched. 
	 * You can limit the amount of questions by providing a number to numberOfQuestions
	 */
	public static QuestionList searchForQuestions(String searchTerm, int numberOfQuestions) {
		if(searchTerm.length() < 2)
			return new QuestionList();
			
		String searchTermLike = "%" + searchTerm + "%";
		String rawQuery = baseQuestionRawQuery + " WHERE question LIKE ? " +
				"OR answer LIKE ? " +
				"OR R.title LIKE ? " + 
				"ORDER BY R.title LIMIT ?";
		Cursor cursorQuestions = db.rawQuery(rawQuery,
				new String[] { searchTermLike, searchTermLike, searchTermLike, Integer.toString(numberOfQuestions) });
		return parseQuestions(cursorQuestions);
	}

	/*
	 * Parses a query result to questions and answers.
	 */
	protected static QuestionList parseQuestions(Cursor cursor) {
		QuestionList questions = new QuestionList();
		cursor.moveToFirst();
		int questionCounter = 0;

		while (cursor.isAfterLast() == false) {

			questions.add(new Question(cursor.getInt(0),
									   cursor.getString(1), 
									   cursor.getString(2),
									   cursor.getInt(6),
									   StringUtility.stringToDate(cursor.getString(9)), // convert to date
									   cursor.getInt(10), // score
									   cursor.getInt(11), // view count
									   cursor.getInt(12)));

			questions.get(questionCounter);
			for (int i = 0; i < cursor.getInt(4) - 1; i++) {
				questions
						.get(questionCounter)
						.getAnswers()
						.add(new Answer(cursor.getInt(7),
								cursor.getString(3), cursor.getInt(8)));
				cursor.moveToNext();

			}

			cursor.moveToNext();
			questionCounter++;
		}
		cursor.close();
		return questions;
	}

}
