package org.wshuai.leetcode;

/**
 * Created by Wei on 8/24/19.
 * #807 https://leetcode.com/problems/max-increase-to-keep-city-skyline/
 */
public class MaxIncreaseToKeepCitySkyline {
    // same idea as #883, #892
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[] rmax = new int[r];
        int[] cmax = new int[c];
        for(int i = 0; i < r; i++){
            int rm = 0;
            for(int j = 0; j < c; j++){
                rm = grid[i][j] > rm ? grid[i][j] : rm;
            }
            rmax[i] = rm;
        }
        for(int i = 0; i < c; i++){
            int cm = 0;
            for(int j = 0; j < r; j++){
                cm = grid[j][i] > cm ? grid[j][i] : cm;
            }
            cmax[i] = cm;
        }
        int sum = 0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                sum += Math.min(rmax[i], cmax[j]) - grid[i][j];
            }
        }
        return sum;
    }
}
