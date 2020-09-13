package org.wshuai.leetcode;

/**
 * Created by Wei on 09/13/2020.
 * #1582 https://leetcode.com/problems/special-positions-in-a-binary-matrix/
 */
public class SpecialPositionsInABinaryMatrix {

    // time O(m*n), space O(m+n)
    public int numSpecial(int[][] mat) {
        int res = 0, m = mat.length, n = mat[0].length;
        int[][] rows = new int[m][2];
        int[] cols = new int[n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 0){
                    continue;
                }
                rows[i][0]++;
                rows[i][1] = j;
                cols[j]++;
            }
        }
        for(int i = 0; i < m; i++){
            if(rows[i][0] == 1 && cols[rows[i][1]] == 1){
                res++;
            }
        }
        return res;
    }
}
