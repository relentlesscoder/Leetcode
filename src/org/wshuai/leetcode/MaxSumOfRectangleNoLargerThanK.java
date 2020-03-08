package org.wshuai.leetcode;

import java.util.TreeSet;

/**
 * Created by Wei on 08/26/2019.
 * #0363 https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 */
public class MaxSumOfRectangleNoLargerThanK {
	// time O(c^2*r*log(r))
	public int maxSumSubmatrix(int[][] matrix, int k) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return 0;
		}
		int res = Integer.MIN_VALUE, r = matrix.length, c = matrix[0].length;
		int[][] prefix = new int[r][c + 1];
		// prefix sum of columns
		for(int i = 0; i < r; i++){
			for(int j = 1; j <= c; j++){
				prefix[i][j] = prefix[i][j - 1] + matrix[i][j - 1];
			}
		}
		for(int i = 0; i < c; i++){
			for(int j = i; j < c; j++){
				int[] sum = new int[r];
				for(int x = 0; x < r; x++){
					sum[x] = prefix[x][j + 1] - prefix[x][i];
				}
				res = Math.max(res, findMax(sum, k));
			}
		}
		return res;
	}

	private int findMax(int[] arr, int k){
		int res = Integer.MIN_VALUE, sum = 0;
		TreeSet<Integer> treeset = new TreeSet<>();
		treeset.add(0);
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];
			// find the closest sum to target
			Integer min = treeset.ceiling(sum - k);
			if(min != null){
				res = Math.max(res, sum - min);
			}
			treeset.add(sum);
		}
		return res;
	}
}
