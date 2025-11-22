package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/19/2019.
 * #0856 https://leetcode.com/problems/score-of-parentheses/
 */
public class ScoreOfParentheses {

	// time O(n), space O(1)
	public int scoreOfParentheses(String s) {
		int res = 0, balance = 0, n = s.length();
		for (int i = 0; i < n; i++) {
			// balance is count of parent contexts for the deepest ()
			if (s.charAt(i) == '(') {
				balance++;
			} else {
				balance--;
				if (s.charAt(i - 1) == '(') {
					res += (1 << balance);
				}
			}
		}
		return res;
	}

    // time O(n), space O(n)
    public int scoreOfParenthesesStack(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // Push init value for current context
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(0); // Push init value for current context
            } else {
                int v = stack.pop();
                int w = stack.pop();
				// Calculate score for current context and add it to the
				// parent context
                stack.push(w + Math.max(2 * v, 1));
            }
        }
        return stack.pop();
    }
}
