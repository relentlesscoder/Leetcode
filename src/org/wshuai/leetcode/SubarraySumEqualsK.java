package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/05/2019.
 * #0560 https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubarraySumEqualsK {

	// time O(n), space O(n)
	public int subarraySum(int[] nums, int k) {
		int res = 0, sum = 0;
		Map<Integer, Integer> prefixSum = new HashMap<>();
		prefixSum.put(0, 1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			res += prefixSum.getOrDefault(sum - k, 0);
			prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
		}
		return res;
	}
}
