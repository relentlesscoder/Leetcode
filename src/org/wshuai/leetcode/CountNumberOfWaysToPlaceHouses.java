package org.wshuai.leetcode;

/**
 * Created by Wei on 02/14/2026.
 * #2320 https://leetcode.cn/problems/count-number-of-ways-to-place-houses/
 */
public class CountNumberOfWaysToPlaceHouses {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n), space O(1)
    public int countHousePlacementsDP(int n) {
        // 优化空间复杂度到常数
        long s0 = 1L, s1 = 0L;
        for (int i = 1; i <= n; i++) {
            long c0 = (s0 + s1) % MOD, c1 = s0;
            s0 = c0;
            s1 = c1;
        }
        long res = (s0 + s1) % MOD;
        return (int) ((res * res) % MOD);
    }

    // time O(n), space O(n)
    public int countHousePlacementsDPWithArray(int n) {
        long[][] dp = new long[n + 1][2];
        dp[0][0] = 1L;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD; // 选择不盖房子
            dp[i][1] = dp[i - 1][0]; // 选择盖房子
        }
        long res = (dp[n][0] + dp[n][1]) % MOD; // 总方案为最后一个位置盖 + 不盖方案的和
        return (int) ((res * res) % MOD);
    }

    // time O(n), space O(n)
    public int countHousePlacementsDFSWithMemorization(int n) {
        // 街道两边放置的方式是独立的所以只需要计算一边
        Long[][] memo = new Long[n][2];
        long res = dfs(0, 0, n, memo);
        // 将两边结果相乘
        return (int) ((res * res) % MOD);
    }

    private long dfs(int i, int last, int n, Long[][] memo) {
        if (i == n) {
            return 1L;
        }
        if (memo[i][last] != null) {
            return memo[i][last];
        }
        // 总是可以选择不盖房子
        long res = dfs(i + 1, 0, n, memo);
        // 选择盖房子
        if (last == 0) {
            res = (res + dfs(i + 1, 1, n, memo)) % MOD;
        }
        return memo[i][last] = res;
    }
}
