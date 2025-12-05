package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2025.
 * #3566 https://leetcode.com/problems/partition-array-into-two-equal-product-subsets/
 */
public class PartitionArrayIntoTwoEqualProductSubsets {

    // time O(2^n), space O(1)
    public boolean checkEqualPartitionsBitMask(int[] nums, long target) {
        int n = nums.length, mask = (1 << n) - 1;
        for (int partition = 1; partition < mask; partition++) {
            long p1 = 1L, p2 = 1L;
            for (int bit = 0; bit < n && p1 <= target && p2 <= target; bit++) {
                if (((1 << bit) & partition) > 0) {
                    p1 *= nums[bit];
                } else {
                    p2 *= nums[bit];
                }
            }
            if (p1 == target && p1 == p2) {
                return true;
            }
        }
        return false;
    }

    // time O(2^n), space O(n)
    public boolean checkEqualPartitions(int[] nums, long target) {
        return dfs(1L, 1L, 0, nums, target);
    }

    private boolean dfs(long p1, long p2, int i, int[] nums, long target) {
        if (i == nums.length) {
            return p1 == target && p2 == target;
        }
        if (p1 > target || p2 > target) {
            return false;
        }
        return dfs(p1 * nums[i], p2, i + 1, nums, target)
                || dfs(p1, p2 * nums[i], i + 1, nums, target);
    }
}
