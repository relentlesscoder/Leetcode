package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/21/2016.
 * #0016 https://leetcode.com/problems/3sum-closest/
 */
public class ThreeSumClosest {
	// time O(n^2)
	public int threeSumClosest(int[] nums, int target) {
		int[] res = new int[]{0, Integer.MAX_VALUE};
		Arrays.sort(nums);
		int n = nums.length;
		for(int i = 0; i < n - 2; i++){
			if(i > 0 && nums[i] == nums[i - 1]){
				continue;
			}
			int left = i + 1;
			int right = n - 1;
			while(left < right){
				int lval = nums[left];
				int rval = nums[right];
				int sum = lval + rval + nums[i];
				int diff = Math.abs(sum - target);
				if(diff < res[1]){
					res[1] = diff;
					res[0] = sum;
				}
				if(sum == target){
					return sum;
				}else if(sum < target){
					while(left < right && nums[left] == lval){
						left++;
					}
				}else{
					while(left < right && nums[right] == rval){
						right--;
					}
				}
			}
		}
		return res[0];
	}
}
