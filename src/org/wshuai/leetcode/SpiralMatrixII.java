package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/16.
 */
public class SpiralMatrixII {
  public int[][] generateMatrix(int n) {
    if(n <= 0){
      return new int[0][0];
    }

    int start = 1;
    int[][] result = new int[n][n];
    int top = 0;
    int bottom = n-1;
    int left = 0;
    int right = n-1;
    while(left <= right && top <= bottom){
      if(left == right){
        result[top][left] = start;
        break;
      }
      for(int i = left; i <= right; i++){
        result[top][i] = start;
        start++;
      }
      for(int i = top+1; i <= bottom; i++){
        result[i][right] = start;
        start++;
      }
      for(int i = right-1; i >= left; i--){
        result[bottom][i] = start;
        start++;
      }
      for(int i = bottom-1; i > top; i--){
        result[i][left] = start;
        start++;
      }
      top++;
      bottom--;
      left++;
      right--;
    }
    return result;
  }
}
