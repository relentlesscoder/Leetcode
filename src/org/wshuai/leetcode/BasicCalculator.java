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
		int res = 0, sign = 1, cur = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(sign);
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(c == ' '){
				continue;
			}
			if(c >= '0' && c <= '9'){
				cur = cur * 10 + (c - '0');
			}else if(c == '+' || c == '-'){
				res += sign * cur;
				cur = 0;
				// calculate sign based on the sign of the current context
				sign = stack.peek() * (c == '+' ? 1 : -1);
			}else if(c == '('){
				stack.push(sign); // push current sign entering parentheses like "-("
			}else{
				stack.pop(); // pop current sign leaving parentheses like ")"
			}
		}
		return res + sign * cur;
	}
}
