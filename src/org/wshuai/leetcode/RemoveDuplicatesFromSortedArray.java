package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/2016.
 * #0026 https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesFromSortedArray {
	// time O(n)
	public int removeDuplicates(int[] nums) {
		if(nums.length <= 1){
			return nums.length;
		}
		int i = 1;
		int cur = nums[0];
		for(int j = 1; j < nums.length; j++){
			if(nums[j] != cur){
				nums[i++] = nums[j];
				cur = nums[j];
			}
		}
		return i;
	}
}
