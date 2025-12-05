package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/03/2019.
 * #1277 https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 */
public class CountSquareSubmatricesWithAllOnes {

    // time O(m * n), space O(n)
    public int countSquaresMonotonicStack(int[][] matrix) {
        // Use idea from #0084 to count squares with all ones
        // for each row as the bottom
        int res = 0, m = matrix.length, n = matrix[0].length;
        int[] row = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[j] = 0;
                } else {
                    row[j]++;
                }
            }
            res += countSquaresOnePass(row);
        }
        return res;
    }

    private int countSquaresOnePass(int[] heights) {
        int res = 0, n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int r = 0; r <= n; r++) {
            int h = r == n ? 0 : heights[r];
            // Note for right boundary we need to check h[r] <= h[i]
            // to avoid double counting.
            //   e.g. [0,3,3,3]
            // If we find right index by h[r] < h[i] then the right
            // boundary is n for all index in [2,4] which leads to
            // double counting. By check h[r] <= h[i] it can limit the
            // upper = Math.min(w, heights[i]) to 1 to invalidate index
            // 2 and 3 so only 4 will be valid and counted.
            while (stack.size() > 1 && heights[stack.peek()] >= h) {
                int i = stack.pop();
                int l = stack.peek();
                int w = r - l - 1;
                int lh = l < 0 ? 0 : heights[l];
                int rh = r >= n ? 0 : heights[r];
                int lower = Math.max(lh, rh) + 1;
                int upper = Math.min(w, heights[i]);
                if (lower <= upper) {
                    res += (2 * w + 2 - lower - upper) * (upper - lower + 1) / 2;
                }
            }
            stack.push(r);
        }
        return res;
    }

    private int countSquaresTwoPass(int[] heights) {
        // https://leetcode.cn/problems/count-square-submatrices-with-all-ones/solutions/3751608/tu-jie-dong-tai-gui-hua-jian-ji-xie-fa-p-1kiy/
        int res = 0, n = heights.length;
        int[] left = new int[n], right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.peek();
            stack.push(i);
        }
        stack.clear();
        stack.push(n);
        for (int i = n - 1; i >= 0; i--) {
            // Find h[r] >= h[i]
            while (stack.size() > 1 && heights[stack.peek()] > heights[i]) {
                stack.pop();
            }
            right[i] = stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            int l = left[i];
            int r = right[i];
            int w = r - l - 1;
            int lh = l < 0 ? 0 : heights[l];
            int rh = r >= n ? 0 : heights[r];
            int lower = Math.max(lh, rh) + 1;
            int upper = Math.min(w, heights[i]);
            if (lower <= upper) {
                res += (2 * w + 2 - lower - upper) * (upper - lower + 1) / 2;
            }
        }
        return res;
    }

    // https://www.techiedelight.com/find-size-largest-square-sub-matrix-1s-present-given-binary-matrix/
    public int countSquares(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        int[][] dp = new int[R][C];
        int res = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dp[i][j] = matrix[i][j];
                if (i > 0 && j > 0 && matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                res += dp[i][j];
            }
        }
        return res;
    }
}
