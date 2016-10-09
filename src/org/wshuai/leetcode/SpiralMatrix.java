package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/8/16.
 */
public class SpiralMatrix {
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> lst = new ArrayList<Integer>();
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
      return lst;
    }

    int rows = matrix.length;
    int cols = matrix[0].length;
    int top = 0;
    int bottom = rows-1;
    int left = 0;
    int right = cols-1;

    while(top <= bottom && left <= right){
      if(top == bottom){
        for(int i = left; i <= right; i++){
          lst.add(matrix[top][i]);
        }
        break;
      }
      if(left == right){
        for(int i = top; i <= bottom; i++){
          lst.add(matrix[i][left]);
        }
        break;
      }
      for(int i = left; i <= right; i++){
        lst.add(matrix[top][i]);
      }
      for(int i = top+1; i <= bottom; i++){
        lst.add(matrix[i][right]);
      }
      for(int i = right-1; i >= left; i--){
        lst.add(matrix[bottom][i]);
      }
      for(int i = bottom-1; i > top; i--){
        lst.add(matrix[i][left]);
      }
      top++;
      bottom--;
      left++;
      right--;
    }

    return lst;
  }
}
