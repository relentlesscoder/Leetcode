package org.wshuai.leetcode;

/**
 * Created by Wei on 10/1/2016.
 */
public class PaintHouse {
  public int minCost(int[][] costs) {
    if(costs == null){
      throw new IllegalArgumentException("Invalid input.");
    }
    int rLen = costs.length;
    if(rLen <= 0){
      return 0;
    }
    int cLen = costs[0].length;
    if(cLen != 3){
      return 0;
    }
    for(int i = 1; i < rLen; i++){
      for(int j = 0; j < 3; j++){
        int left = 1;
        int right = 2;
        if(j == 1){
          left--;
        }
        if(j == 2){
          left--;
          right--;
        }
        int lVal = costs[i - 1][left];
        int rVal = costs[i - 1][right];
        costs[i][j] = costs[i][j] + (lVal < rVal ? lVal : rVal);
      }
    }
    int l = costs[rLen - 1][0];
    int m = costs[rLen - 1][1];
    int r = costs[rLen - 1][2];
    int min = l < m ? l : m;
    return min < r ? min : r;
  }
}
