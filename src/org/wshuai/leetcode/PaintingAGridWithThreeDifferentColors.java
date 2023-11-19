package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/18/2023.
 * #1931 https://leetcode.com/problems/painting-a-grid-with-three-different-colors/
 */
public class PaintingAGridWithThreeDifferentColors {

    private static final int MOD = 1_000_000_007;

    // time O(n * 3^m * 2^m), space O(4^m * 2^m)
    public int colorTheGrid(int m, int n) {
        // Since m <= 5, we can use bitmask to represent the column state.
        // We can use color mapping 1 = RED, 2 = GREEN, 3 = BLUE thus for each
        // color in a cell we need two bits in the bitmask then the total bits
        // for a column is 10 bits. Therefore, the column state space is 2^10 = 1024.
        Integer[][] dp = new Integer[1_000][1_024];
        // For each column state, we can cache all validate column states for the next
        // column.
        List<Integer>[] adj = new ArrayList[1_024];
        return paint(0, 0, n, m, dp, adj);
    }

    private int paint(int column, int prevMask, int n, int m, Integer[][] dp, List<Integer>[] adj) {
        if (column == n) {
            return 1;
        }
        if (dp[column][prevMask] != null) {
            return dp[column][prevMask];
        }
        int res = 0;
        if (adj[prevMask] == null) {
            adj[prevMask] = new ArrayList<>();
            dfs(prevMask, 0, m, m, adj[prevMask]);
        }
        for (int nextPaint : adj[prevMask]) {
            res = (res + paint(column + 1, nextPaint, n, m, dp, adj)) % MOD;
        }
        return dp[column][prevMask] = res;
    }

    private void dfs(int prevColumnPaintMask, int curr, int rows, int m, List<Integer> paints) {
        if (rows == 0) {
            paints.add(curr);
            return;
        }
        int leftColor = prevColumnPaintMask & 3, upperColor = rows == m ? 0 : curr & 3;
        for (int c = 1; c <= 3; c++) {
            if (c == leftColor || c == upperColor) { // color can't be the same as that in the left and upper cells
                continue;
            }
            dfs(prevColumnPaintMask >> 2, (curr << 2) | c, rows - 1, m, paints);
        }
    }
}
