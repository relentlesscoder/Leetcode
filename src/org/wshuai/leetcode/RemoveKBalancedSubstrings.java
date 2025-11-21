package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/21/2025.
 * #3703 https://leetcode.com/problems/remove-k-balanced-substrings/
 */
public class RemoveKBalancedSubstrings {

    // time O(n), space O(n)
    public String removeSubstring(String s, int k) {
        Deque<int[]> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek()[0] == c) {
                stack.peek()[1]++;
            } else {
                stack.push(new int[]{c, 1});
            }
            int[] curr = stack.pop();
            if (curr[0] == ')' && curr[1] == k && !stack.isEmpty()
                    && stack.peek()[0] == '(' && stack.peek()[1] >= k) {
                int[] prev = stack.pop();
                if (prev[1] > k) {
                    stack.push(new int[]{prev[0], prev[1] - k});
                }
            } else {
                stack.push(curr);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            int[] curr = stack.pollLast();
            int count = curr[1];
            while (count-- > 0) {
                res.append((char) curr[0]);
            }
        }
        return res.toString();
    }
}
