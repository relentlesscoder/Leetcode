package org.wshuai.leetcode;

/**
 * Created by Wei on 6/7/2017.
 * #174 https://leetcode.com/problems/dungeon-game/
 */
public class DungeonGame {

  //DP, see https://discuss.leetcode.com/topic/6858/my-ac-java-version-suggestions-are-welcome
  public int calculateMinimumHP(int[][] dungeon) {
    if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0){
      return 0;
    }
    int rows = dungeon.length;
    int cols = dungeon[0].length;
    int[][] aux = new int[rows][cols];

    aux[rows-1][cols-1] = Math.max(1, 1-dungeon[rows-1][cols-1]);

    for(int i = rows-2; i >= 0; i--){
      aux[i][cols-1] = Math.max(aux[i+1][cols-1]-dungeon[i][cols-1], 1);
    }
    for(int i = cols-2; i >= 0; i--){
      aux[rows-1][i] = Math.max(aux[rows-1][i+1]-dungeon[rows-1][i], 1);
    }
    for(int i = rows-2; i >= 0; i--){
      for(int j = cols-2; j >= 0; j--){
        int down = Math.max(aux[i+1][j]-dungeon[i][j], 1);
        int right = Math.max(aux[i][j+1]-dungeon[i][j], 1);
        aux[i][j] = Math.min(down, right);
      }
    }
    return aux[0][0];
  }

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
