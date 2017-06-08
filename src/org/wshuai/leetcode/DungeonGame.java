package org.wshuai.leetcode;

/**
 * Created by Wei on 6/7/2017.
 * #174 https://leetcode.com/problems/dungeon-game/
 */
public class DungeonGame {
  private int min = Integer.MAX_VALUE;

  // DFS, TLE
  public int calculateMinimumHPDFS(int[][] dungeon) {
    if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0){
      return 0;
    }
    int row = dungeon.length;
    int col = dungeon[0].length;
    calculateMinimumHPUtil(dungeon, row, col, 0, 0, 0, 0);
    return min;
  }

  private void calculateMinimumHPUtil(int[][] dungeon, int row, int col, int x, int y, int sum, int local){
    if(x < 0 || x >= row || y < 0 || y >= col){
      return;
    }
    sum += dungeon[x][y];
    if(sum < 0){
      local = Math.min(local, sum);
    }
    if(x == row-1 && y == col-1){
      int cmin = Math.abs(local)+1;
      min = Math.min(cmin, min);
      return;
    }
    calculateMinimumHPUtil(dungeon, row, col, x+1, y, sum, local);
    calculateMinimumHPUtil(dungeon, row, col, x, y+1, sum, local);
  }
}
