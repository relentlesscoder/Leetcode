package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/07/2019.
 * #1021 https://leetcode.com/problems/remove-outermost-parentheses/
 */
public class RemoveOutermostParentheses {

    // time O(n), space O(n)
    public String removeOuterParentheses(String s) {
        int n = s.length(), score = 0;
        char[] arr = s.toCharArray();
        for (int i = 0, j = 0; i < n; i++) {
            score += arr[i] == '(' ? 1 : -1;
            if (score == 0) {
                arr[i] = '#';
                arr[j] = '#';
                j = i + 1;
            }
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
    public String removeOuterParenthesesStack(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == ')' && !stack.isEmpty() && arr[stack.peek()] == '(') {
                int idx = stack.pop();
                if (stack.isEmpty()) {
                    arr[idx] = '#';
                    arr[i] = '#';
                }
            } else {
                stack.push(i);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (arr[i] != '#') {
                res.append(arr[i]);
            }
        }
        return res.toString();
    }
}
