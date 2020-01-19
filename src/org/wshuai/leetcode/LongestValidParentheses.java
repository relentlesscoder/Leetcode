package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 11/08/2016.
 * #0032 https://leetcode.com/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {

	// time O(n), space O(n)
	public int longestValidParentheses(String s) {
		int res = 0;
		int n = s.length();
		char[] arr = s.toCharArray();
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < n; i++){
			char c = arr[i];
			if(c == '(' || stack.isEmpty() || arr[stack.peek()] == ')'){
				stack.push(i);
			}else{
				stack.pop();
				// on top of the stack is the end of last invalid substring
				res = Math.max(i - (stack.isEmpty() ? -1 : stack.peek()), res);
			}
		}
		return res;
	}

	// time O(n), space O(n)
	public int longestValidParenthesesDP(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int len = s.length();
		int max = 0;
		int[] dp = new int[len];
		for (int i = len - 2; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				// handle case like "(())"
				int end = i + dp[i + 1] + 1;
				if (end < len && s.charAt(end) == ')') {
					dp[i] = dp[i + 1] + 2;
					if (end < len - 1) {
						// handle case like "()()"
						dp[i] += dp[end + 1];
					}
				}
			}
			max = dp[i] > max ? dp[i] : max;
		}
		return max;
	}
}
