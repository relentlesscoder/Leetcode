package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/6/16.
 */
public class UniquePaths {

  public int uniquePaths1D(int m, int n) {
    if(m <= 0 || n <= 0){
      return 0;
    }

    int[] aux = new int[n];
    Arrays.fill(aux, 1);
    for(int i = 1; i < m; i++){
      for(int j = 1; j < n; j++){
        aux[j] += aux[j-1];
      }
    }
    return aux[n-1];
  }

  public int uniquePaths2D(int m, int n) {
    if(m <= 0 || n <= 0){
      return 0;
    }

    int[][] aux = new int[m][n];
    for(int i = 0; i < m; i++){
      aux[i][0] = 1;
    }
    for(int i = 0; i < n; i++){
      aux[0][i] = 1;
    }
    for(int i = 1; i < m; i++){
      for(int j = 1; j < n; j++){
        aux[i][j] = aux[i-1][j] + aux[i][j-1];
      }
    }
    return aux[m-1][n-1];
  }
}
