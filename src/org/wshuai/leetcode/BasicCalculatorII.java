package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 03/14/2017.
 * #0227 https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorII {

	// time O(n)
	public int calculate(String s) {
		int res = 0, n = s.length(), cur = 0;
		char operator = ' ';
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < n; i++){
			char c = s.charAt(i);
			if(c == ' '){
				continue;
			}
			if(c == '+' || c == '-' || c == '*' || c == '/'){
				operator = c;
			}else{
				cur = cur * 10 + (int)(c - '0');
				if(i == n - 1 || !Character.isDigit(s.charAt(i + 1))){
					if(operator == ' ' || operator == '+'){
						stack.push(cur);
					}else if(operator == '-'){
						stack.push(-cur);
					}else if(operator == '*'){
						stack.push(stack.pop() * cur);
					}else if(operator == '/'){
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
