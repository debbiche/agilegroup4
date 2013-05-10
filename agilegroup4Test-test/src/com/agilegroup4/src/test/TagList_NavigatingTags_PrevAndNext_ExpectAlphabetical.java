package com.agilegroup4.src.test;

import java.util.ArrayList;

import com.agilegroup4.model.Tag;
import com.agilegroup4.model.TagList;

import junit.framework.TestCase;

public class TagList_NavigatingTags_PrevAndNext_ExpectAlphabetical extends
		TestCase {

	private TagList _tagsList;
	private Tag _alpha;
	private Tag _beta;
	private Tag _charlie;
	
	public TagList_NavigatingTags_PrevAndNext_ExpectAlphabetical(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		_alpha = new Tag(1, "Alpha");
		_beta = new Tag(2, "Beta");
		_charlie = new Tag(3, "Charlie");
		ArrayList<Tag> allTags = new ArrayList<Tag>() {{
			add(_alpha);
			add(_beta);
			add(_charlie);
		}};
		_tagsList = new TagList(allTags);
		_tagsList.add(_alpha);
	}
	
	public void testTagListNavigateTags() {
		Tag expectedBeta = _tagsList.getNext();
		Tag expectedAlpha = _tagsList.getPrevious();
		_tagsList.getNext();
		Tag expectedCharlie = _tagsList.getNext();
		
		assertEquals(expectedBeta, _beta);
		assertEquals(expectedAlpha, _alpha);
		assertEquals(expectedCharlie, _charlie);
		
	}

}
