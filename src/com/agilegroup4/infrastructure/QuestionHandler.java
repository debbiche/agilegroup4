package com.agilegroup4.infrastructure;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.agilegroup4.helpers.StringUtility;
import com.agilegroup4.model.Question;
import com.agilegroup4.model.QuestionList;
import com.agilegroup4.model.Tag;
import com.agilegroup4.src.DatabaseHandler;

/*
 * Question handler that is used to query questions in the database.
 */
public class QuestionHandler extends DatabaseHandler {

	//Holds the base query for relating questions and answers.
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
			"R.favorite_count, " +
			"R.tags AS taggy " +
			"FROM posts R INNER JOIN posts D ON " +
			"D.parent_id = R.id";
			
	
	/*
	 * Creates a new question handler that is used to query questions in the database.
	 * @param context a database context.
	 */
	public QuestionHandler(Context context) {
		super(context);
	}

	/*
	 * Returns a list of question that matches the searchTerm. 
	 * Answer body, Question body and Title will be searched. 
	 * You can limit the amount of questions by providing a number to numberOfQuestions
	 * @param searchTerm The search term you want to search for. At least 2 chars needed.
	 * @param numberOfQuestions The maximum number of questions you want back.
	 * @return A list of questions matching your search term.
	 */
	public static QuestionList searchForQuestions(String searchTerm, int numberOfQuestions) {
		if(searchTerm.length() < 2)
			return new QuestionList();
			
		String searchTermLike = "%" + searchTerm + "%";
		String rawQuery = "SELECT id, title, body, comment_count, creation_date,  score, view_count, favorite_count, tags " +
				"FROM posts " +
				"WHERE post_type_id = 1 " +
				"AND ( body LIKE ? " +
				"OR title LIKE ? ) " +
				"ORDER BY title LIMIT ?";
		Cursor cursorQuestions = db.rawQuery(rawQuery,
				new String[] { searchTermLike, searchTermLike, Integer.toString(numberOfQuestions) });
		return parseQuestions(cursorQuestions);
	}
	
	/*
	 * Returns a list of question that matches the searchTerm with added tag filter. 
	 * Answer body, Question body and Title and Tags will be searched. 
	 * You can limit the amount of questions by providing a number to numberOfQuestions
	 * @param searchTerm The search term you want to search for. At least 2 chars needed.
	 * @param numberOfQuestions The maximum number of questions you want back.
	 * @param tag The tag you want to match when searching questions.
	 * @return A list of questions matching your search term and provided tag.
	 * 
	 * id,title,body,comment_count,creation_date," +
	 *								 "score,view_count,favorite_count,tags
	 */
	public static QuestionList searchForQuestionsByTag(String searchTerm, ArrayList<Tag> tagList, int numberOfQuestions) {
		// Create the query string that will be used in the rawQuery
		// Add the first 'final' parts to the string
		String rawQuery = "SELECT id, title, body, comment_count, creation_date,  score, view_count, favorite_count, tags " +
				"FROM posts " +
				"WHERE post_type_id = 1";
		
		// Add the free text part
		String searchTermLike = "\"%" + searchTerm + "%\"";
		rawQuery += " AND ( ( body LIKE " + searchTermLike +
				" OR title LIKE " + searchTermLike + " ) ";
		
		// Add the tags part
		String tagSearchTerm;
		tagSearchTerm = "tags LIKE \"%" + tagList.get(0).getTagName() + "%\"";
		for(int i = 1; i < tagList.size(); i++)
			tagSearchTerm += " AND tags LIKE \"%" + tagList.get(i).getTagName() + "%\"";
		rawQuery += " AND ( " + tagSearchTerm + " ) )";
		
		// Add the limit part
		rawQuery += " ORDER BY title LIMIT " + Integer.toString(numberOfQuestions);
		
		// Run the query
		Cursor cursorQuestions = db.rawQuery(rawQuery, null);
		return parseQuestions(cursorQuestions);
	}

