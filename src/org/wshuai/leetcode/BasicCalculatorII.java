package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 03/14/2017.
 * #0227 https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorII {

	// time O(n), space O(n)
	public int calculate(String s) {
		int n = s.length(), res = 0, cur = 0;
		char op = ' ';
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < n; i++){
			char c = s.charAt(i);
			if(c == ' '){
				continue;
			}
			if(!Character.isDigit(c)){
				op = c;
			}else{
				cur = cur * 10 + (c - '0');
				if(i == n - 1 || !Character.isDigit(s.charAt(i + 1))){
					if(op == ' ' || op == '+'){
						stack.push(cur);
					}else if(op == '-'){
						stack.push(-cur);
					}else if(op == '*'){
						stack.push(stack.pop() * cur);
					}else{
						stack.push(stack.pop() / cur);
					}
					cur = 0;
				}
			}
		}
		while(!stack.isEmpty()){
			res += stack.pop();
		}
		return res;
	}
}
