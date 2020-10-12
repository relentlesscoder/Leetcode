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
			// smallest number in the triplet cannot be positive
			if(nums[i] > 0){
				break;
			}
			// remove duplicate
			if(i > 0 && nums[i] == nums[i - 1]){
				continue;
			}
			int target = -nums[i], left = i + 1, right = n - 1;
			while(left < right){
				int leftVal = nums[left], rightVal = nums[right], sum = leftVal + rightVal;
				if(sum == target){
					res.add(Arrays.asList(nums[i], nums[left], nums[right]));
					// remove duplicate
					while(left < right && nums[left] == leftVal){
						left++;
					}
					while(left < right && nums[right] == rightVal){
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
