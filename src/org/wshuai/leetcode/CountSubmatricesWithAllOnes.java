package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 07/22/2020.
 * #1504 https://leetcode.com/problems/count-submatrices-with-all-ones/
 */
public class CountSubmatricesWithAllOnes {

    // time O(m * n), space O(n)
    public int numSubmatMonotonicStack(int[][] mat) {
        // Use idea from #0084 to count rectangle with
        // all ones for each row as the bottom
        int res = 0, m = mat.length, n = mat[0].length;
        int[] row = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[j] = mat[i][j] == 0 ? 0 : row[j] + 1;
            }
            res += countRectangles(row);
        }
        return res;
    }

    private int countRectangles(int[] heights) {
        // https://leetcode.cn/problems/count-submatrices-with-all-ones/solutions/3704971/omn-dan-diao-zhan-pythonjavacgo-by-endle-jf8l/
        int res = 0, n = heights.length;
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{-1, 0, -1});
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && stack.peek()[2] >= heights[i]) {
                stack.pop();
            }
            int count = stack.peek()[1] + (i - stack.peek()[0]) * heights[i];
            stack.push(new int[]{i, count, heights[i]});
            res += count;
        }
        return res;
    }

    // time O(n * m^2), space O(m*n)
    public int numSubmat(int[][] mat) {
        int res = 0, m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) { // Iterate top rows from 0 to m - 1
            int[] nums = new int[n]; // Used to store height (consecutive 1s)
            for (int j = i; j < m; j++) { // Iterate bottom rows from i to m - 1
                int last = -1, h = j - i + 1;
                for (int k = 0; k < n; k++) {
                    nums[k] += mat[j][k];
                    if (nums[k] != h) {
                        last = k;
                    }
                    // For each index k with height h, we can have k - last all ones
                    // rectangle ends at column k
                    /**
                     * 1 1 0 1 1
                     * 1 1 0 1 1
                     * 1 1 1 1 1
                     * 1 1 1 1 1
                     */
                    res += k - last;
                }
            }
        }
        return res;
    }
}
