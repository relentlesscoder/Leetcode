package org.wshuai.leetcode;

/**
 * Created by Wei on 01/16/2021.
 * #1695 https://leetcode.com/problems/maximum-erasure-value/
 */
public class MaximumErasureValue {

    // time O(n), space O(MAX)
    public int maximumUniqueSubarray(int[] nums) {
        int res = 0, n = nums.length, score = 0;
        int[] freq = new int[10_001];
        for (int i = 0, j = 0; i < n; i++) {
            freq[nums[i]]++;
            score += nums[i];
            while (freq[nums[i]] > 1) {
                score -= nums[j];
                --freq[nums[j++]];
            }
            res = Math.max(res, score);
        }
        return res;
    }
}
