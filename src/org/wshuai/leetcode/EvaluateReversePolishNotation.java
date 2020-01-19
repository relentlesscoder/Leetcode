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
			if(t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")){
				int right = stack.pop();
				int left = stack.pop();
				stack.push(calculate(left, right, t));
			}else{
				stack.push(Integer.parseInt(t));
			}
		}
		return stack.peek();
	}

	private int calculate(int left, int right, String oper){
		if(oper.equals("-")){
			return left - right;
		}else if(oper.equals("*")){
			return left * right;
		}else if(oper.equals("/")){
			return left / right;
		}
		return left + right;
	}
}
