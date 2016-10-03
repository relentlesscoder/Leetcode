package org.wshuai.leetcode;

/**
 * Created by Wei on 10/2/16.
 */
public class SparseMatrixMultiplication {
  //74 ms
  public int[][] multiply(int[][] A, int[][] B) {
    int aRow = A.length;
    int aCol = A[0].length;
    int bCol = B[0].length;
    int[][] result = new int[aRow][bCol];
    for(int i = 0; i < aRow; i++){
      for(int j = 0; j < aCol; j++){
        if(A[i][j] != 0){
          for(int k = 0; k < bCol; k++){
            result[i][k] += A[i][j]*B[j][k];
          }
        }
      }
    }

    return result;
  }

  //1269 ms
  public int[][] multiplyNaive(int[][] A, int[][] B) {
    int aRow = A.length;
    int aCol = A[0].length;
    int bCol = B[0].length;
    int[][] result = new int[aRow][bCol];
    for(int i = 0; i < aRow; i++){
      for(int j = 0; j < bCol; j++){
        for(int k = 0; k < aCol; k++){
          int val1 = A[i][k];
          int val2 = B[k][j];
          result[i][j] += (val1 == 0 || val2 == 0) ? 0 : A[i][k]*B[k][j];
        }
      }
    }

    return result;
  }
}
