package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 07/05/2025.
 * #3417 https://leetcode.com/problems/zigzag-grid-traversal-with-skip/
 */
public class ZigzagGridTraversalWithSkip {

    // time O(m * n), space O(m * n)
    public List<Integer> zigzagTraversal(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < n; j++) {
                    if (j % 2 == 0) {
                        res.add(grid[i][j]);
                    }
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    if (j % 2 == 1) {
                        res.add(grid[i][j]);
                    }
                }
            }
        }
        return res;
    }
}
