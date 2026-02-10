package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/02/2025.
 * #2466 https://leetcode.com/problems/count-ways-to-build-good-strings/
 */
public class CountWaysToBuildGoodStrings {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n), space O(n)
    public int countGoodStringsDP(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];
        dp[0] = 1;
        for (int i = 1; i <= high; i++) {
            if (i >= zero) {
                dp[i] = (dp[i] + dp[i - zero]) % MOD;
            }
            if (i >= one) {
                dp[i] = (dp[i] + dp[i - one]) % MOD;
            }
        }
        int res = 0;
        for (int i = low; i <= high; i++) {
            res = (res + dp[i]) % MOD;
        }
        return res;
    }

    // time O(h), space O(h)
    public int countGoodStringsDFSWithMemorization(int low, int high, int zero, int one) {
        int res = 0;
        int[] memo = new int[high + 1];
        Arrays.fill(memo, -1);
        for (int i = low; i <= high; i++) {
            res = (res + dfs(i, zero, one, memo)) % MOD;
        }
        return res;
    }

    private int dfs(int cnt, int zero, int one, int[] memo) {
        if (cnt < 0) {
            return 0;
        }
        if (cnt == 0) {
            return 1;
        }
        if (memo[cnt] != -1) {
            return memo[cnt];
        }
        return memo[cnt] = (dfs(cnt - zero, zero, one, memo)
                + dfs(cnt - one, zero, one, memo)) % MOD;
    }
}
