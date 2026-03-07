package org.wshuai.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/01/2025.
 * #2787 https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/
 */
public class WaysToExpressAnIntegerAsSumOfPowers {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n * log(n)), space O(n)
    public int numberOfWaysDPWithArray(int n, int x) {
        // 进一步优化空间
        List<Integer> arr = new ArrayList<>();
        int m = 1;
        for (int j = 0; m <= n && j <= n; m++) {
            j = pow(m, x);
            arr.add(j);
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i < m; i++) {
            dp[0] = 1;
            // 注意要从右往左计算，则 s 左边的值还是上一行的计算结果
            for (int s = n; s >= 1; s--) {
                if (arr.get(i - 1) <= s) {
                    dp[s] = (dp[s] + dp[s - arr.get(i - 1)]) % MOD;
                }
            }
        }
        return dp[n];
    }

    // time O(n * log(n)), space O(n)
    public int numberOfWaysDPWithRollingArray(int n, int x) {
        // 空间优化版 DP
        List<Integer> arr = new ArrayList<>();
        int m = 1;
        for (int j = 0; m <= n && j <= n; m++) {
            j = pow(m, x);
            arr.add(j);
        }
        int[] pre = new int[n + 1];
        pre[0] = 1;
        for (int i = 1; i < m; i++) {
            int[] dp = new int[n + 1];
            dp[0] = 1;
            for (int s = 1; s <= n; s++) {
                if (arr.get(i - 1) > s) {
                    dp[s] = pre[s];
                } else {
                    dp[s] = (pre[s] + pre[s - arr.get(i - 1)]) % MOD;
                }
            }
            pre = dp;
        }
        return pre[n];
    }

    // time O(n * log(n)), space O(n * log(n))
    public int numberOfWaysDPWithGrid(int n, int x) {
        // 把记忆化搜索翻译成 DP
        List<Integer> arr = new ArrayList<>();
        int m = 1;
        for (int j = 0; m <= n && j <= n; m++) {
            j = pow(m, x);
            arr.add(j);
        }
        int[][] dp = new int[m][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
            for (int s = 1; s <= n; s++) {
                if (arr.get(i - 1) > s) {
                    dp[i][s] = dp[i - 1][s];
                } else {
                    dp[i][s] = (dp[i - 1][s] + dp[i - 1][s - arr.get(i - 1)]) % MOD;
                }
            }
        }
        return dp[m - 1][n];
    }

    // time O(n * log(n)), space O(n * log(n))
    public int numberOfWaysDFSWithMemorization(int n, int x) {
        // 记忆化搜索 - 0/1 背包
        List<Integer> arr = new ArrayList<>();
        int m = 1;
        for (int j = 0; m <= n && j <= n; m++) {
            j = pow(m, x);
            arr.add(j);
        }
        int[][] memo = new int[m][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(m - 1, n, arr, memo);
    }

    private int dfs(int i, int s, List<Integer> arr, int[][] memo) {
        if (s == 0) {
            return 1;
        }
        if (s < 0 || i == 0) {
            return 0;
        }
        if (memo[i][s] != -1) {
            return memo[i][s];
        }
        // 不能选
        if (arr.get(i - 1) > s) {
            return memo[i][s] = dfs(i - 1, s, arr, memo);
        }
        // 可选可不选
        return memo[i][s] = (dfs(i - 1, s, arr, memo)
                + dfs(i - 1, s - arr.get(i - 1), arr, memo)) % MOD;
    }

    private static int pow(int x, int n) {
        int res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res *= x;
            }
            x *= x;
            n /= 2;
        }
        return res;
    }
}
