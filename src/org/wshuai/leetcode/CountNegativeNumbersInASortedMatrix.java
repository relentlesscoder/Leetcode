package org.wshuai.leetcode;

/**
 * Created by Wei on 02/18/2020.
 * #1351 https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
 */
public class CountNegativeNumbersInASortedMatrix {
	// time O(r*log(c))
	public int countNegatives(int[][] grid) {
		int res = 0, r = grid.length, c = grid[0].length;
		for(int i = 0; i < r; i++){
			res += binarySearch(grid[i], 0);
		}
		return res;
	}

	private int binarySearch(int[] nums, int target){
		int left = 0, right = nums.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[mid] >= target){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return nums.length - left;
	}
}
