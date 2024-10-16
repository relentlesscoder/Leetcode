package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/11/2019.
 * #0921 https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 */
public class MinimumAddToMakeParenthesesValid {

	// time O(n), space O(1)
	public int minAddToMakeValid(String s) {
		int open = 0, close = 0;
		for (char c : s.toCharArray()) {
			if (c == ')') {
				if (open > 0) {
					open--; // we have an opening "(" to close current ")"
				} else {
					close++; // can't close current ")"
				}
			} else {
				open++; // opening "(" seen
			}
		}
		return open + close; // add "(" and ")" that are still opening
	}

	// time O(n), space O(n)
	public int minAddToMakeValidStack(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		for (char c : s.toCharArray()) {
			if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
				stack.pop();
			} else {
				stack.push(c);
			}
		}
		return stack.size();
	}
}
