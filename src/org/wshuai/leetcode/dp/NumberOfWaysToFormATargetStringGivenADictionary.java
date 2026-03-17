package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 03/02/2021.
 * #1639
 * https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/
 */
public class NumberOfWaysToFormATargetStringGivenADictionary {

    private static final int MOD = (int) 1e9 + 7;

    // time O(m * n + l * MAX), space O(m + n)
    public int numWays(String[] words, String target) {
        // 空间优化版 DP
        int l = words.length, n = target.length(), m = 0;
        for (int i = 0; i < l; i++) {
            m = Math.max(m, words[i].length());
        }
        int[][] freq = new int[m][26];
        for (String w : words) {
            for (int i = 0; i < w.length(); i++) {
                freq[i][w.charAt(i) - 'a']++;
            }
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            int pre = dp[0];
            dp[0] = 1;
            for (int j = 0; j < n; j++) {
                long res = dp[j + 1];
                int idx = target.charAt(j) - 'a';
                if (freq[i][idx] > 0) {
                    res = (res + (long) freq[i][idx] * pre % MOD) % MOD;
                }
                int x = dp[j + 1];
                dp[j + 1] = (int) res;
                pre = x;
            }
        }
        return dp[n];
    }

    // time O(m * n + l * MAX), space O(m * n)
    public int numWaysDPWithGrid(String[] words, String target) {
        // 把记忆化搜索翻译成 DP
        int l = words.length, n = target.length(), m = 0;
        for (int i = 0; i < l; i++) {
            m = Math.max(m, words[i].length());
        }
        int[][] freq = new int[m][26];
        for (String w : words) {
            for (int i = 0; i < w.length(); i++) {
                freq[i][w.charAt(i) - 'a']++;
            }
        }
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            dp[i + 1][0] = 1;
            for (int j = 0; j < n; j++) {
                long res = dp[i][j + 1];
                int idx = target.charAt(j) - 'a';
                if (freq[i][idx] > 0) {
                    res = (res + (long) freq[i][idx] * dp[i][j] % MOD) % MOD;
                }
                dp[i + 1][j + 1] = (int) res;
            }
        }
        return dp[m][n];
    }

    // time O(m * n + l * MAX), space O(m * n)
    public int numWaysDFSWithMemorization(String[] words, String target) {
        int l = words.length, n = target.length(), m = 0;
        // 统计 words 中字符串的最大长度
        for (int i = 0; i < l; i++) {
            m = Math.max(m, words[i].length());
        }
        // 统计 words 中每个位置上字符出现的频次
        int[][] freq = new int[m][26];
        for (String w : words) {
            for (int i = 0; i < w.length(); i++) {
                freq[i][w.charAt(i) - 'a']++;
            }
        }
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(m - 1, n - 1, freq, target.toCharArray(), memo);
    }

    private int dfs(int i, int j, int[][] freq, char[] t, int[][] memo) {
        // target 匹配完了，说明我们找到了一种匹配方案，返回 1
        if (j == -1) {
            return 1;
        }
        // freq 被匹配完了但 target 还没有匹配完，说明当前的匹配方案不合法，返回 0
        if (i == -1) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 总是可以选择不用 freq 中的 i 位置的字符来匹配
        long res = dfs(i - 1, j, freq, t, memo);
        // 如果 freq 中的 i 位置有 t[j] 这个字符，则也可以选择用 freq 中的 i 位置的 t[j] 来匹配
        // 注意这里要乘以 freq[i][t[j] - 'a']，因为 freq 中的 i 位置可能有多个 t[j] 这个字符，
        // 我们可以选择其中的任意一个来匹配
        if (freq[i][t[j] - 'a'] > 0) {
            res = (res + (long) freq[i][t[j] - 'a'] * dfs(i - 1, j - 1, freq, t, memo) % MOD) % MOD;
        }
        return memo[i][j] = (int) res;
    }
}
