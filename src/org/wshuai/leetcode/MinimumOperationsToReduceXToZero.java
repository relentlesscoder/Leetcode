package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/26/2023.
 * #1658 https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 */
public class MinimumOperationsToReduceXToZero {

	// time O(n), space O(n)
	public int minOperationsPrefixSum(int[] nums, int x) {
		int n = nums.length, sum = 0, prefixSum = 0, maxSubarrayLength = -1;
		for (int num : nums) {
			sum += num;
		}
		if (sum == x) {
			return n;
		}
		Map<Integer, Integer> prefixSumMap = new HashMap<>();
		prefixSumMap.put(0, -1);
		for (int i = 0; i < n; i++) {
			prefixSum += nums[i];
			int key = prefixSum - sum + x;
			if (prefixSumMap.containsKey(key)) {
				maxSubarrayLength = Math.max(maxSubarrayLength, i - prefixSumMap.get(key));
			}
			prefixSumMap.putIfAbsent(prefixSum, i);
		}
		return maxSubarrayLength == -1 ? -1 : n - maxSubarrayLength;
	}
}
