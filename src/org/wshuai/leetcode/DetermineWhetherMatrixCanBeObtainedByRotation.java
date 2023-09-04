package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2023.
 * #1886 https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/description/
 */
public class DetermineWhetherMatrixCanBeObtainedByRotation {

    // time O(n^2), space O(1)
    public boolean findRotation(int[][] mat, int[][] target) {
        return same(mat, target)
                || rotateOnce(mat, target)
                || rotateTwice(mat, target)
                || rotateThreeTimes(mat, target);
    }

    private boolean same(int[][] mat, int[][] target) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean rotateOnce(int[][] mat, int[][] target) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[j][n - 1 - i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean rotateTwice(int[][] mat, int[][] target) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[n - 1 - i][n - 1 - j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean rotateThreeTimes(int[][] mat, int[][] target) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[n - 1 - j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
