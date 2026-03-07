package org.wshuai.leetcode.dp;

import java.util.List;

/**
 * Created by Wei on 12/30/2019.
 * #1301 https://leetcode.com/problems/number-of-paths-with-max-score/
 */
public class NumberOfPathsWithMaxScore {

    private static final int MOD = (int) 1e9 + 7;
    private static final int MIN = Integer.MIN_VALUE / 2;
    private static final int[][] DIRS = new int[][]{{-1, 0}, {0, -1}, {-1, -1}};

    // time O(n ^ 2), space O(n)
    public int[] pathsWithMaxScoreDP(List<String> board) {
        // 空间优化版 DP
        int n = board.size();
        int[][] pre = new int[n][2];
        for (int i = 0; i < n; i++) {
            int[][] dp = new int[n][2];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[j][0] = 0;
                    dp[j][1] = 1;
                    continue;
                }
                char c = board.get(i).charAt(j);
                if (c == 'X') {
                    dp[j][0] = Integer.MIN_VALUE;
                    dp[j][1] = 0;
                    continue;
                }
                int val = (i == n - 1 && j == n - 1) ? 0 : (c - '0');
                int max = Integer.MIN_VALUE, count = 0;
                for (int d = 0; d < 3; d++) {
                    int x = i + DIRS[d][0], y = j + DIRS[d][1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        int[] arr = x == i ? dp[y] : pre[y];
                        if (arr[0] > max) {
                            max = arr[0];
                            count = arr[1];
                        } else if (arr[0] == max) {
                            count = (count + arr[1]) % MOD;
                        }
                    }
                }
                dp[j][0] = (max == Integer.MIN_VALUE) ? Integer.MIN_VALUE : (max + val);
                dp[j][1] = count;
            }
            pre = dp;
        }
        return pre[n - 1][0] == Integer.MIN_VALUE ? new int[]{0, 0} : pre[n - 1];
    }

    // time O(n^2), space O(n^2)
    public int[] pathsWithMaxScoreDPWithGrid(List<String> board) {
        // 将记忆化搜索翻译成 DP
        int n = board.size();
        int[][][] dp = new int[n][n][2];
        dp[0][0][0] = 0;
        dp[0][0][1] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                char c = board.get(i).charAt(j);
                if (c == 'X') {
                    dp[i][j][0] = Integer.MIN_VALUE;
                    dp[i][j][1] = 0;
                    continue;
                }
                int val = (i == n - 1 && j == n - 1) ? 0 : (c - '0');
                int max = Integer.MIN_VALUE, count = 0;
                for (int d = 0; d < 3; d++) {
                    int x = i + DIRS[d][0], y = j + DIRS[d][1];
                    if (x >= 0 && x < n && y >= 0 && y < n && dp[x][y][0] > max) {
                        max = dp[x][y][0];
                        count = dp[x][y][1];
                    } else if (x >= 0 && x < n && y >= 0 && y < n && dp[x][y][0] == max) {
                        count = (count + dp[x][y][1]) % MOD;
                    }
                }
                dp[i][j][0] = (max == Integer.MIN_VALUE) ? Integer.MIN_VALUE : (max + val);
                dp[i][j][1] = count;
            }
        }
        return dp[n - 1][n - 1][0] == Integer.MIN_VALUE ? new int[]{0, 0} : dp[n - 1][n - 1];
    }

    // time O(n^2), space O(n^2)
    public int[] pathsWithMaxScoreDFSWithMemorization(List<String> board) {
        // 记忆化搜索
        int n = board.size();
        // 每个格子存: 0 - 最大值 1 - 出现次数
        int[][][] memo = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = Integer.MIN_VALUE;
            }
        }
        int[] res = dfs(n - 1, n - 1, board, memo);
        return res[0] == MIN ? new int[]{0, 0} : res;
    }

    private int[] dfs(int i, int j, List<String> board, int[][][] memo) {
        int n = board.size();
        if (i == 0 && j == 0) {
            return new int[]{0, 1};
        }
        char c = board.get(i).charAt(j);
        if (c == 'X') { // 有障碍物不能通过
            return new int[]{MIN, 0};
        }
        if (memo[i][j][0] != Integer.MIN_VALUE) {
            return memo[i][j];
        }
        int max = MIN, count = 0;
        int val = (i == n - 1 && j == n - 1) ? 0 : (c - '0');
        for (int d = 0; d < 3; d++) { // 递归左，上和左上的方格
            int x = i + DIRS[d][0], y = j + DIRS[d][1];
            if (x >= 0 && x < n && y >= 0 && y < n) {
                int[] ans = dfs(x, y, board, memo);
                // 找最大值
                if (ans[0] > max) {
                    max = ans[0];
                    count = ans[1];
                } else if (ans[0] == max) {
                    count = (count + ans[1]) % MOD;
                }
            }
        }
        max = (max == MIN) ? MIN : max + val;
        count = (max == MIN) ? 0 : count;
        return memo[i][j] = new int[]{max, count};
    }
}
