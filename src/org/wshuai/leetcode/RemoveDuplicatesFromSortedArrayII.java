package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/2016.
 * #0080 https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class RemoveDuplicatesFromSortedArrayII {

	// time O(n), space O(1)
	public int removeDuplicates(int[] nums) {
		int n = nums.length, j = 2;
		for(int i = 2; i < n; i++){
			// 当前数字不等与前一个数字或者当前数字已经重复出现2次
			if(nums[i] != nums[i - 1] || nums[j - 1] != nums[j - 2]){
				nums[j++] = nums[i];
			}
		}
		return j;
	}
}