	/*
	 * Parses a query result to questions and answers.
	 * @param cursor A SQLLite database cursor to step through the results.
	 * @returns A list of questions.
	 */
	protected static QuestionList parseQuestions(Cursor cursor) {
		QuestionList questions = new QuestionList();
		cursor.moveToFirst();

		while (cursor.isAfterLast() == false) {

			questions.add(new Question(cursor.getInt(0),	// id
									   cursor.getString(1), // title
									   cursor.getString(2),	// body
									   cursor.getInt(3),	// comment count
									   StringUtility.stringToDate(cursor.getString(4)), // convert to date
									   cursor.getInt(5), 	// score
									   cursor.getInt(6), 	// view count
									   cursor.getInt(7),	// favorite count
 									   cursor.getString(8))); //get tags

			cursor.moveToNext();
		}
		cursor.close();
		return questions;
	}
	
	// TODO: remove the following stuff if search feature works :)
	
//	/*
//	 * Returns a list of question that matches the searchTerm. 
//	 * Answer body, Question body and Title will be searched. 
//	 * You can limit the amount of questions by providing a number to numberOfQuestions
//	 * @param searchTerm The search term you want to search for. At least 2 chars needed.
//	 * @param numberOfQuestions The maximum number of questions you want back.
//	 * @return A list of questions matching your search term.
//	 */
//	public static QuestionList searchForQuestions(String searchTerm, int numberOfQuestions) {
//		if(searchTerm.length() < 2)
//			return new QuestionList();
//			
//		String searchTermLike = "%" + searchTerm + "%";
//		String rawQuery = baseQuestionRawQuery + " WHERE question LIKE ? " +
//				"OR answer LIKE ? " +
//				"OR R.title LIKE ? " + 
//				"ORDER BY R.title LIMIT ?";
//		Cursor cursorQuestions = db.rawQuery(rawQuery,
//				new String[] { searchTermLike, searchTermLike, searchTermLike, Integer.toString(numberOfQuestions) });
//		return parseQuestions(cursorQuestions);
//	}
//	
//	/*
//	 * Returns a list of question that matches the searchTerm with added tag filter. 
//	 * Answer body, Question body and Title and Tags will be searched. 
//	 * You can limit the amount of questions by providing a number to numberOfQuestions
//	 * @param searchTerm The search term you want to search for. At least 2 chars needed.
//	 * @param numberOfQuestions The maximum number of questions you want back.
//	 * @param tag The tag you want to match when searching questions.
//	 * @return A list of questions matching your search term and provided tag.
//	 */
//	public static QuestionList searchForQuestionsByTag(String searchTerm, String tag, int numberOfQuestions) {
//		if(searchTerm.length() < 2)
//			return new QuestionList();
//			
//		String searchTermLike = "%" + searchTerm + "%";
//		String rawQuery = baseQuestionRawQuery + " WHERE question LIKE ? " +
//				"OR answer LIKE ? " +
//				"OR R.title LIKE ? " + 
//				"AND taggy = ? " + 
//				"ORDER BY R.title LIMIT ?";
//		Cursor cursorQuestions = db.rawQuery(rawQuery,
//				new String[] { searchTermLike, searchTermLike, searchTermLike, tag, Integer.toString(numberOfQuestions) });
//		return parseQuestions(cursorQuestions);
//	}
//
//	/*
//	 * Parses a query result to questions and answers.
//	 * @param cursor A SQLLite database cursor to step through the results.
//	 * @returns A list of questions.
//	 */
//	protected static QuestionList parseQuestions(Cursor cursor) {
//		QuestionList questions = new QuestionList();
//		cursor.moveToFirst();
//		int questionCounter = 0;
//
//		while (cursor.isAfterLast() == false) {
//
//			questions.add(new Question(cursor.getInt(0),
//									   cursor.getString(1), 
//									   cursor.getString(2),
//									   cursor.getInt(6),
//									   StringUtility.stringToDate(cursor.getString(9)), // convert to date
//									   cursor.getInt(10), // score
//									   cursor.getInt(11), // view count
//									   cursor.getInt(12),
//									   cursor.getString(13))); //get tags
//
//			questions.get(questionCounter);
//			System.out.println(cursor.getInt(4));
//			for (int i = 0; i < cursor.getInt(4) - 1; i++) {
//				questions
//						.get(questionCounter)
//						.getAnswers()
//						.add(new Answer(cursor.getInt(7),
//								cursor.getString(3), cursor.getInt(8)));
//				cursor.moveToNext();
//
//			}
//
//			cursor.moveToNext();
//			questionCounter++;
//		}
//		cursor.close();
//		return questions;
//	}

}
