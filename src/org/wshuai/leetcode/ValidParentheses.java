package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/26/2016.
 * #0020 https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {
	// time O(n), space O(n)
	public boolean isValid(String s) {
		Stack<Character> openBrackets = new Stack<>();
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(c == '}' || c == ']' || c == ')'){
				if(openBrackets.isEmpty() || openBrackets.peek() != c){
					return false;
				}
				openBrackets.pop();
			}else{
				openBrackets.push(c == '{' ? '}' : (c == '(' ? ')' : ']'));
			}
		}
		return openBrackets.isEmpty();
	}
}
