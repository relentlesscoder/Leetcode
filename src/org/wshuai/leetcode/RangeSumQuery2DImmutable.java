package org.wshuai.leetcode;

/**
 * Created by Wei on 9/25/16.
 * #304 https://leetcode.com/problems/range-sum-query-2d-immutable/
 */
public class RangeSumQuery2DImmutable {
  private int[][] mtx;
  private int rLen;
  private int cLen;

  public RangeSumQuery2DImmutable(int[][] matrix) {
    if(matrix == null){
      return;
    }
    rLen = matrix.length;
    if(rLen == 0){
      return;
    }
    cLen = matrix[0].length;
    if(cLen == 0){
      return;
    }
    mtx = new int[rLen][cLen];
    for(int i = 0; i < rLen; i++){
      mtx[i][0] = matrix[i][0];
      for(int j = 1; j < cLen; j++){
        mtx[i][j] = mtx[i][j-1] + matrix[i][j];
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    if(rLen == 0 || cLen == 0){
      return 0;
    }
    int sum = 0;
    if(validateIdx(row1, col1) && validateIdx(row2, col2)){
      for(int i = row1; i <= row2; i++){
        sum += mtx[i][col2] - (col1 == 0 ? 0 : mtx[i][col1 - 1]);
      }
    }
    return sum;
  }

  private boolean validateIdx(int row, int col){
    return row >= 0 && col >= 0 && row < rLen && col < cLen;
  }
}
