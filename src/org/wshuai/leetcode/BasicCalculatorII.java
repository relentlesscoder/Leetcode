package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 03/14/2017.
 * #0227 https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorII {
	// time O(n)
	public int calculate(String s) {
		if(s == null || s.isEmpty()){
			return 0;
		}
		Stack<Integer> stack = new Stack<>();
		int res = 0, n = s.length(), val = 0;
		char oper = ' ';
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(c == ' '){
				continue;
			}
			if(c == '+' || c == '-' || c == '*' || c == '/'){
				oper = c;
			}else{
				val = val * 10 + (int)(c - '0');
				if(i == n - 1 || !Character.isDigit(s.charAt(i + 1))){
					if(oper == ' ' || oper == '+'){
						stack.push(val);
					}
					if(oper == '-'){
						stack.push(-val);
					}
					if(oper == '*'){
						stack.push(stack.pop() * val);
					}
					if(oper == '/'){
						stack.push(stack.pop() / val);
					}
					val = 0;
				}
			}
		}
		for(int i : stack){
			res += i;
		}
		return res;
	}
}
