package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/13/2026.
 * #2266 https://leetcode.com/problems/count-number-of-texts/
 */
public class CountNumberOfTexts {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n), space O(1)
    public int countTextsDP(String pressedKeys) {
        // 进一步优化使空间复杂度降为常数
        int n = pressedKeys.length();
        char[] digits = pressedKeys.toCharArray();
        int c0 = 1, c1 = 1, c2 = 1, c3 = 1;
        for (int i = 4; i < n + 4; i++) {
            int c = c3;
            if (i > 4 && digits[i - 5] == digits[i - 4]) {
                c = (c + c2) % MOD;
            }
            if (i > 5 &&
                    digits[i - 5] == digits[i - 4]
                    && digits[i - 6] == digits[i - 4]) {
                c = (c + c1) % MOD;
            }
            if ((digits[i - 4] == '7' || digits[i - 4] == '9')
                    && (i > 6 &&
                    digits[i - 5] == digits[i - 4]
                    && digits[i - 6] == digits[i - 4]
                    && digits[i - 7] == digits[i - 4])) {
                c = (c + c0) % MOD;
            }
            c0 = c1;
            c1 = c2;
            c2 = c3;
            c3 = c;
        }
        return c3;
    }

    // time O(n), space O(n)
    public int countTextsDPWithArray(String pressedKeys) {
        int n = pressedKeys.length();
        char[] digits = pressedKeys.toCharArray();
        // 将 dp 的长度设为 n + 4 便于计算
        int[] dp = new int[n + 4];
        dp[0] = dp[1] = dp[2] = dp[3] = 1;
        for (int i = 4; i < n + 4; i++) {
            dp[i] = dp[i - 1]; // 第一个字母
            // 第二个字母
            if (i > 4 && digits[i - 5] == digits[i - 4]) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
            // 第三个字母
            if (i > 5 && digits[i - 5] == digits[i - 4]
                    && digits[i - 6] == digits[i - 4]) {
                dp[i] = (dp[i] + dp[i - 3]) % MOD;
            }
            // 第四个字母
            if ((digits[i - 4] == '7' || digits[i - 4] == '9')
                    && (i > 6 && digits[i - 5] == digits[i - 4]
                    && digits[i - 6] == digits[i - 4]
                    && digits[i - 7] == digits[i - 4])) {
                dp[i] = (dp[i] + dp[i - 4]) % MOD;
            }
        }
        return dp[n + 3];
    }

    // time O(n), space O(n)
    public int countTextsDFSWithMemorization(String pressedKeys) {
        int n = pressedKeys.length();
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(0, pressedKeys.toCharArray(), memo);
    }

    private int dfs(int i, char[] digits, int[] memo) {
        if (i == digits.length) {
            return 1;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int res = 0, n = digits.length;
        // 按键的第一个字母需要按一次
        res = (res + dfs(i + 1, digits, memo)) % MOD;
        // 按键的第二个字母需要按两次
        if (i < n - 1 && digits[i + 1] == digits[i]) {
            res = (res + dfs(i + 2, digits, memo)) % MOD;
        }
        // 按键的第三个字母需要按三次
        if (i < n - 2 && digits[i + 1] == digits[i] && digits[i + 2] == digits[i]) {
            res = (res + dfs(i + 3, digits, memo)) % MOD;
        }
        // 7 和 9 有第四个字母需要按四次
        if ((digits[i] == '7' || digits[i] == '9')
                && (i < n - 3
                && digits[i + 1] == digits[i]
                && digits[i + 2] == digits[i]
                && digits[i + 3] == digits[i])) {
            res = (res + dfs(i + 4, digits, memo)) % MOD;
        }
        return memo[i] = res % MOD;
    }
}
