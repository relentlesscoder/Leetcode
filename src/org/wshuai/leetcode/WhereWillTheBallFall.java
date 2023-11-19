package org.wshuai.leetcode;

/**
 * Created by Wei on 12/28/2020.
 * #1706 https://leetcode.com/problems/where-will-the-ball-fall/
 */
public class WhereWillTheBallFall {

    // time O(m*n)
    public int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            int b = i;
            for(int j = 0; j < m; j++){
                if((grid[j][b] == 1 && (b == n - 1 || grid[j][b + 1] == -1))
                        || (grid[j][b] == -1 && (b == 0 || grid[j][b - 1] == 1))){
                    b = -1;
                    break;
                }else{
                    b += grid[j][b];
                }
            }
            res[i] = b;
        }
        return res;
    }
}
