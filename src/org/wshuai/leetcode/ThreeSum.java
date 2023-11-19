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
		int n = nums.length;
		Arrays.sort(nums);
		for(int i = 0; i < n - 2; i++){
			// deduplicate
			if(i > 0 && nums[i - 1] == nums[i]){
				continue;
			}
			// branch pruning
			if(nums[i] > 0){
				break;
			}
			int target = -nums[i], j = i + 1, k = n - 1;
			while(j < k){
				int left = nums[j], right = nums[k], sum = left + right;
				if(sum == target){
					res.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
				}else if(sum < target){
					j++;
				}else{
					k--;
				}
				// deduplicate
				while(j < k && j > i + 1 && nums[j] == nums[j - 1]){
					j++;
				}
				while(j < k && k < n - 1 && nums[k] == nums[k + 1]){
					k--;
				}
			}
		}
		return res;
	}
}
