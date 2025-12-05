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

    public int maxFrequencyScore(int[] nums, int k) {
        int n = nums.length;
        long res = 0, score = 0;
        Map<Integer, Deque<Integer>> stacks = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (stacks.containsKey(nums[i])) {
                Deque<Integer> st = stacks.get(nums[i]);
                long prod = (long) st.peek() * nums[i] % MOD;
                score = score + prod - st.peek();
                st.push((int) prod);
            } else {
                stacks.computeIfAbsent(nums[i], x -> new ArrayDeque<>()).push(nums[i]);
                score = score + nums[i];
            }
            int left = i - k + 1;
            if (left < 0) {
                continue;
            }
            res = Math.max(res, (score % MOD + MOD) % MOD);
            Deque<Integer> st = stacks.get(nums[left]);
            score = score - st.pop() + (st.isEmpty() ? 0 : st.peek());
            if (st.isEmpty()) {
                stacks.remove(nums[left]);
            }
        }
        return (int) res;
    }
}
