package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/2016.
 * #0027 https://leetcode.com/problems/remove-element/
 */
public class RemoveElement {
	// time O(n)
	public int removeElement(int[] nums, int val) {
		int i = 0;
		for(int j = 0; j < nums.length; j++){
			if(nums[j] == val){
				continue;
			}
			nums[i++] = nums[j];
		}
		return i;
	}
}
