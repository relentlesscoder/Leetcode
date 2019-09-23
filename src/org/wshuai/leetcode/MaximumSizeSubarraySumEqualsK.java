package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/2/2016.
 * #325 https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 */
public class MaximumSizeSubarraySumEqualsK {
	//O(n) time, same idea as #303 https://leetcode.com/problems/range-sum-query-immutable/
	//but use hashmap to improve the time
	public int maxSubArrayLen(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int max = 0;
		int len = nums.length;
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		for (int i = 0; i < len; i++) {
			sum += nums[i];
			int diff = sum - k;
			if (map.containsKey(diff)) {
				int idx = map.get(diff);
				max = Math.max(max, i - idx);
			}

			//If map already contains the key, don't override it
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return max;
	}
}
