package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 8/9/2015.
 * #1 https://leetcode.com/problems/two-sum/
 */
public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++){
			map.put(target - nums[i], i);
		}
		for(int i = 0; i < nums.length; i++){
			if(map.containsKey(nums[i]) && map.get(nums[i]) != i){
				return new int[]{i, map.get(nums[i])};
			}
		}
		return new int[0];
	}
}

