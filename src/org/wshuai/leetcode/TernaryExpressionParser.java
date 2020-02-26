package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/30/2016.
 * #0439 https://leetcode.com/problems/ternary-expression-parser/
 */
public class TernaryExpressionParser {
	// time O(n)
	public String parseTernary(String expression) {
		Stack<Character> stack = new Stack<Character>();
		char[] arr = expression.toCharArray();
		int n = arr.length, i = n - 1;
		while(i >= 0){
			stack.push(arr[i--]);
			int size = stack.size();
			while(size >= 5 && stack.get(size - 2) == '?' && stack.get(size - 4) == ':'){
				char[] temp = new char[5];
				for(int j = 0; j < 5; j++){
					temp[j] = stack.pop();
				}
				stack.push(temp[0] == 'T' ? temp[2] : temp[4]);
				size -= 4;
			}
		}
		return Character.toString(stack.peek());
	}
}
