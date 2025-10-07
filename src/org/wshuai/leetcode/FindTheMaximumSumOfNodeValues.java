package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3068 https://leetcode.com/problems/find-the-maximum-sum-of-node-values/
 */
public class FindTheMaximumSumOfNodeValues {

    // time O(n), space O(1)
    public long maximumValueSumOptimizedDP(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        long even = 0, odd = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            long prevOdd = odd;
            odd = Math.max(prevOdd + nums[i], even + (nums[i] ^ k));
            even = Math.max(even + nums[i], prevOdd + (nums[i] ^ k));
        }
        return even;
    }

    // time O(n), space O(n)
    public long maximumValueSum2DDP(int[] nums, int k, int[][] edges) {
        // https://leetcode.cn/problems/find-the-maximum-sum-of-node-values/solutions/2664309/liang-chong-fang-fa-shu-xing-dp-xian-xin-lh6b/
        int n = nums.length;
        long[][] dp = new long[n + 1][2];
        dp[0][0] = 0;
        dp[0][1] = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = Math.max(dp[i][0] + nums[i], dp[i][1] + (nums[i] ^ k));
            dp[i + 1][1] = Math.max(dp[i][1] + nums[i], dp[i][0] + (nums[i] ^ k));
        }
        return dp[n][0];
    }
}
