package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 9/19/19.
 * #856 https://leetcode.com/problems/score-of-parentheses/
 */
public class ScoreOfParentheses {
	public int scoreOfParentheses(String S) {
		int res = 0;
		int i = 0;
		Stack<Integer> stack = new Stack<>();
		while (i < S.length()) {
			// find the next ) to construct a balanced parentheses
			if (S.charAt(i) == '(') {
				stack.push(i);
			} else {
				int l = stack.pop();
				if (stack.isEmpty()) {
					// if the current contains child balanced parentheses, solve it recursively
					res += i == l + 1 ? 1 : 2 * scoreOfParentheses(S.substring(l + 1, i));
				}
			}
			i++;
		}
		return res;
	}
}
