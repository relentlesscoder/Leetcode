package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/10/2016.
 */
public class ContainsDuplicateII {
	// time O(n), space O(n)
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++){
			if(map.containsKey(nums[i])
				&& Math.abs(map.get(nums[i]) - i) <= k){
				return true;
			}
			map.put(nums[i], i);
		}
		return false;
	}
}
