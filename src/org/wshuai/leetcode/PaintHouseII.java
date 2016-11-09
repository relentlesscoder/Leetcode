package org.wshuai.leetcode;

/**
 * Created by Wei on 11/4/16.
 * #265 https://leetcode.com/problems/paint-house-ii/
 */
public class PaintHouseII {

  //O(n*k) 4ms, DP
  public int minCostII(int[][] costs) {
    if(costs == null){
      return 0;
    }
    int rows = costs.length;
    if(rows == 0){
      return 0;
    }
    int cols = costs[0].length;
    if(cols == 0){
      return 0;
    }
    for(int i = 1; i < rows; i++){
      int x = getNextMin(i-1, -1, costs, cols);
      int y = getNextMin(i-1, x, costs, cols);
      for(int j = 0; j < cols; j++){
        if(j != x){
          costs[i][j] += costs[i-1][x];
        }else{
          costs[i][j] += costs[i-1][y];
        }
      }
    }
    int idx = getNextMin(rows-1, -1, costs, cols);
    return costs[rows-1][idx];
  }

  private int getNextMin(int row, int idx, int[][] costs, int cols){
    int res = -1;
    int min = Integer.MAX_VALUE;
    for(int i = 0; i < cols; i++){
      if(i == idx){
        continue;
      }
      if(costs[row][i] < min){
        min = costs[row][i];
        res = i;
      }
    }
    return res;
  }

  //O(k^n) TLE
  public int minCostIIDFS(int[][] costs) {
    if(costs == null){
      return 0;
    }
    int rows = costs.length;
    if(rows == 0){
      return 0;
    }
    int cols = costs[0].length;
    if(cols == 0){
      return 0;
    }
    MinSumObj ms = new MinSumObj();
    minCostUtil(0, -1, 0, ms, costs, rows, cols);
    return ms.min;
  }

  private void minCostUtil(int row, int lastColor, int sum, MinSumObj ms,
                           int[][] costs, int rows, int cols){
    if(row == rows){
      if(sum < ms.min){
        ms.min = sum;
      }
      return;
    }else{
      for(int i = 0; i < cols; i++){
        if(i == lastColor){
          continue;
        }
        int cost = costs[row][i];
        minCostUtil(row+1, i, sum+cost, ms, costs, rows, cols);
      }
    }
  }
}

class MinSumObj{
  int min = Integer.MAX_VALUE;
}
