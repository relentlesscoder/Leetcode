package org.wshuai.leetcode;

import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 03/10/2017.
 * #0385 https://leetcode.com/problems/mini-parser/
 */
public class MiniParser {
	// time O(n), space O(n)
	public NestedInteger deserialize(String s) {
		if(!s.startsWith("[")){
			return new NestedInteger(Integer.parseInt(s));
		}
		int i = 1, j = 1, n = s.length();
		NestedInteger res = new NestedInteger();
		Stack<NestedInteger> stack = new Stack<>();
		stack.push(res);
		while(j < n){
			char c = s.charAt(j);
			if(c == '['){
				NestedInteger cur = new NestedInteger();
				stack.peek().add(cur);
				stack.push(cur);
				i = j + 1;
			}else if(c == ']' || c == ','){
				String str = s.substring(i, j);
				if(str.length() > 0){
					stack.peek().add(new NestedInteger(Integer.parseInt(str)));
				}
				if(c == ']'){
					stack.pop();
				}
				i = j + 1;
			}
			j++;
		}
		return res;
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
