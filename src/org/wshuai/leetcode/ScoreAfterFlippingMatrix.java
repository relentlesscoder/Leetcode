package org.wshuai.leetcode;

/**
 * Created by Wei on 9/28/2019.
 * #861 https://leetcode.com/problems/score-after-flipping-matrix/
 */
public class ScoreAfterFlippingMatrix {
  public int matrixScore(int[][] A) {
    int r = A.length, c = A[0].length;
    int res = 0;
    for(int j = 0; j < c; j++){
      int col = 0;
      for(int i = 0; i < r; i++){
        col += A[i][j] ^ A[i][0];
      }
      res += Math.max(col, r - col) * (1 << (c - 1 - j));
    }
    return res;
  }
}
