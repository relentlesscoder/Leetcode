package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/26/2016.
 * #0020 https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {

	// time O(n), space O(n)
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (c == '(' || c == '{' || c == '[') {
				stack.push(c);
			} else if (!stack.isEmpty() && isMatch(stack.peek(), c)) {
				stack.pop();
			} else {
				return false;
			}
		}
		return stack.isEmpty();
	}

	private boolean isMatch(char a, char b) {
		return (a == '(' && b == ')') || (a == '{' && b == '}') || (a == '[' && b == ']');
	}
}
