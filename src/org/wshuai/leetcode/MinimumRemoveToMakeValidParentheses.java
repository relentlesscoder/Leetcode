package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 11/13/2019.
 * #1249 https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 */
public class MinimumRemoveToMakeValidParentheses {

	// time O(n), space O(n)
	public String minRemoveToMakeValidStack(String s) {
		int[] indexToRemove = new int[s.length()];
		Stack<Integer> stack = new Stack();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else if (s.charAt(i) == ')') {
				if (stack.isEmpty()) {
					indexToRemove[i] = 1; // record the index of unmatched ")"
				} else {
					stack.pop(); // match the ")" with "(" we've seen previously
				}
			}
		}
		while (!stack.isEmpty()) {
			indexToRemove[stack.pop()] = 1; // record the index of unmatched "("
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (indexToRemove[i] == 0) {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}

	// time O(n), space O(n)
	public String minRemoveToMakeValid(String s) {
		// first pass to remove invalid ")"
		StringBuilder sb = new StringBuilder();
		int balance = 0, openCount = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				openCount++;
				balance++;
			} else if (c == ')') {
				if (balance == 0) {
					continue;
				}
				balance--;
			}
			sb.append(c);
		}

		// second pass to remove extra "("
		StringBuilder res = new StringBuilder();
		int openNeeded = openCount - balance;
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c == '(') {
				if (openNeeded-- <= 0) {
					continue;
				}
			}
			res.append(c);
		}
		return res.toString();
	}
}
