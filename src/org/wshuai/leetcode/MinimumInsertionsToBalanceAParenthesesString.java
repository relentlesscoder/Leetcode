package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/11/2020.
 * #1541 https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
 */
public class MinimumInsertionsToBalanceAParenthesesString {

    // time O(n), space O(1)
    public int minInsertions(String s) {
        int neededRight = 0;  // Increment by 2 for each '('.
        int missingLeft = 0;  // Increment by 1 for each missing '('.
        int missingRight = 0; // Increment by 1 for each missing ')'.

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (neededRight % 2 == 1) {
                    // e.g. "()(..."
                    ++missingRight;
                    --neededRight;
                }
                neededRight += 2;
            } else if (--neededRight < 0) { // c == ')'
                // e.g. "()))..."
                ++missingLeft;
                neededRight += 2;
            }
        }
        return neededRight + missingLeft + missingRight;
    }

    // time O(n), space O(n)
    public int minInsertionsStack(String s) {
        // 1 means ()
        // 2 means (
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (stack.isEmpty()) {
                    stack.push(1);
                    res++;
                } else if (stack.peek() == 1) {
                    stack.pop();
                } else {
                    stack.pop();
                    stack.push(1);
                }
            } else {
                if (stack.isEmpty()) {
                    stack.push(2);
                } else if (stack.peek() == 1) {
                    stack.pop();
                    stack.push(2);
                    res++;
                } else {
                    stack.push(2);
                }
            }
        }
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
