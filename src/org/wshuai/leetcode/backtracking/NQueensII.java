package org.wshuai.leetcode.backtracking;

/**
 * Created by Wei on 11/09/2016.
 * #0052 https://leetcode.com/problems/n-queens-ii/
 */
public class NQueensII {
    private int res = 0;

    // time O(n * n!), space O(n)
    public int totalNQueens(int n) {
        res = 0;
        int cols = 0, // 列是否被占
                diff = 0, // 反对角线是否被占
                sum = 0; // 对角线是否被占
        dfs(0, n, cols, diff, sum);
        return res;
    }

    private void dfs(int x, int n, int cols, int diff, int sum) {
        if (x == n) {
            res++;
            return;
        }
        for (int y = 0; y < n; y++) {
            if (((1 << y) & cols) == 0 && (1 << (x - y + n) & diff) == 0 && (1 << (x + y) & sum) == 0) {
                cols |= (1 << y);
                diff |= 1 << (x - y + n);
                sum |= 1 << (x + y);
                dfs(x + 1, n, cols, diff, sum);
                cols ^= (1 << y);
                diff ^= 1 << (x - y + n);
                sum ^= 1 << (x + y);
            }
        }
    }
}
