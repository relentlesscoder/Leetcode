package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2024.
 * #2133 https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers/
 */
public class CheckIfEveryRowAndColumnContainsAllNumbers {

    // time O(n * n), space O(n * n)
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            int[] map = new int[n + 1];
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < 1 || matrix[i][j] > n) {
                    return false;
                }
                map[matrix[i][j]]++;
            }
            for (int k = 1; k <= n; k++) {
                if (map[k] != 1) {
                    return false;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            int[] map = new int[n + 1];
            for (int i = 0; i < n; i++) {
                map[matrix[i][j]]++;
            }
            for (int k = 1; k <= n; k++) {
                if (map[k] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
