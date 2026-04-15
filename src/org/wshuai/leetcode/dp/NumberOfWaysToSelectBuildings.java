package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 09/02/2023.
 * #2222 https://leetcode.com/problems/number-of-ways-to-select-buildings/
 */
public class NumberOfWaysToSelectBuildings {

    // time O(n), space O(n)
    public long numberOfWays(String s) {
        // 核心思路: 枚举中间建筑, 合法序列只有010和101两种
        // 中间为1时, 方案数 = 左边0的个数 * 右边0的个数
        // 中间为0时, 方案数 = 左边1的个数 * 右边1的个数
        long res = 0;
        int n = s.length();
        char[] sc = s.toCharArray();
        // leftZeros[i]: 位置i左边(不含i)的0的个数
        int[] leftZeros = new int[n];
        for (int i = 0, cnt = 0; i < n; i++) {
            leftZeros[i] = cnt;
            cnt += sc[i] == '0' ? 1 : 0;
        }
        // cnt: 位置i右边(不含i)的0的个数, 从右往左累计
        for (int i = n - 1, cnt = 0; i >= 0; i--) {
            if (sc[i] == '1') {
                // 中间为1, 构成010: 左边0个数 * 右边0个数
                res += (long) leftZeros[i] * cnt;
            } else {
                // 中间为0, 构成101: 左边1个数 * 右边1个数
                // 左边1个数 = i - leftZeros[i], 右边1个数 = (n-1-i) - cnt
                res += (long) (i - leftZeros[i]) * (n - 1 - i - cnt);
            }
            cnt += sc[i] == '0' ? 1 : 0;
        }
        return res;
    }

    // time O(n), space O(1)
    public long numberOfWaysDP(String s) {
        // 核心思路: 选3个建筑使相邻类型不同(010或101), 统计方案数
        // dp[j][k]: 已选j个建筑, 最后一个类型为k的方案数. 压缩掉天数维度直接累加
        // 关键点: 只有sc[i] != k时才更新dp[j+1][k], 读取的是dp[j][1^k]
        // 而1^k恰好等于sc[i](因为k != sc[i]), 即读的是dp[j][sc[i]]
        // 上一轮j只更新了dp[j][k != sc[i]], 所以dp[j][sc[i]]未被动过, 不会冲突
        int n = s.length();
        char[] sc = s.toCharArray();
        // dp[j][k]: 已选j个建筑, 最后一个类型为k的方案数
        long[][] dp = new long[4][2];
        dp[0][0] = dp[0][1] = 1; // 选0个建筑, 哨兵值1
        for (int i = 0; i < n; i++) {
            dp[0][0] = dp[0][1] = 1;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    if (sc[i] - '0' != k) {
                        // 当前建筑类型sc[i] != k, 可以在"最后为1^k"的序列后追加sc[i], 结果最后为k
                        dp[j + 1][k] += dp[j][1 ^ k];
                    }
                }
            }
        }
        // 选3个建筑, 最后为0或1的方案数之和
        return dp[3][0] + dp[3][1];
    }

    // time O(n), space O(n)
    public long numberOfWaysDPWithGrid(String s) {
        // 核心思路: 与压缩版相同, 但保留完整三维数组
        // dp[i][j][k]: 前i个建筑中选了j个, 最后一个类型为k的方案数
        int n = s.length();
        char[] sc = s.toCharArray();
        long[][][] dp = new long[n + 1][4][2];
        dp[0][0][0] = dp[0][0][1] = 1; // 选0个, 哨兵值1
        for (int i = 0; i < n; i++) {
            dp[i + 1][0][0] = dp[i + 1][0][1] = 1;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    if (sc[i] - '0' == k) {
                        // 当前建筑类型 == k, 无法追加(相邻不能同类型), 直接继承
                        dp[i + 1][j + 1][k] = dp[i][j + 1][k];
                    } else {
                        // 当前建筑类型 != k, 可以追加: 继承 + 在"最后为1^k"的序列后追加
                        dp[i + 1][j + 1][k] = dp[i][j + 1][k] + dp[i][j][1 ^ k];
                    }
                }
            }
        }
        return dp[n][3][0] + dp[n][3][1];
    }

    // time O(n), space O(n)
    public long numberOfWaysDFSWithMemorization(String s) {
        // 核心思路: 记忆化DFS, 从右往左选建筑, 要求相邻选中的建筑类型不同
        int n = s.length();
        char[] sc = s.toCharArray();
        // memo[i][j][k]: 前i+1个建筑中还需选j+1个, 以类型k结尾的方案数
        long[][][] memo = new long[n][3][2];
        for (long[][] matrix : memo) {
            for (long[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }
        // 最后一个选0或选1的方案数之和
        return dfs(n - 1, 2, 0, sc, memo) + dfs(n - 1, 2, 1, sc, memo);
    }

    // i: 当前建筑索引, j: 还需选j+1个建筑, k: 当前要选的类型
    private long dfs(int i, int j, int k, char[] s, long[][][] memo) {
        if (j == -1) {
            // 已选够3个, 合法方案
            return 1L;
        }
        if (i == -1) {
            // 建筑用完但没选够, 不合法
            return 0L;
        }
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        if (s[i] - '0' == k) {
            // 当前建筑类型 == k, 无法追加(相邻不能同类型), 跳过
            return memo[i][j][k] = dfs(i - 1, j, k, s, memo);
        }
        // 当前建筑类型 != k: 跳过 或 选中(下一个要选1^k, 剩余数-1)
        return memo[i][j][k] = dfs(i - 1, j, k, s, memo) + dfs(i - 1, j - 1, 1 ^ k, s, memo);
    }
}
