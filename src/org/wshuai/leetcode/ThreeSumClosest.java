package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/21/2016.
 * #0016 https://leetcode.com/problems/3sum-closest/
 */
public class ThreeSumClosest {
	// time O(n^2)
	public int threeSumClosest(int[] nums, int target) {
		if(nums == null || nums.length < 3){
			return -1;
		}
		Arrays.sort(nums);
		int n = nums.length, res = 0, diff = Integer.MAX_VALUE;
		for(int i = 0; i < n - 2; i++){
			int j = i + 1, k = n - 1;
			while(j < k){
				int sum = nums[i] + nums[j] + nums[k];
				if(sum == target){
					return target;
				}else{
					int cur = Math.abs(sum - target);
					if(cur < diff){
						diff = cur;
						res = sum;
					}
					if(sum > target){
						k--;
					}else{
						j++;
					}
				}
			}
		}
		return res;
	}
}
