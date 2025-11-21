package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/21/2025.
 * #2524 https://leetcode.com/problems/maximum-frequency-score-of-a-subarray/
 */
public class MaximumFrequencyScoreOfASubarray {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n), space O(n)
    public int maxFrequencyScore(int[] nums, int k) {
        int res = 0, n = nums.length;
        long score = 0;
        Map<Integer, Deque<Integer>> stacks = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (stacks.containsKey(nums[i])) {
                Deque<Integer> stack = stacks.get(nums[i]);
                long last = stack.peek(), curr = last * nums[i] % MOD;
                score += curr - last;
                stack.push((int) curr);
            } else {
                score += nums[i];
                stacks.computeIfAbsent(nums[i], key -> new ArrayDeque<>()).push(nums[i]);
            }
            if (i >= k - 1) {
                res = Math.max(res, (int) ((score % MOD + MOD) % MOD));
                Deque<Integer> stack = stacks.get(nums[i - k + 1]);
                score -= stack.pop();
                if (stack.isEmpty()) {
                    stacks.remove(nums[i - k + 1]);
                } else {
                    score += stack.peek();
                }
            }
        }
        return res;
    }
}
