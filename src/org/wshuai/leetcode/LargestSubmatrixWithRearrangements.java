package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/20/2021.
 * #1727 https://leetcode.com/problems/largest-submatrix-with-rearrangements/
 */
public class LargestSubmatrixWithRearrangements {

    // time O(m*n*log(n))
    public int largestSubmatrix(int[][] matrix) {
        int res = 0, m = matrix.length, n = matrix[0].length;
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 1){
                    matrix[i][j] += matrix[i - 1][j]; // For each column, find the number of consecutive ones ending at each position.
                }
            }
        }
        for(int i = 0; i < m; i++){
            Arrays.sort(matrix[i]); // For each row, sort the cumulative ones in non-increasing order and "fit" the largest submatrix.
            for(int j = 0; j < n; j++){
                res = Math.max(res, matrix[i][j] * (n - j));
            }
        }
        return res;
    }
}
