package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/10/2016.
 * #0219 https://leetcode.com/problems/contains-duplicate-ii/
 */
public class ContainsDuplicateII {

	// time O(n), space O(n)
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		int n = nums.length;
		Map<Integer, Integer> last = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int pre = last.getOrDefault(nums[i], -1);
			if (pre != -1 && i - pre <= k) {
				return true;
			}
			last.put(nums[i], i);
		}
		return false;
	}
}
