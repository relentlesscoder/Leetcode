package org.wshuai.leetcode;

/**
 * Created by Wei on 11/17/2023.
 * #1692 https://leetcode.com/problems/count-ways-to-distribute-candies/
 */
public class CountWaysToDistributeCandies {

    private static final long MOD = 1_000_000_007;

    // time O(n * k), space O(n * k)
    public int waysToDistributeBottomUp(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = ((dp[i - 1][j] * j) % MOD + dp[i - 1][j - 1]) % MOD;
            }
        }
        return (int) dp[n][k];
    }

    // time O(n * k), space O(n * k)
    public int waysToDistributeTopDown(int n, int k) {
        return (int) distribute(n, k, new Long[n + 1][k + 1]);
    }

    private long distribute(int candies, int bags, Long[][] dp) {
        if (candies == 0) {
            return 0;
        }
        if (bags == 1) {
            return 1;
        }
        if (dp[candies][bags] != null) {
            return dp[candies][bags];
        }
        dp[candies][bags] = ((distribute(candies - 1, bags, dp) * bags) % MOD
                + distribute(candies - 1, bags - 1, dp)) % MOD;
        return dp[candies][bags];
    }
}
