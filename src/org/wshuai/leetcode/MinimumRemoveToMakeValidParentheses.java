package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/13/2019.
 * #1249 https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 */
public class MinimumRemoveToMakeValidParentheses {

    // time O(n), space O(n)
    public String minRemoveToMakeValidStack(String s) {
        boolean[] indexesToRemove = new boolean[s.length()];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove[i] = true;
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            indexesToRemove[stack.pop()] = true;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (indexesToRemove[i]) {
                continue;
            }
            res.append(s.charAt(i));
        }
        return res.toString();
    }

    // time O(n), space O(n)
    public String minRemoveToMakeValid(String s) {
        int balance = 0, openCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                balance++;
                openCount++;
            } else if (c == ')') {
                if (balance == 0) {
                    continue;
                }
                balance--;
            }
            sb.append(c);
        }
        openCount -= balance;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                openCount--;
                if (openCount < 0) {
                    continue;
                }
            }
            res.append(c);
        }
        return res.toString();
    }
}
