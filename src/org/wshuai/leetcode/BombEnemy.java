package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/16.
 * #361 https://leetcode.com/problems/bomb-enemy/
 */
public class BombEnemy {

  //703ms
  public int maxKilledEnemiesNaive(char[][] grid) {
    if(grid == null){
      return 0;
    }
    int rows = grid.length;
    if(rows == 0){
      return 0;
    }
    int cols = grid[0].length;
    if(cols == 0){
      return 0;
    }

    int max = 0;
    int[][] aux = new int[rows][cols];
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        char val = grid[i][j];
        if(val == 'W'){
          aux[i][j] = -1;
          continue;
        }
        if(val == '0'){
          continue;
        }
        if(grid[i][j] == 'E'){
          countEnemy(i, j, rows, cols, aux, grid);
        }
      }
    }

    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        if(aux[i][j] > max){
          max = aux[i][j];
        }
      }
    }

    return max;
  }

  private void countEnemy(int i, int j, int rows, int cols, int[][] aux, char[][] grid){
    //Up
    int u = i-1;
    while(u >= 0 && grid[u][j] != 'W'){
      if(grid[u][j] == '0'){
        aux[u][j]++;
      }
      u--;
    }
    //Down
    int d = i+1;
    while(d < rows && grid[d][j] != 'W'){
      if(grid[d][j] == '0'){
        aux[d][j]++;
      }
      d++;
    }
    //Left
    int l = j-1;
    while(l >= 0 && grid[i][l] != 'W'){
      if(grid[i][l] == '0'){
        aux[i][l]++;
      }
      l--;
    }
    //Right
    int r = j+1;
    while(r < cols && grid[i][r] != 'W'){
      if(grid[i][r] == '0'){
        aux[i][r]++;
      }
      r++;
    }
  }
}
