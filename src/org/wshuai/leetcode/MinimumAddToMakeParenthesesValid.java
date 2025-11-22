package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/11/2019.
 * #0921 https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 */
public class MinimumAddToMakeParenthesesValid {

    // time O(n), space O(1)
    public int minAddToMakeValid(String s) {
        int n = s.length(), open = 0, close = 0;
        for (int i = 0; i < n; i++) {
            char b = s.charAt(i);
            if (b == ')') {
                if (open > 0) {
                    open--; // Use an opening '(' to close current ')'
                } else {
                    close++; // Can't close current ')'
                }
            } else {
                open++; // Opening '(' is seen
            }
        }
        return open + close; // add "(" and ")" that are still opening
    }

    // time O(n), space O(n)
    public int minAddToMakeValidStack(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char b = s.charAt(i);
            if (b == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                stack.pop();
            } else {
                stack.push(i);
            }
        }
        return stack.size();
    }
}
