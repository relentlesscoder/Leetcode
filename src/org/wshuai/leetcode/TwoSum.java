package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/09/2015.
 * #0001 https://leetcode.com/problems/two-sum/
 */
public class TwoSum {
	// time O(n), space O(n), one pass
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++){
			if(map.containsKey(nums[i])){
				return new int[]{map.get(nums[i]), i};
			}
			map.put(target - nums[i], i);
		}
		return new int[0];
	}
}

