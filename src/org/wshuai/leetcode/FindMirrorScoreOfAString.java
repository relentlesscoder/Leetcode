package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 11/19/2025.
 * #3412 https://leetcode.com/problems/find-mirror-score-of-a-string/
 */
public class FindMirrorScoreOfAString {

    // time O(n), space O(n)
    public long calculateScore(String s) {
        long res = 0;
        int n = s.length();
        Deque<Integer>[] stacks = new ArrayDeque[26];
        Arrays.setAll(stacks, i -> new ArrayDeque<>());
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            Deque<Integer> stack = stacks[25 - (c - 'a')];
            if (!stack.isEmpty()) {
                res += i - stack.pop();
            } else {
                stacks[c - 'a'].push(i);
            }
        }
        return res;
    }
}
