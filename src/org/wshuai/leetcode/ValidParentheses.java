package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/26/2016.
 * #0020 https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    // time O(n), space O(n)
    public boolean isValid(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char b = s.charAt(i);
            if (b == '(' || b == '{' || b == '[') {
                stack.push(i);
                continue;
            }
            if (!stack.isEmpty() && matching(s.charAt(stack.peek()), b)) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private boolean matching(char a, char b) {
        return (a == '(' && b == ')') || (a == '{' && b == '}') || (a == '[' && b == ']');
    }
}
