package org.wshuai.leetcode;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 11/14/2016.
 * #0341 https://leetcode.com/problems/flatten-nested-list-iterator/
 */
public class FlattenNestedListIterator implements Iterator<Integer> {
	private Stack<NestedInteger> stack = new Stack<>();

	public FlattenNestedListIterator(List<NestedInteger> nestedList) {
		for(int i = nestedList.size() - 1; i >= 0; i--){
			stack.push(nestedList.get(i));
		}
	}

	@Override
	public Integer next() {
		return stack.pop().getInteger();
	}

	@Override
	public boolean hasNext() {
		while(!stack.isEmpty()){
			NestedInteger ni = stack.peek();
			if(ni.isInteger()){
				return true;
			}
			stack.pop();
			List<NestedInteger> list = ni.getList();
			for(int i = list.size() - 1; i >= 0; i--){
				stack.push(list.get(i));
			}
		}
		return false;
	}

	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	private class NestedInteger {
		// Constructor initializes an empty nested list.
		public NestedInteger() {
		}

		// Constructor initializes a single integer.
		public NestedInteger(int value) {
		}

		// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger() {
			return false;
		}

		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger() {
			return 0;
		}

		// Set this NestedInteger to hold a single integer.
		public void setInteger(int value) {
		}

		// Set this NestedInteger to hold a nested list and adds a nested integer to it.
		public void add(NestedInteger ni) {
		}

		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList() {
			return null;
		}
	}
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
