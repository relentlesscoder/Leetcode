package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/25/2016.
 * #0018 https://leetcode.com/problems/4sum/
 */
public class FourSum {
	// time O(n^3)
	// more reading https://leetcode.com/problems/4sum/discuss/8609/My-solution-generalized-for-kSums-in-JAVA
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if(nums == null || nums.length < 4){
			return res;
		}
		Arrays.sort(nums);
		int n = nums.length;
		for(int i = 0; i < n - 3; i++){
			// pruning
			if(nums[i] * 4 > target){
				break;
			}
			// avoid duplicates
			if(i > 0 && nums[i] == nums[i - 1]){
				continue;
			}
			for(int j = i + 1; j < n - 2; j++){
				// pruning
				if(nums[j] * 3 > target - nums[i]){
					break;
				}
				// avoid duplicates
				if(j > i + 1 && nums[j] == nums[j - 1]){
					continue;
				}
				int sum = nums[i] + nums[j];
				int left = j + 1;
				int right = n - 1;
				while(left < right){
					int lval = nums[left];
					int rval = nums[right];
					int cur = sum + lval + rval;
					if(cur == target){
						res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
						// avoid duplicates
						while(left < right && nums[left] == lval){
							left++;
						}
						// avoid duplicates
						while(left < right && nums[right] == rval){
							right--;
						}
					}else if(cur < target){
						left++;
					}else{
						right--;
					}
				}
			}
		}
		return res;
	}
}
