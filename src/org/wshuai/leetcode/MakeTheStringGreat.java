package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/10/2020.
 * #1544 https://leetcode.com/problems/make-the-string-great/
 */
public class MakeTheStringGreat {

    // time O(n), space O(n)
    public String makeGood(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && Math.abs(s.charAt(stack.peek()) - c) == 32) {
                stack.pop();
            } else {
                stack.push(i);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(s.charAt(stack.pollLast()));
        }
        return res.toString();
    }
}
