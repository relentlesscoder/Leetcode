package org.wshuai.leetcode;

/**
 * Created by Wei on 10/2/2019.
 * #1060 https://leetcode.com/problems/missing-element-in-sorted-array/
 */
public class MissingElementInSortedArray {
	public int missingElement(int[] nums, int k) {
		int l = 0, h = nums.length;
		while(l < h){
			int m = l + (h - l)/2;
			// if there is no numbers missing, nums[m] - m == nums[0]
			// 4, 5, 6, 7, 8, 9, 10 -> 7 - 3 = 4
			// since there are missing numbers.
			// if there is 1 missing number in the left, nums[m] - m - 1 == nums[0] ->
			// if n missing we have nums[m] - m - n == nums[0]
			if(nums[m] - m - k >= nums[0]){
				h = m;
			}else{
				l = m + 1;
			}
		}
		return nums[0] + l + k - 1;
	}
}
