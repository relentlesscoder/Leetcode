package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/11/2019.
 * #0921 https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 */
public class MinimumAddToMakeParenthesesValid {

	// time O(n), space O(n)
	public int minAddToMakeValid(String S) {
		int res = 0;
		if (S.length() == 0) {
			return res;
		}
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (c == '(') {
				stack.push(c);
			} else if (!stack.isEmpty() && stack.peek() == '(') {
				stack.pop();
			} else {
				stack.push(c);
			}
		}
		return stack.size();
	}
}
