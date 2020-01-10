package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/2016.
 * #0080 https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class RemoveDuplicatesFromSortedArrayII {
	public int removeDuplicates(int[] nums) {
		int n = nums.length;
		if(n <= 2){
			return n;
		}
		int j = 2, cur = nums[1];
		for(int i = 2; i < n; i++){
			// append condition is:
			// 1. current value is greater than the current max
			// 2. current value is equal to the current max but
			//     we only have one same value in the new array
			if(nums[i] > cur || nums[j - 1] != nums[j - 2]){
				nums[j++] = cur = nums[i];
			}
		}
		return j;
	}
}
