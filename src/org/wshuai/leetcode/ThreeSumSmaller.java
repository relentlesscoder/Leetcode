package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/28/2016.
 * #0259 https://leetcode.com/problems/3sum-smaller/
 */
public class ThreeSumSmaller {
	// time O(n^2)
	public int threeSumSmaller(int[] nums, int target) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int res = 0, n = nums.length;
		Arrays.sort(nums);
		for(int i = 0; i < n - 2; i++){
			int j = i + 1, k = n - 1;
			while(j < k){
				if(nums[i] + nums[j] + nums[k] >= target){
					k--;
				}else{
					res += (k - j);
					j++;
				}
			}
		}
		return res;
	}
}
