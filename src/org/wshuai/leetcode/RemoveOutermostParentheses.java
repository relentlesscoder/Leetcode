package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 8/7/19.
 * #1021 https://leetcode.com/problems/remove-outermost-parentheses/
 */
public class RemoveOutermostParentheses {
	public String removeOuterParentheses(String S) {
		if (S == null || S.length() == 0) {
			return "";
		}
		Stack<Character> stack = new Stack<Character>();
		int i = S.length() - 1;
		int counter = 0;
		int last = 0;
		while (i >= 0) {
			char c = S.charAt(i);
			if (c == ')') {
				counter++;
				stack.push(c);
			} else {
				counter--;
				if (counter == 0) {
					stack.remove(last);
					last = stack.size();
				} else {
					stack.push(c);
				}
			}
			i--;
		}
		if (stack.empty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.empty()) {
			sb.append("" + stack.pop());
		}
		return sb.toString();
	}
}
