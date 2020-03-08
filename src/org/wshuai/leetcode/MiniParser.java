package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 03/10/2017.
 * #0385 https://leetcode.com/problems/mini-parser/
 */
public class MiniParser {
	// time O(n), space O(n)
	public NestedInteger deserialize(String s) {
		if(!s.startsWith("[")){
			return new NestedInteger(Integer.parseInt(s));
		}
		int i = 1, j = 1, n = s.length();
		NestedInteger res = new NestedInteger();
		Stack<NestedInteger> stack = new Stack<>();
		stack.push(res);
		while(j < n){
			char c = s.charAt(j);
			if(c == '['){
				NestedInteger cur = new NestedInteger();
				stack.peek().add(cur);
				stack.push(cur);
				i = j + 1;
			}else if(c == ']' || c == ','){
				String str = s.substring(i, j);
				if(str.length() > 0){
					stack.peek().add(new NestedInteger(Integer.parseInt(str)));
				}
				if(c == ']'){
					stack.pop();
				}
				i = j + 1;
			}
			j++;
		}
		return res;
	}
}
