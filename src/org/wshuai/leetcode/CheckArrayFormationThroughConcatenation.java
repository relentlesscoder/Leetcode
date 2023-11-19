package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/08/2020.
 * #1640 https://leetcode.com/problems/check-array-formation-through-concatenation/
 */
public class CheckArrayFormationThroughConcatenation {

    // time O(n)
    public boolean canFormArray(int[] arr, int[][] pieces) {
        int m = pieces.length;
        int[] index = new int[101];
        Arrays.fill(index, -1);
        for(int i = 0; i < m; i++){
            index[pieces[i][0]] = i;
        }
        for(int i = 0; i < arr.length;){
            if(index[arr[i]] == -1){
                return false;
            }
            int row = index[arr[i]];
            for(int j = 0; j < pieces[row].length; j++){
                if(arr[i++] != pieces[row][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
