package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/12/2020.
 * #1614 https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
 */
public class MaximumNestingDepthOfTheParentheses {

    // time O(n), space O(1)
    public int maxDepth(String s) {
        int res = 0, depth = 0;
        for (char c : s.toCharArray()) {
            if (c == ')') {
                depth--;
            } else if (c == '(') {
                res = Math.max(res, ++depth);
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int maxDepthStack(String s) {
        int res = 0, n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                stack.pop();
            } else if (c == '(') {
                stack.push(i);
                res = Math.max(res, stack.size());
            }
        }
        return res;
    }
}
