package org.wshuai.leetcode.backtracking;

/**
 * Created by Wei on 02/05/2026.
 * #2397 https://leetcode.com/problems/maximum-rows-covered-by-columns/
 */
public class MaximumRowsCoveredByColumns {

    private int res = 0;

    // time O(2^n + m * c(n, numSelect)), space O(n + m)
    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length, n = matrix[0].length;
        if (numSelect == n) {
            return m;
        }
        // 最大列数为 12 所以可以将每一行的状态压缩到一个整型中
        int[] rows = new int[m];
        for (int i = 0; i < m; i++) {
            int mask = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    mask |= (1 << j);
                }
            }
            rows[i] = mask;
        }
        dfs(0, 0, n, rows, numSelect);
        return res;
    }

    private void dfs(int i, int mask, int n, int[] rows, int target) {
        // 用一个整型 mask 代表所有被选择的列
        // 如果整型中 1 的位数达到目标
        if (Integer.bitCount(mask) == target) {
            int cnt = 0;
            // 对每一行判断当前的列的选择是否覆盖此行里所有的 1
            for (int r : rows) {
                cnt += (r & mask) == r ? 1 : 0;
            }
            res = Math.max(res, cnt);
            return;
        }
        if (i == n) {
            return;
        }
        // 选择当前的列 i
        dfs(i + 1, mask, n, rows, target);
        // 不选择当前的列 i
        dfs(i + 1, mask | (1 << i), n, rows, target);
    }
}
