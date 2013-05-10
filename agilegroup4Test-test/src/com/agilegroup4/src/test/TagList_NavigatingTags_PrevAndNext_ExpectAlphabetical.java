package com.agilegroup4.src.test;

import java.util.ArrayList;

import com.agilegroup4.helpers.StringUtility;
import com.agilegroup4.model.Tag;
import com.agilegroup4.model.TagList;

import junit.framework.TestCase;

public class TagList_NavigatingTags_PrevAndNext_ExpectAlphabetical extends
		TestCase {

	private TagList _tagsList;
	
	public TagList_NavigatingTags_PrevAndNext_ExpectAlphabetical(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ArrayList<Tag> allTags = new ArrayList<Tag>() {{
			add(new Tag(1, "Alpha"));
			add(new Tag(2, "Beta"));
			add(new Tag(3, "Charlie"));
		}};
		_tagsList = new TagList(allTags);
	}
	
	public void testTagListNavigateTags() {
		
		//assertEquals(resultString, stringWithOutHTML);
	}

}
