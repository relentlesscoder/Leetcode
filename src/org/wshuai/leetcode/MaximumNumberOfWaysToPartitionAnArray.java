package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/02/2023.
 * #2025 https://leetcode.com/problems/maximum-number-of-ways-to-partition-an-array/
 */
public class MaximumNumberOfWaysToPartitionAnArray {

	// time O(n), space O(n)
	public int waysToPartition(int[] nums, int k) {
		int res = 0, n = nums.length;
		long sum = 0;
		for (int num : nums) {
			sum += num;
		}
		// diff[i] = sum(nums[0] ... nums[i - 1]) - sum(nums[i] ... nums[n - 1])
		long[] diff = new long[n];
		diff[0] = -sum;
		for (int i = 1; i < n; i++) {
			// diff[i - 1] = sum(0 ... i - 2) - sum(i - 1 ... n - 1)
			// diff[i - 1] + nums[i - 1] * 2 = sum(0, i - 1) - sum(i ... n - 1)
			diff[i] = diff[i - 1] + nums[i - 1] * 2;
			if (diff[i] == 0) { // count the pivot when we don't change any values - diff[i] is 0
				res++;
			}
		}
		Map<Long, Integer> left = new HashMap<>(), right = new HashMap<>();
		for (int i = 1; i < n; i++) {
			right.put(diff[i], right.getOrDefault(diff[i], 0) + 1);
		}
		// diff[0] -> sum([]) - sum(nums[0] ... nums[n - 1])
		// diff[1] -> sum(nums[0]) - sum(nums[1] ... nums[n - 1])
		// diff[2] -> sum(nums[0] ... nums[1]) - sum(nums[2] ... nums[n - 1])
		// ...
		// diff[n - 1] -> sum(nums[0] ... nums[n - 2]) - sum(nums[n - 1])
		// if we change nums[j] to k (delta = k - nums[j]), the for all 1 <= i <= j, diff[i] decreased by delta so we need to look at the left to see if there is any diff (from original array) equals to delta (to make the new diff equals to 0)
		// and for all i > j diff[i] increased by delta so we need to look at the right to see if there is any diff equals to -delta
		for (int j = 0; j < n; j++) {
			if (j > 0) { // diff[0] is not a valid partition
				left.put(diff[j], left.getOrDefault(diff[j], 0) + 1);
				right.put(diff[j], right.getOrDefault(diff[j], 0) - 1);
			}
			int count = 0;
			long delta = k - nums[j];
			count += left.getOrDefault(delta, 0);
			count += right.getOrDefault(-delta, 0);
			res = Math.max(res, count);
		}
		return res;
	}
}
