package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/03/2023.
 * #2448 https://leetcode.com/problems/minimum-cost-to-make-array-equal/
 */
public class MinimumCostToMakeArrayEqual {

	// time O(n * log(high - low)), space O(1)
	public long minCost(int[] nums, int[] cost) {
		int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
		for (int num : nums) {
			low = Math.min(low, num);
			high = Math.max(high, num);
		}
		long res = getCost(nums, cost, nums[0]);
		while (low < high) {
			int mid = (low + high) >> 1;
			long cost1 = getCost(nums, cost, mid), cost2 = getCost(nums, cost, mid + 1);
			res = Math.min(cost1, cost2);
			if (cost1 > cost2) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return res;
	}

	private long getCost(int[] nums, int[] cost, int target) {
		long res = 0;
		for (int i = 0; i < nums.length; i++) {
			res += 1L * Math.abs(nums[i] - target) * cost[i];
		}
		return res;
	}

	// time O(n), space O(n)
	public long minCostPrefixSum(int[] nums, int[] cost) {
		int n = nums.length;
		int[][] arr = new int[n][2];
		for (int i = 0; i < n; i++) {
			arr[i] = new int[] {nums[i], cost[i]};
		}
		Arrays.sort(arr, (a, b) -> a[0] - b[0]);

		long[] prefixSum = new long[n];
		prefixSum[0] = arr[0][1];
		for (int i = 1; i < n; i++) {
			prefixSum[i] = prefixSum[i - 1] + arr[i][1];
		}
		long totalCost = 0L;
		for (int i = 1; i < n; i++) {
			totalCost += 1L * arr[i][1] * (arr[i][0] - arr[0][0]);
		}
		long res = totalCost;
		for (int i = 1; i < n; i++) {
			int delta = arr[i][0] - arr[i - 1][0];
			totalCost += 1L * prefixSum[i - 1] * delta;
			totalCost -= 1L * (prefixSum[n - 1] - prefixSum[i - 1]) * delta;
			res = Math.min(res, totalCost);
		}
		return res;
	}
}
