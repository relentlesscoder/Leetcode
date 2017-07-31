package org.wshuai.leetcode;

/**
 * Created by Wei on 9/24/2016.
 * #200 https://leetcode.com/problems/number-of-islands/#/description
 */
public class NumberOfIslands {
  public int numIslands(char[][] grid) {
    if(grid == null){
      return 0;
    }
    int rLen = grid.length;
    if(rLen <= 0){
      return 0;
    }
    int cLen = grid[0].length;
    if(cLen <= 0){
      return 0;
    }

    int[][] visited = new int[rLen][cLen];
    int count = 0;
    for(int i = 0; i < rLen; i++){
      for(int j = 0; j < cLen; j++){
        if(grid[i][j] == '1' && visited[i][j] != 1){
          numIslandsDFS(grid, i, j, visited, rLen, cLen);
          count++;
        }
      }
    }
    return count;
  }

  private void numIslandsDFS(char[][] grid, int i, int j, int[][] visited, int rLen, int cLen){
    visited[i][j] = 1;
    int[] hs = new int[]{0, 0, 1, -1};
    int[] vs = new int[]{1, -1, 0, 0};
    for(int x = 0; x < 4; x++){
      int m = i + hs[x];
      int n = j + vs[x];
      if(m >= 0 && m < rLen && n >= 0 && n < cLen && grid[m][n] == '1' && visited[m][n] != 1){
        numIslandsDFS(grid, m, n, visited, rLen, cLen);
      }
    }
  }
}
