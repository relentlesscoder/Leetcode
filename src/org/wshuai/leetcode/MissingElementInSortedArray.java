package org.wshuai.leetcode;

/**
 * Created by Wei on 10/2/2019.
 * #1060 https://leetcode.com/problems/missing-element-in-sorted-array/
 */
public class MissingElementInSortedArray {
	public int missingElement(int[] nums, int k) {
		int left = 0;
		int right = nums.length - 1;
		int totalMissing = missing(right, nums);
		if(totalMissing < k){
			return nums[right] + k - totalMissing;
		}
		while (left < right){
			int mid = left + (right - left) / 2;
			if(missing(mid, nums) < k){
				left = mid + 1;
			}else{
				right = mid;
			}
		}

		return nums[left - 1] + k - missing(left - 1, nums);
	}

	private int missing(int index, int[] nums){
		return nums[index] - nums[0] - index;
	}
}
