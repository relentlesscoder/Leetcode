package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 06/29/2017.
 * #0224 https://leetcode.com/problems/basic-calculator/
 */
public class BasicCalculator {

	// time O(n), space O(n)
	// the top comment at https://leetcode.com/problems/basic-calculator/discuss/62361/Iterative-Java-solution-with-stack
	public int calculate(String s) {
		int res = 0, n = s.length(), sign = 1, val = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		for(int i = 0; i < n; i++){
			char c = s.charAt(i);
			if(c == ' '){
				continue;
			}
			if(c >= '0' && c <= '9'){
				val = val * 10 + (c - '0');
				if(i == n - 1 || !Character.isDigit(s.charAt(i + 1))){
					res += sign * val;
					val = 0;
				}
			}else if(c == '+' || c == '-'){
				sign = stack.peek() * (c == '+' ? 1 : -1); // calculate sign based on the sign of the current context
			}else if(c == '('){
				stack.push(sign); // push current sign entering parentheses like "-("
			}else{
				stack.pop(); // pop current sign leaving parentheses like ")"
			}
		}
		return res;
	}
}
