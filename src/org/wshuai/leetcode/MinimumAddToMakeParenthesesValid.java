package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/11/2019.
 * #0921 https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 */
public class MinimumAddToMakeParenthesesValid {

	// time O(n), space O(1)
	public int minAddToMakeValid(String s) {
		int left = 0, right = 0; // left and right to denote opening "(" and ")"
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (left == 0) {
					right++; // can't close current ")"
				} else {
					left--; // we have an opening "(" to close current ")"
				}
			} else {
				left++; // opening "(" seen
			}
		}
		return left + right; // add "(" and ")" that are still opening
	}

	// time O(n), space O(n)
	public int minAddToMakeValidStack(String S) {
		Stack<Character> stack = new Stack<>();
		for(char c : S.toCharArray()){
			if(c == ')' && !stack.isEmpty() && stack.peek() == '('){
				stack.pop();
			}else{
				stack.push(c);
			}
		}
		return stack.size();
	}
}
