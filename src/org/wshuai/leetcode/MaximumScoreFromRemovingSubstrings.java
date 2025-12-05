package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/28/2023.
 * #1717 https://leetcode.com/problems/maximum-score-from-removing-substrings/
 */
public class MaximumScoreFromRemovingSubstrings {

    private int total;

    // time O(n), space O(n)
    public int maximumGain(String s, int x, int y) {
        total = 0;
        String str = calc(s, Math.max(x, y), x > y ? "ab" : "ba");
        calc(str, Math.min(x, y), x > y ? "ba" : "ab");
        return total;
    }

    private String calc(String s, int score, String pattern) {
        char[] p = pattern.toCharArray();
        int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == p[1] && !stack.isEmpty() && s.charAt(stack.peek()) == p[0]) {
                stack.pop();
                total += score;
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
