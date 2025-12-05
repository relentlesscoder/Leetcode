package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 02/27/2017.
 * #0525 https://leetcode.com/problems/contiguous-array/
 */
public class ContiguousArray {

	// time O(n), space O(n)
	public int findMaxLength(int[] nums) {
		int res = 0, sum = 0, n = nums.length;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		for (int i = 0; i < n; i++) {
			sum += nums[i] == 1 ? 1 : -1;
			if (map.containsKey(sum)) {
				res = Math.max(res, i - map.get(sum));
			} else {
				map.put(sum, i);
			}
		}
		return res;
	}
}
