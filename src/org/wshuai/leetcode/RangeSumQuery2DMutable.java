package org.wshuai.leetcode;

/**
 * Created by Wei on 9/25/16.
 */
public class RangeSumQuery2DMutable {
  private int[][] btree;
  private int[][] mtx;
  private int rLen;
  private int cLen;

  public RangeSumQuery2DMutable(int[][] matrix) {
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
    btree = new int[rLen][cLen+1];
    mtx = matrix;

    for(int i = 0; i < rLen; i++){
      for(int j = 0; j < cLen; j++){
        add(i, j+1, matrix[i][j]);
      }
    }
  }

  private void add(int row, int col, int val){
    int len = btree[0].length;
    for(int j=col; j < len; j=j+(j&(-j))){
      btree[row][j] += val;
    }
  }

  public void update(int row, int col, int val) {
    if(cLen == 0 || rLen == 0){
      return;
    }
    if(validateIdx(row, col)){
      add(row, col+1, val-mtx[row][col]);
      mtx[row][col] = val;
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    if(cLen == 0 || rLen == 0){
      return 0;
    }
    int sum = 0;
    if(validateIdx(row1, col1) && validateIdx(row2, col2)){
      for(int i = row1; i <= row2; i++){
        sum += sumHelper(i, col2+1)-sumHelper(i, col1);
      }
    }
    return sum;
  }

  private int sumHelper(int row, int col){
    int sum = 0;
    for(int j=col; j>=1; j=j-(j&(-j))){
      sum += btree[row][j];
    }
    return sum;
  }

  private boolean validateIdx(int row, int col){
    return row >= 0 && col >= 0 && row < rLen && col < cLen;
  }
}
