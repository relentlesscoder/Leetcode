package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2023.
 * #2022 https://leetcode.com/problems/convert-1d-array-into-2d-array/description/
 */
public class Convert1DArrayInto2DArray {

    // time O(n), space O(n)
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n) {
            return new int[0][0];
        }
        int[][] res = new int[m][n];
        for (int i = 0; i < original.length; i++) {
            res[i/n][i%n] = original[i];
        }
        return res;
    }
}
