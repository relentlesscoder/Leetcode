package org.wshuai.leetcode;

/**
 * Created by Wei on 7/18/17.
 * #566 https://leetcode.com/problems/reshape-the-matrix/
 */
public class ReshapeTheMatrix {
  public int[][] matrixReshape(int[][] nums, int r, int c) {
    if(nums == null || nums.length == 0 || nums[0].length == 0){
      return nums;
    }
    int rl = nums.length;
    int cl = nums[0].length;
    if(rl * cl < r * c){
      return nums;
    }
    int[][] res = new int[r][c];
    for(int i = 0; i < r; i++){
      for(int j = 0; j < c; j++){
        int idx = c*i+j;
        res[i][j] = nums[idx/cl][idx%cl];
      }
    }
    return res;
  }
}
