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
		if(s == null || s.isEmpty()){
			return 0;
		}
		int res = 0, sign = 1, num = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(sign);

		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(c >= '0' && c <= '9'){
				num = num * 10 + (c - '0');
			}else if(c == '+' || c == '-'){
				res += sign * num;
				sign = stack.peek() * (c == '+' ? 1 : -1);
				num = 0;
			}else if(c == '('){
				stack.push(sign);
			}else if(c == ')'){
				stack.pop();
			}
		}

		res += sign * num;
		return res;
	}
}
