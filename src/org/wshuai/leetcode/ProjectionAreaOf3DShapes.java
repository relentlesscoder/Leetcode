package org.wshuai.leetcode;

/**
 * Created by Wei on 8/23/19.
 * #883 https://leetcode.com/problems/projection-area-of-3d-shapes/
 */
public class ProjectionAreaOf3DShapes {
    public int projectionArea(int[][] grid) {
        int ans = 0;
        int N = grid.length;
        for(int i = 0; i < N; i++){
            int rmax = 0;
            int cmax = 0;
            for(int j = 0; j < N; j++){
                // calculate top projection, grid[i][j] = 0 is the special case
                if(grid[i][j] > 0){
                    ans++;
                }
                // get row max
                rmax = grid[i][j] > rmax ? grid[i][j] : rmax;
                // get column max
                cmax = grid[j][i] > cmax ? grid[j][i] : cmax;
            }
            ans += rmax + cmax;
        }
        return ans;
    }
}
