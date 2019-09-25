package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 9/25/19.
 * #1198 https://leetcode.com/problems/find-smallest-common-element-in-all-rows/
 */
public class FindSmallestCommonElementInAllRows {

	// binary search
	public int smallestCommonElement(int[][] mat) {
		int r = mat.length;
		int c = mat[0].length;
		int[] arr = mat[0];

		for(int i = 0; i < arr.length; i++){
			boolean sme = true;
			for(int j = 1; j < r; j++){
				if(binarySearch(mat[j], arr[i]) < 0){
					sme = false;
					break;
				}
			}
			if(sme){
				return arr[i];
			}
		}

		return -1;
	}

	private int binarySearch(int[] nums, int target){
		int left = 0;
		int right = nums.length - 1;
		while(left < right){
			int mid = (left + right)/2;
			if(nums[mid] >= target){
				right = mid;
			}else{
				left = mid + 1;
			}
		}
		return nums[right] == target ? right : -1;
	}

	public int smallestCommonElementCounting(int[][] mat) {
		int r = mat.length;
		int c = mat[0].length;
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < c; i++){
			for(int j = 0; j < r; j++){
				int count = 1 + map.getOrDefault(mat[j][i], 0);
				if(count == r){
					return mat[j][i];
				}
				map.put(mat[j][i], count);
			}
		}
		return -1;
	}
}
