package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/25/2019.
 * #1190 https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {

	// time O(n), space O(n)
	public String reverseParentheses(String s) {
		int n = s.length();
		int[] pair = new int[n];
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			if (c == '(') {
				stack.push(i);
			} else if (c == ')') {
				int j = stack.pop();
				pair[i] = j;
				pair[j] = i;
			}
		}
		StringBuilder res = new StringBuilder();
		int index = 0, step = 1;
		for (; index < n; index += step) {
			char c = s.charAt(index);
			if (c == '(' || c == ')') {
				index = pair[index];
				step = -step;
			} else {
				res.append(c);
			}
		}
		return res.toString();
	}

	// time O(n^2), space O(n)
	public String reverseParenthesesStack(String s) {
		StringBuilder res = new StringBuilder();
		int n = s.length();
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			if (c == '(') {
				// Push current length (point to next non-parentheses character)
				stack.push(res.length());
			} else if (c == ')') {
				int open = stack.pop();
				// Reverse the character sequence in current parentheses pair
				reverse(res, open, res.length() - 1);
			} else {
				res.append(c);
			}
		}
		return res.toString();
	}

	private void reverse(StringBuilder sb, int start, int end) {
		while (start < end) {
			char temp = sb.charAt(start);
			sb.setCharAt(start++, sb.charAt(end));
			sb.setCharAt(end--, temp);
		}
	}
}
