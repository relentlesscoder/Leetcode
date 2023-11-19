package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2019.
 * #1060 https://leetcode.com/problems/missing-element-in-sorted-array/
 */
public class MissingElementInSortedArray {

	// time log(n)
	public int missingElement(int[] nums, int k) {
		int left = 0, right = nums.length - 1, totalMissing = countMissing(nums, right);
		if(totalMissing < k){
			return nums[right] + k - totalMissing;
		}
		while(left < right){
			int mid = left + (right - left) / 2;
			if(countMissing(nums, mid) < k){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return nums[left - 1] + k - countMissing(nums, left - 1);
	}

	private int countMissing(int[] nums, int i){
		return nums[i] - nums[0] - i;
	}
}
