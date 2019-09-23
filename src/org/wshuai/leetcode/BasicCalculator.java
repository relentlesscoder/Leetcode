package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 6/29/17.
 * #224 https://leetcode.com/problems/basic-calculator/
 */
public class BasicCalculator {
	//O(n), Stack
	public int calculate(String s) {
		int result = 0;
		if (s == null || s.isEmpty()) {
			return result;
		}
		int len = s.length();
		int i = 0;
		Stack<String> stk = new Stack<String>();
		while (i < len) {
			char val = s.charAt(i);
			if (val >= '0' && val <= '9') {
				int j = i;
				while (j < len && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
					j++;
				}
				stk.push(s.substring(i, j));
				i = j;
			} else if (val == ' ') {
				i++;
			} else if (val == '+' || val == '-' || val == '(') {
				stk.push(Character.toString(val));
				i++;
			} else {
				// Current character is ')', evaluate value in current parenthesis
				int temp = 0;
				while (!stk.isEmpty()) {
					int num = Integer.parseInt(stk.pop());
					String opr = stk.pop();
					if (opr.equals("(")) {
						temp += num;
						break;
					} else {
						temp += opr.equals("-") ? -num : num;
					}
				}
				stk.push(Integer.toString(temp));
				i++;
			}
		}

		while (stk.size() > 1) {
			int num = Integer.parseInt(stk.pop());
			String opr = stk.pop();
			result += opr.equals("-") ? -num : num;
		}
		result += Integer.parseInt(stk.pop());
		return result;
	}
}
