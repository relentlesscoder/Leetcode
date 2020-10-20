package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 08/26/2016.
 * #0150 https://leetcode.com/problems/evaluate-reverse-polish-notation/
 */
public class EvaluateReversePolishNotation {

	// time O(n), space O(n)
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for(String t : tokens){
			if(Character.isDigit(t.charAt(t.length() - 1))){
				stack.push(Integer.parseInt(t));
			}else{
				int b = stack.pop(), a = stack.pop();
				stack.push(eval(a, b, t.charAt(0)));
			}
		}
		return stack.peek();
	}

	private int eval(int a, int b, char op){
		if(op == '-'){
			return a - b;
		}else if(op == '*'){
			return a * b;
		}else if(op == '/'){
			return a / b;
		}
		return a + b;
	}
}
