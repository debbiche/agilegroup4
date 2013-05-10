package com.agilegroup4.helpers;

import java.util.Comparator;

import com.agilegroup4.model.Tag;

public class TagComparator implements Comparator<Tag> {

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Tag lhs, Tag rhs) {
		return lhs.getTagName().compareTo(rhs.getTagName());
	}

}
