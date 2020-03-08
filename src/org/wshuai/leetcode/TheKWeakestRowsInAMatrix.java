package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/03/2020.
 * #1341 https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/
 */
public class TheKWeakestRowsInAMatrix {
	// time O(r*log(c))
	public int[] kWeakestRows(int[][] mat, int k) {
		if(mat == null || mat.length == 0 || mat[0].length == 0 || k > mat.length){
			return new int[0];
		}
		int r = mat.length, c = mat[0].length;
		int[][] count = new int[mat.length][2];
		for(int i = 0; i < r; i++){
			count[i][0] = i;
			count[i][1] = binarySearch(mat[i]);
		}
		Arrays.sort(count, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
		int[] res = new int[k];
		for(int i = 0; i < k; i++){
			res[i] = count[i][0];
		}
		return res;
	}

	private int binarySearch(int[] nums){
		int left = 0, right = nums.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[mid] == 1){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}
}
