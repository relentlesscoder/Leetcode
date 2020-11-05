package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/11/2019.
 * #0921 https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 */
public class MinimumAddToMakeParenthesesValid {

	// time O(n), space O(n)
	public int minAddToMakeValid(String S) {
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
