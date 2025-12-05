package org.wshuai.leetcode;

/**
 * Created by Wei on 07/29/2025.
 * #2044 https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/
 */
public class CountNumberOfMaximumBitwiseORSubsets {

    // time O(n * max), space O(max)
    public int countMaxOrSubsets(int[] nums) {
        int max = 0;
        int[] dp = new int[1 << 17];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = max; i >= 0; i--) {
                dp[i | num] += dp[i];
            }
            max |= num;
        }
        return dp[max];
    }

    // time O(n * 2^n), space O(1)
    public int countMaxOrSubsetsBitManipulation(int[] nums) {
        int res = 0, max = 0, n = nums.length, mask = (1 << n);
        for (int num : nums) {
            max |= num;
        }
        for (int i = 0; i < mask; i++) {
            int val = 0;
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) > 0) {
                    val |= nums[j];
                }
            }
            res += val == max ? 1 : 0;
        }
        return res;
    }

    // time O(n * max), space O(n * max)
    public int countMaxOrSubsetsMemorization(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max |= num;
        }
        Integer[][] memo = new Integer[nums.length][max + 1];
        return count(nums, 0, 0, max, memo);
    }

    private int count(int[] nums, int index, int val, int max, Integer[][] memo) {
        if (index == nums.length) {
            return val == max ? 1 : 0;
        }
        if (memo[index][val] != null) {
            return memo[index][val];
        }
        int res = count(nums, index + 1, val, max, memo)
                + count(nums, index + 1, val | nums[index], max, memo);
        return memo[index][val] = res;
    }

    // time O(2^n), space O(n)
    public int countMaxOrSubsetsRecursion(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max |= num;
        }
        return count(nums, 0, 0, max);
    }

    private int count(int[] nums, int index, int val, int max) {
        if (index == nums.length) {
            return val == max ? 1 : 0;
        }
        return count(nums, index + 1, val, max)
                + count(nums, index + 1, val | nums[index], max);
    }
}
