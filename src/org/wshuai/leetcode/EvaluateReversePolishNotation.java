package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/26/2016.
 * #0150 https://leetcode.com/problems/evaluate-reverse-polish-notation/
 */
public class EvaluateReversePolishNotation {

	// time O(n), space O(n)
	public int evalRPN(String[] tokens) {
		Deque<Integer> stack = new ArrayDeque<>();
		for (String token : tokens) {
			if (token.equals("+")) {
				stack.push(stack.pop() + stack.pop());
			} else if (token.equals("-")) {
				int val = stack.pop();
				stack.push(stack.pop() - val);
			} else if (token.equals("*")) {
				stack.push(stack.pop() * stack.pop());
			} else if (token.equals("/")) {
				int val = stack.pop();
				stack.push(stack.pop() / val);
			} else {
				stack.push(Integer.parseInt(token));
			}
		}
		return stack.pop();
	}
}
