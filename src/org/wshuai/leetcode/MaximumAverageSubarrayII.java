package org.wshuai.leetcode;

/**
 * Created by Wei on 12/12/2019.
 * #0644 https://leetcode.com/problems/maximum-average-subarray-ii/
 */
public class MaximumAverageSubarrayII {

    // time O(n * log(n)), space O(1)
	public double findMaxAverage(int[] nums, int k) {
		double max = -Double.MAX_VALUE, min = Double.MAX_VALUE, mid = 0;
		for (int num : nums) {
			max = Math.max(max, num);
			min = Math.min(min, num);
		}
		while (max - min > 1e-5) {
			mid = (max + min) / 2;
			if (isTooBig(nums, k, mid)) {
				max = mid;
			} else {
				min = mid;
			}
		}
		return min;
	}

	private boolean isTooBig(int[] nums, int k, double mid) {
		// sum: the sum from nums[0] to nums[i];
		// prefixSum: the sum from nums[0] to nums[i - k];
		// minPrefixSum: the minimal sum from nums[0] to nums[j] (0 <= j <= i-k)
		double sum = 0, prefixSum = 0, minPrefixSum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i] - mid;
			if (i >= k) {
				prefixSum += nums[i - k] - mid;
				minPrefixSum = Math.min(prefixSum, minPrefixSum);
			}
			// Return false if there exists some subarray that have larger average value
			if (i >= k - 1 && sum > minPrefixSum) {
				return false;
			}
		}
		return true;
	}
}
