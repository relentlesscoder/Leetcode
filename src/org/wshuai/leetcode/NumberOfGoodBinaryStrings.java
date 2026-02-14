package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/13/2026.
 * #2533 https://leetcode.com/problems/number-of-good-binary-strings/
 */
public class NumberOfGoodBinaryStrings {

    private static final int MOD = (int) 1e9 + 7;

    // time O(L), space O(L)
    public int goodBinaryStrings(int minLength, int maxLength, int oneGroup, int zeroGroup) {
        int res = 0;
        int[] dp = new int[maxLength + 1];
        dp[0] = 1;
        for (int i = 1; i <= maxLength; i++) {
            // 当前字符串尾部为一组 1
            if (i >= oneGroup) {
                dp[i] = (dp[i] + dp[i - oneGroup]) % MOD;
            }
            // 当前字符串尾部为一组 0
            if (i >= zeroGroup) {
                dp[i] = (dp[i] + dp[i - zeroGroup]) % MOD;
            }
            // 当前字符串符合要求则加入答案
            if (i >= minLength) {
                res = (res + dp[i]) % MOD;
            }
        }
        return res;
    }

    // time O(L), space O(L)
    public int goodBinaryStringsDFSWithMemorization(
            int minLength, int maxLength, int oneGroup, int zeroGroup) {
        int[] memo = new int[maxLength + 1];
        Arrays.fill(memo, -1);
        return dfs(0, minLength, maxLength, oneGroup, zeroGroup, memo);
    }

    private int dfs(int i, int min, int max, int one, int zero, int[] memo) {
        if (i > max) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int res = 0;
        if (i >= min) { // 当前字符串符合要求
            res++;
        }
        // 在当前字符串尾部加一组 1
        res = (res + dfs(i + one, min, max, one, zero, memo)) % MOD;
        // 在当前字符串尾部加一组 0
        res = (res + dfs(i + zero, min, max, one, zero, memo)) % MOD;
        return memo[i] = res;
    }
}
