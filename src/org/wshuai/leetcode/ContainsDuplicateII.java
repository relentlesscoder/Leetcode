package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/10/16.
 */
public class ContainsDuplicateII {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int len = nums.length;
		Map<Integer, Integer> set = new HashMap<Integer, Integer>();
		for (int i = 0; i < len; i++) {
			int val = nums[i];
			Integer x = set.get(val);
			if (x != null && i - x <= k) {
				return true;
			} else {
				set.put(val, i);
			}
		}

		return false;
	}
}
