package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/14/2016.
 * #0015 https://leetcode.com/problems/3sum/
 */
public class ThreeSum {

	// time O(n^2)
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if(nums == null || nums.length < 3){
			return res;
		}
		Arrays.sort(nums);
		int n = nums.length;
		for(int i = 0; i < n - 2; i++){
			if(nums[i] > 0){
				break;
			}
			// avoid duplicates
			if(i > 0 && nums[i] == nums[i - 1]){
				continue;
			}
			int target = -nums[i];
			int left = i + 1;
			int right = n - 1;
			while(left < right){
				int lval = nums[left];
				int rval = nums[right];
				int sum = lval + rval;
				if(sum == target){
					res.add(Arrays.asList(nums[i], nums[left], nums[right]));
					// avoid duplicates
					while(left < right && nums[left] == lval){
						left++;
					}
					// avoid duplicates
					while(left < right && nums[right] == rval){
						right--;
					}
				}else if(sum < target){
					left++;
				}else{
					right--;
				}
			}
		}
		return res;
	}
}
