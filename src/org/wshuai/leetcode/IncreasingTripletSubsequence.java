package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #0334 https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {

	// time O(n)
	public boolean increasingTriplet(int[] nums) {
		if(nums == null || nums.length < 3){
			return false;
		}
		int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] <= first){
				first = nums[i];
			}else if(nums[i] <= second){
				second = nums[i];
			}else{
				return true;
			}
		}
		return false;
	}
}
