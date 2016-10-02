package org.wshuai.leetcode;

/**
 * Created by Wei on 9/25/2016.
 */
public class SearchA2DMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
      return false;
    }

    int rLen = matrix.length;
    int cLen = matrix[0].length;

    int left = 0;
    int right = rLen*cLen - 1;

    while(left <= right){
      int mid = left + (right-left)/2;
      int row = mid/cLen;
      int col = mid%cLen;

      int val = matrix[row][col];
      if(val == target){
        return true;
      }else if(val < target){
        left = mid+1;
      }else{
        right = mid-1;
      }
    }

    return false;
  }
}
