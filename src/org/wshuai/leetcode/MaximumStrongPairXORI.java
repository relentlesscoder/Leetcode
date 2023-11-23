package org.wshuai.leetcode;

/**
 * Created by Wei on 11/22/2023.
 * #2932 https://leetcode.com/problems/maximum-strong-pair-xor-i/
 */
public class MaximumStrongPairXORI {

    // time O(n^2), space O(1)
    public int maximumStrongPairXor(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) <= Math.min(nums[i], nums[j])) {
                    res = Math.max(res, nums[i] ^ nums[j]);
                }
            }
        }
        return res;
    }
}
