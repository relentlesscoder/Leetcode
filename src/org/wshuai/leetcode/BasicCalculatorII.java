package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 03/14/2017.
 * #0227 https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorII {

    // time O(n), space O(1)
    public int calculate(String s) {
        int res = 0, n = s.length(), curr = 0, prev = 0;
        char opr = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curr = curr * 10 + (c - '0');
            }
            if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == n - 1) {
                if (opr == '+') {
                    res += prev;
                    prev = curr;
                } else if (opr == '-') {
                    res += prev;
                    prev = -curr;
                } else if (opr == '*') {
                    prev *= curr;
                } else if (opr == '/') {
                    prev /= curr;
                }
                opr = c;
                curr = 0;
            }
        }
        return res + prev;
    }

    // time O(n), space O(n)
    public int calculateStack(String s) {
        int res = 0, n = s.length(), val = 0;
        char opr = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                val = val * 10 + (c - '0');
            }
            if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == n - 1) {
                if (opr == '+') {
                    stack.push(val);
                } else if (opr == '-') {
                    stack.push(-val);
                } else if (opr == '*') {
                    stack.push(stack.pop() * val);
                } else if (opr == '/') {
                    stack.push(stack.pop() / val);
                }
                opr = c;
                val = 0;
            }
        }
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
