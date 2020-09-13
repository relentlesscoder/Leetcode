package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 11/13/19.
 * #1249 https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 */
public class MinimumRemoveToMakeValidParentheses {

	// time O(n), space O(n)
	public String minRemoveToMakeValid(String s) {
		int n = s.length();
		StringBuilder res = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		boolean[] delete = new boolean[n];
		for(int i = 0; i < n; i++){
			char c = s.charAt(i);
			if(c == '('){
				stack.push(i);
			}else if(c == ')'){
				if(stack.isEmpty()){
					delete[i] = true;
				}else{
					stack.pop();
				}
			}
		}
		while(!stack.isEmpty()){
			delete[stack.pop()] = true;
		}
		for(int i = 0; i < n; i++){
			if(delete[i]){
				continue;
			}
			res.append(s.charAt(i));
		}
		return res.toString();
	}
}
