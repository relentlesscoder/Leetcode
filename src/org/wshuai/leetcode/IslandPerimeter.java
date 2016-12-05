package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/2016.
 * #463 https://leetcode.com/problems/island-perimeter/
 */
public class IslandPerimeter {
  //O(n^2), DFS
  public int islandPerimeter(int[][] grid) {
    if(grid == null || grid.length == 0 || grid[0].length == 0){
      return 0;
    }
    int rLen = grid.length;
    int cLen = grid[0].length;
    boolean[][] visited = new boolean[rLen][cLen];
    RefType rt = new RefType();
    for(int i = 0; i < rLen; i++){
      for(int j = 0; j < cLen; j++){
        if(grid[i][j]==1 && !visited[i][j]){
          islandPerimeterUtil(grid, visited, rt, rLen, cLen, i, j);
          break;
        }
      }
    }
    return rt.val;
  }

  private void islandPerimeterUtil(int[][] grid, boolean[][] visited, RefType rt, int rLen, int cLen, int i, int j){
    if(i < 0 || i >= rLen || j < 0 || j >= cLen || grid[i][j] == 0){
      rt.val++;
    }else if(!visited[i][j]){
      visited[i][j] = true;
      islandPerimeterUtil(grid, visited, rt, rLen, cLen, i-1, j);
      islandPerimeterUtil(grid, visited, rt, rLen, cLen, i+1, j);
      islandPerimeterUtil(grid, visited, rt, rLen, cLen, i, j-1);
      islandPerimeterUtil(grid, visited, rt, rLen, cLen, i, j+1);
    }
  }
}
