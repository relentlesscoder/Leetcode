package org.wshuai.leetcode;

/**
 * Created by Wei on 01/11/2024.
 * #2946 https://leetcode.com/problems/matrix-similarity-after-cyclic-shifts/
 */
public class MatrixSimilarityAfterCyclicShifts {

    // time O(m * n), space O(1)
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = i % 2 == 0 ? mat[i][(j + k) % n] : mat[i][(j - k % n + n) % n];
                if (mat[i][j] != val) {
                    return false;
                }
            }
        }
        return true;
    }
}
