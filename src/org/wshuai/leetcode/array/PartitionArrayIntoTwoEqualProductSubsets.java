package org.wshuai.leetcode.array;

/**
 * Created by Wei on 09/27/2025.
 * #3566 https://leetcode.com/problems/partition-array-into-two-equal-product-subsets/
 */
public class PartitionArrayIntoTwoEqualProductSubsets {

    // time O(2^n), space O(1)
    public boolean checkEqualPartitionsBitMask(int[] nums, long target) {
        // #0078
        int n = nums.length, m = 1 << n;
        for (int i = 0; i < m; i++) {
            long prod1 = 1L, prod2 = 1L;
            for (int j = 0; j < n && prod1 <= target && prod2 <= target; j++) {
                if (((1 << j) & i) > 0) {
                    prod1 *= nums[j];
                } else {
                    prod2 *= nums[j];
                }
            }
            if (prod1 == target && prod2 == target) {
                return true;
            }
        }
        return false;
    }

    // time O(2^n), space O(n)
    public boolean checkEqualPartitionsBacktracking(int[] nums, long target) {
        // #0078
        return dfs(0, 1L, 1L, nums, target);
    }

    private boolean dfs(int i, long prod1, long prod2, int[] nums, long target) {
        // 找到符合要求的划分方案
        if (i == nums.length) {
            return prod1 == target && prod2 == target;
        }
        // 提前结束
        if (prod1 > target || prod2 > target) {
            return false;
        }
        // 将当前数字加入第一部分或者第二部分
        return dfs(i + 1, prod1 * nums[i], prod2, nums, target)
                || dfs(i + 1, prod1, prod2 * nums[i], nums, target);
    }
}
