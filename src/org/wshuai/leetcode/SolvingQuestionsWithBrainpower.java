package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/26/2023.
 * #2140 https://leetcode.com/problems/solving-questions-with-brainpower/
 */
public class SolvingQuestionsWithBrainpower {

    // time O(n), space O(n)
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int j = Math.min(i + questions[i][1] + 1, n);
            // 与 DFS 的解法类似，将 dp[i] 转化为子问题 dp[j] + questions[i][0] ，由于
            // 子问题有更大的索引所以我们要逆序遍历。
            dp[i] = Math.max(dp[i + 1], dp[j] + questions[i][0]);
        }
        return dp[0];
    }

    // time O(n), space O(n)
    public long mostPointsDFSWithMemorization(int[][] questions) {
        long res = 0;
        int n = questions.length;
        long[] memo = new long[n];
        Arrays.fill(memo, -1L);
        return dfs(0, questions, memo);
    }

    private long dfs(int i, int[][] questions, long[] memo) {
        if (i >= questions.length) {
            return 0L;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        // 选或者不选
        return memo[i] = Math.max(
                (long) questions[i][0] + dfs(i + questions[i][1] + 1, questions, memo),
                dfs(i + 1, questions, memo));
    }
}
