package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/17/2025.
 * #3561 https://leetcode.com/problems/resulting-string-after-adjacent-removals/
 */
public class ResultingStringAfterAdjacentRemovals {

    // time O(n), space O(n)
    public String resultingString(String s) {
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() &&
                    (Math.abs(s.charAt(stack.peek()) - c) % 26 == 1
                            || Math.abs(s.charAt(stack.peek()) - c) % 26 == 25)) {
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
