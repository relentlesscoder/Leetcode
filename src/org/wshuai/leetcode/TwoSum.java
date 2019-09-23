package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 8/9/15.
 * #1 https://leetcode.com/problems/two-sum/
 */
public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		Map<Integer, Integer> temp = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (temp.containsKey(nums[i])) {
				result[0] = (Integer) temp.get(nums[i]);
				result[1] = i;
			} else {
				temp.put(target - nums[i], i);
			}
		}
		return result;
	}
}

