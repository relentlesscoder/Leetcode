package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 06/29/2017.
 * #0224 https://leetcode.com/problems/basic-calculator/
 */
public class BasicCalculator {

    // time O(n), space O(n)
    public int calculate(String s) {
        int n = s.length(), res = 0, sign = 1, val = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c >= '0' && c <= '9') {
                val = val * 10 + (c - '0');
                if (i == n - 1 || !Character.isDigit(s.charAt(i + 1))) {
                    res += sign * val;
                    val = 0;
                }
            } else if (c == '+' || c == '-') {
                // Calculate sign based on the sign of the current context
                sign = stack.peek() * (c == '+' ? 1 : -1);
            } else if (c == '(') {
                // Push current sign entering parentheses like "-("
                stack.push(sign);
            } else {
                // Pop current sign leaving parentheses like ")"
                stack.pop();
            }
        }
        return res;
    }
}
