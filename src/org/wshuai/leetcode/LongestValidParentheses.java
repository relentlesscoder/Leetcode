package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/08/2016.
 * #0032 https://leetcode.com/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {

    // time O(n), space O(1)
    public int longestValidParentheses(String s) {
        /*
         * Visual Intuition about approach 4:
         *   0123456
         *   ()((())
         * Do a visual scan left to right, you will immediately know only [0..1]
         * is valid. Here's why we go from
         * ( --> () -->
         * Found it! Let's note this valid string [0,1] and keep looking for longer
         * strings.
         * ()( --> ()(( --> ()((( --> ()((() --> ()((())
         * The string scanned this way is asymmetric from index 2 up to every index
         * after that. So that's why we miss this longest string of length 4 from [3..6]
         * during left to right scan.
         * Then let's do a visual scan from right to left and you will notice [3..6] right
         * away which was missed in our previous scan.
         * Here's how:
         * (()) <-- ()) <-- )) <-- )
         * Wow, such symmetry! Let's note this string [3..6] as one of the candidates.
         * */
        int res = 0, n = s.length();
        char[] arr = s.toCharArray();
        for (int idx = 0, open = 0, close = 0; idx < n; idx++) {
            if (arr[idx] == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                res = Math.max(res, close << 1);
            }
            if (close > open) {
                close = open = 0;
            }
        }
        for (int idx = n - 1, open = 0, close = 0; idx >= 0; idx--) {
            if (arr[idx] == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                res = Math.max(res, open << 1);
            }
            if (open > close) {
                close = open = 0;
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int longestValidParenthesesStack(String s) {
        int res = 0, n = s.length();
        char[] arr = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // Add sentinel
        for (int i = 0; i < n; i++) {
            if (arr[i] == ')' && stack.size() > 1 && arr[stack.peek()] == '(') {
                stack.pop();
                // Greedily remove the opening brackets, the longest valid substring
                // ends at index i is [stack.peek() + 1, i]
                res = Math.max(res, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int longestValidParenthesesDP(String s) {
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
