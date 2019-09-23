package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 8/10/19.
 * #844 https://leetcode.com/problems/backspace-string-compare/
 */
public class BackspaceStringCompare {
	public boolean backspaceCompare(String S, String T) {
		Stack<Character> stack1 = new Stack<Character>();
		char[] arr1 = S.toCharArray();
		for (char c : arr1) {
			if (c != '#') {
				stack1.push(c);
			} else if (!stack1.isEmpty()) {
				stack1.pop();
			}
		}
		Stack<Character> stack2 = new Stack<Character>();
		char[] arr2 = T.toCharArray();
		for (char c : arr2) {
			if (c != '#') {
				stack2.push(c);
			} else if (!stack2.isEmpty()) {
				stack2.pop();
			}
		}
		if (stack1.size() != stack2.size()) {
			return false;
		}
		while (!stack1.isEmpty()) {
			if (stack1.pop() != stack2.pop()) {
				return false;
			}
		}
		return true;
	}
}
