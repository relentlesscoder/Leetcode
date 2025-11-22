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
        int n = s.length();
        char[] arr = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == ')' && !stack.isEmpty() && arr[stack.peek()] == '(') {
                stack.pop();
            } else if (arr[i] == '(' || arr[i] == ')') {
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            arr[stack.pop()] = '#';
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (arr[i] != '#') {
                res.append(arr[i]);
            }
        }
        return res.toString();
    }

    // time O(n), space O(n)
    public String minRemoveToMakeValid(String s) {
        int n = s.length(), open = 0, unclosed = 0;
        char[] arr = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == ')' && unclosed == 0) { // Skip ')' that we can't close
                continue;
            } else if (arr[i] == ')') { // Close ')' that we can
                unclosed--;
            } else if (arr[i] == '(') { // Count new open '('
                open++;
                unclosed++;
            }
            stack.push(i);
        }
        open -= unclosed; // Calculate valid opens
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            int idx = stack.pollLast();
            if (arr[idx] == '(' && open == 0) { // Remove the extra opens
                continue;
            } else if (arr[idx] == '(') { // Add valid opens greedily from left
                open--;
            }
            res.append(arr[idx]);
        }
        return res.toString();
    }
}
