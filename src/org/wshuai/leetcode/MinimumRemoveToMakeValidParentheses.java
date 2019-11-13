package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by Wei on 11/13/19.
 * #1249 https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 */
public class MinimumRemoveToMakeValidParentheses {
	public String minRemoveToMakeValid(String s) {
		Set<Integer> lst = new HashSet<>();
		Stack<Integer> stack = new Stack<>();
		int i = 0;
		while(i < s.length()){
			if(s.charAt(i) == '('){
				stack.push(i);
			}else if(s.charAt(i) == ')'){
				if(stack.isEmpty()){
					lst.add(i);
				}else{
					stack.pop();
				}
			}
			i++;
		}
		while(!stack.isEmpty()){
			lst.add(stack.pop());
		}
		StringBuilder sb = new StringBuilder();
		i = 0;
		while(i < s.length()){
			if(!lst.contains(i)){
				sb.append(s.charAt(i));
			}
			i++;
		}
		return sb.toString();
	}
}
