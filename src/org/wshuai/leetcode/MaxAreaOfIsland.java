package org.wshuai.leetcode;

/**
 * Created by Wei on 9/4/19.
 * #695 https://leetcode.com/problems/max-area-of-island/
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int[][] conn = new int[4][2];
        conn[0] = new int[]{1, 0};
        conn[1] = new int[]{-1, 0};
        conn[2] = new int[]{0, 1};
        conn[3] = new int[]{0, -1};
        int max = 0;
        int row = grid.length;
        int col = grid[0].length;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 1){
                    grid[i][j] = 0;
                    int area = bfs(i, j, grid, conn);
                    max = area > max ? area : max;
                }
            }
        }
        return max;
    }

    private int bfs(int i, int j, int[][] grid, int[][] conn){
        int res = 1;
        for(int k = 0; k < 4; k++){
            int x = i + conn[k][0];
            int y = j + conn[k][1];
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1){
                grid[x][y] = 0;
                res += bfs(x, y, grid, conn);
            }
        }
        return res;
    }
}
