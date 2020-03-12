package org.wshuai.leetcode;

/**
 * Created by Wei on 07/18/2017.
 * #0566 https://leetcode.com/problems/reshape-the-matrix/
 */
public class ReshapeTheMatrix {
	// time O(m*n)
	public int[][] matrixReshape(int[][] nums, int r, int c) {
		if(nums == null || nums.length == 0 || nums[0].length == 0){
			return nums;
		}
		int m = nums.length, n = nums[0].length;
		if(m * n != r * c){
			return nums;
		}
		int[][] res = new int[r][c];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				int cur = i * n + j;
				res[cur / c][cur % c] = nums[i][j];
			}
		}
		return res;
	}
}
