package org.wshuai.leetcode.dp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 02/07/2026.
 * #3376 https://leetcode.com/problems/minimum-time-to-break-locks-i/
 */
public class MinimumTimeToBreakLocksI {

    // time O(n * 2^n), space O(2^n)
    public int findMinimumTimeDP(List<Integer> strength, int k) {
        int n = strength.size();
        int[] memo = new int[1 << n];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        return dfs((1 << n) - 1, strength, k, n, memo);
    }

    private int dfs(int i, List<Integer> strength, int k, int n, int[] memo) {
        if (memo[i] != -1) {
            return memo[i];
        }
        int x = 1 + k * (n - Integer.bitCount(i)); // 利用当前状态的 1 的位数计算 x 值
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) { // 每次对当前的状态减少一把打开的锁直到状态为 0
            if (((i >> j) & 1) > 0) { // 判断当前的锁是否已经打开
                // 当前的状态所需的能量 = min(少开一把锁的总能量 + 开这把锁的能量)
                res = Math.min(res, dfs(i ^ (1 << j), strength, k, n, memo)
                        + (strength.get(j) + x - 1) / x);
            }
        }
        return memo[i] = res;
    }

    private int res = Integer.MAX_VALUE;

    // time O(n * n!), space O(n)
    public int findMinimumTimeBacktracking(List<Integer> strength, int k) {
        Collections.sort(strength); // 排序让更大的 k 值作用于更强的锁
        dfs(0, 0, 1, strength, k, new boolean[strength.size()]);
        return res;
    }

    private void dfs(int count, int time, int x, List<Integer> strength, int k, boolean[] unlocked) {
        if (time >= res) { // 如果时间已经大于当前最小值则提前结束
            return;
        }
        if (count == strength.size()) {
            res = Math.min(res, time);
            return;
        }
        for (int i = 0; i < strength.size(); i++) {
            if (!unlocked[i]) {
                unlocked[i] = true;
                dfs(count + 1, time + (strength.get(i) + x - 1) / x, x + k,
                        strength, k, unlocked);
                unlocked[i] = false;
            }
        }
    }
}
