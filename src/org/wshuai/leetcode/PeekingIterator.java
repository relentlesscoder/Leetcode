package org.wshuai.leetcode;

import java.util.Iterator;

/**
 * Created by Wei on 11/23/2016.
 * #0284 https://leetcode.com/problems/peeking-iterator/
 */
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
public class PeekingIterator implements Iterator<Integer> {
	private Iterator<Integer> iterator;
	private Integer cur;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iterator = iterator;
		cur = iterator.next();
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		return cur;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		Integer res = cur;
		cur = iterator.hasNext() ? iterator.next() : null;
		return res;
	}

	@Override
	public boolean hasNext() {
		return cur != null;
	}
}
