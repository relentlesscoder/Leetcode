package org.wshuai.leetcode;

/**
 * Created by Wei on 9/10/2019.
 * #980 https://leetcode.com/problems/unique-paths-iii/
 */
public class UniquePathsIII {
    int r0;
    int c0;
    int r1;
    int c1;
    int[][] aux;
    int[][] mov;
    int res;

    public int uniquePathsIII(int[][] grid) {
        res = 0;
        mov = new int[2][4];
        mov[0] = new int[]{1, -1, 0, 0};
        mov[1] = new int[]{0, 0, 1, -1};
        r0 = -1;
        c0 = -1;
        r1 = -1;
        c1 = -1;
        int todo = 0;
        aux = new int[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if (grid[i][j] != -1) {
                    todo++;
                }
                if(grid[i][j] == 1){
                    r0 = i;
                    c0 = j;
                }
                else if(grid[i][j] == 2){
                    r1 = i;
                    c1 = j;
                }
            }
        }
        dfs(grid, r0, c0, todo);
        return res;
    }

    private void dfs(int[][] grid, int r, int c, int todo){
        todo--;
        if(todo < 0){
            return;
        }
        if(r == r1 && c == c1){
            if(todo == 0){
                res++;
            }
            return;
        }
        aux[r][c] = 1;
        for(int i = 0; i < 4; i++){
            int x = r + mov[0][i];
            int y = c + mov[1][i];
            if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] % 2 == 0 && aux[x][y] != 1){
                dfs(grid, x, y, todo);
            }
        }
        aux[r][c] = 0;
    }
}
