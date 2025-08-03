package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/02/2025.
 * #2466 https://leetcode.com/problems/count-ways-to-build-good-strings/
 */
public class CountWaysToBuildGoodStrings {

    private static final int MOD = (int)1e9 + 7;

    // time O(n), space O(n)
    public int countGoodStringsBottomUp(int low, int high, int zero, int one) {
        int res = 0;
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
        for (int i = low; i <= high; i++) {
            res = (res + dp[i]) % MOD;
        }
        return res;
    }

    // time O(n), space O(n)
    public int countGoodStringsTopDown(int low, int high, int zero, int one) {
        int[] memo = new int[high + 1];
        Arrays.fill(memo, -1);
        return count(0, low, high, zero, one, memo);
    }

    private int count(int len, int low, int high, int zero, int one, int[] memo) {
        int res = 0;
        if (len > high) {
            return 0;
        }
        if (memo[len] != -1) {
            return memo[len];
        }
        if (len >= low) {
            res++;
        }
        res = (res + count(len + zero, low, high, zero, one, memo) % MOD) % MOD;
        res = (res + count(len + one, low, high, zero, one, memo) % MOD) % MOD;
        return memo[len] = res;
    }
}
