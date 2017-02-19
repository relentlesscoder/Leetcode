package org.wshuai.leetcode;

/**
 * Created by Wei on 2/19/17.
 * #498 https://leetcode.com/problems/diagonal-traverse/?tab=Description
 */
public class DiagonalTraverse {
  public int[] findDiagonalOrder(int[][] matrix) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
      return new int[0];
    }
    int rows = matrix.length;
    int cols = matrix[0].length;
    int len = rows*cols;
    int[] res = new int[len];
    int i = 0;
    int j = 0;
    for(int idx = 0; idx < len; idx++){
      res[idx] = matrix[i][j];
      if(i == 0 && j != cols-1){
        j++;
        while(j > 0 && i < rows-1){
          res[++idx] = matrix[i][j];
          i++;
          j--;
        }
      }else if(i == rows-1){
        j++;
        while(j < cols-1 && i > 0){
          res[++idx] = matrix[i][j];
          i--;
          j++;
        }
      }else if(j == 0){
        i++;
        while(i > 0 && j < cols-1){
          res[++idx] = matrix[i][j];
          i--;
          j++;
        }
      }else if(j == cols-1){
        i++;
        while(i < rows-1 && j > 0){
          res[++idx] = matrix[i][j];
          i++;
          j--;
        }
      }
    }

    return res;
  }
}
