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
        int res = 0, currentNumber = 0, lastNumber = 0, n = s.length();
        char operation = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }
            if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == n - 1) {
                if (operation == '+') {
                    res += lastNumber;
                    lastNumber = currentNumber;
                } else if (operation == '-') {
                    res += lastNumber;
                    lastNumber = -currentNumber;
                } else if (operation == '*') {
                    lastNumber *= currentNumber;
                } else {
                    lastNumber /= currentNumber;
                }
                operation = c;
                currentNumber = 0;
            }
        }
        return res + lastNumber;
    }

    // time O(n), space O(n)
    public int calculateStack(String s) {
        int res = 0, currentNumber = 0, n = s.length();
        char operation = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }
            if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == n - 1) {
                if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '-') {
                    stack.push(-currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = c;
                currentNumber = 0;
            }
        }
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
