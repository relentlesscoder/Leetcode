package org.wshuai.leetcode;

/**
 * Created by Wei on 07/29/2025.
 * #2044 https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/
 */
public class CountNumberOfMaximumBitwiseORSubsets {

    // time O(n * max), space O(max)
    public int countMaxOrSubsetsDP(int[] nums) {
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

    private int res = 0;

    // time O(2^n), space O(n)
    public int countMaxOrSubsetsBacktracking1(int[] nums) {
        res = 0;
        int max = 0; // 计算数组最大的或值
        for (int x : nums) {
            max |= x;
        }
        dfs1(0, 0, nums, max);
        return res;
    }

    private void dfs1(int i, int sum, int[] nums, int max) {
        if (sum == max) { // 优化: 如果当前的或值已达到最大值则后续无论怎么选都等于这个值，选法一共有 2^(n - i) 种。
            res += 1 << (nums.length - i);
            return;
        }
        if (i == nums.length) {
            return;
        }
        dfs1(i + 1, sum, nums, max); // 不选
        dfs1(i + 1, sum | nums[i], nums, max); // 选
    }

    private int max = 0;

    // time O(2^n), space O(n)
    public int countMaxOrSubsetsBacktracking2(int[] nums) {
        // 回溯的另一种写法
        res = 0;
        int max = 0;
        for (int x : nums) {
            max |= x;
        }
        dfs2(0, 0, nums, max);
        return res;
    }

    private void dfs2(int i, int sum, int[] nums, int max) {
        if (sum == max) {
            res += 1 << (nums.length - i);
            return;
        }
        for (int j = i; j < nums.length; j++) {
            dfs2(j + 1, sum | nums[j], nums, max);
        }
    }

    // time O(n * 2^n), space O(1)
    public int countMaxOrSubsetsBitMask(int[] nums) {
        int res = 0, max = 0, n = nums.length, m = 1 << n;
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) > 0) {
                    sum |= nums[j];
                }
            }
            if (sum >= max) {
                res = sum == max ? res + 1 : 1;
                max = sum;
            }
        }
        return res;
    }
}
