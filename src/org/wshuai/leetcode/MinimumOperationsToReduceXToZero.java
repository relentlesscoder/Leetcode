package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/26/2023.
 * #1658 https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
 */
public class MinimumOperationsToReduceXToZero {

	// time O(n), space O(1)
	public int minOperations(int[] nums, int x) {
		int n = nums.length, sum = 0, prefixSum = 0, maxSubarrayLength = -1;
		for (int num : nums) {
			sum += num;
		}
		for (int start = 0, end = 0; end < n; end++) {
			prefixSum += nums[end]; // since array elements are all positive, when sum is greater than (sum - x), we advance left as much as we could to make the sum smaller
			while (start < n && prefixSum > sum - x) {
				prefixSum -= nums[start++];
			}
			if (prefixSum == sum - x) {
				maxSubarrayLength = Math.max(maxSubarrayLength, end - start + 1);
			}
		}
		return maxSubarrayLength == -1 ? -1 : n - maxSubarrayLength;
	}

	// time O(n), space O(n)
	public int minOperationsPrefixSum(int[] nums, int x) {
		// convert the problem to #0325 https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
		// to get the max size subarray with sum equals to (sum - x)
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
