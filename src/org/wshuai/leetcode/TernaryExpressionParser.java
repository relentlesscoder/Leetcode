package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/30/16.
 * #439 https://leetcode.com/problems/ternary-expression-parser/
 */
public class TernaryExpressionParser {
	public String parseTernary(String expression) {
		if (expression == null || expression.length() == 0) {
			return "";
		}
		Stack<Character> stack = new Stack<Character>();
		int len = expression.length();
		int i = len - 1;
		//The expression needs to be parsed from right to left
		while (i >= 0) {
			stack.push(expression.charAt(i));
			i--;
			int size = stack.size();
			while (size >= 5 && stack.get(size - 2) == '?' && stack.get(size - 4) == ':') {
				char[] arr = new char[5];
				for (int j = 0; j < 5; j++) {
					arr[j] = stack.pop();
				}
				char x = arr[0] == 'T' ? arr[2] : arr[4];
				stack.push(x);
				size = stack.size();
			}
		}
		return Character.toString(stack.peek());
	}
}
