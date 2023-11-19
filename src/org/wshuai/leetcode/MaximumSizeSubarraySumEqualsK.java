package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/02/2016.
 * #0325 https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 */
public class MaximumSizeSubarraySumEqualsK {

	// time O(n), space O(n)
	public int maxSubArrayLen(int[] nums, int k) {
		int n = nums.length, maxSubArraySize = 0, prefixSum = 0;
		Map<Integer, Integer> prefixMap = new HashMap<>();
		prefixMap.put(0, -1);
		for (int i = 0; i < n; i++) {
			prefixSum += nums[i];
			if (prefixMap.containsKey(prefixSum - k)) {
				maxSubArraySize = Math.max(maxSubArraySize, i - prefixMap.get(prefixSum - k));
			}
			prefixMap.putIfAbsent(prefixSum, i);
		}
		return maxSubArraySize;
	}
}
