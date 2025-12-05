package org.wshuai.leetcode;

/**
 * Created by Wei on 04/05/2025.
 * #2661 https://leetcode.com/problems/first-completely-painted-row-or-column/
 */
public class FirstCompletelyPaintedRowOrColumn {

    // time O(m * n), space O(m * n)
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] rowCount = new int[m], colCount = new int[n], reverseMap = new int[arr.length + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                reverseMap[mat[i][j]] = i * n + j;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int r = reverseMap[arr[i]] / n , c = reverseMap[arr[i]] % n;
            if (++rowCount[r] == n) {
                return i;
            }
            if (++colCount[c] == m) {
                return i;
            }
        }
        return -1;
    }
}
