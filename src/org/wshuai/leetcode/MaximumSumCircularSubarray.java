package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2019.
 * #918 https://leetcode.com/problems/maximum-sum-circular-subarray/
 */
public class MaximumSumCircularSubarray {

	// NB! https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/178422/One-Pass
	public int maxSubarraySumCircular(int[] A) {
		int total = 0, maxSum = -30_000, curMax = 0, minSum = 30_000, curMin = 0;
		for(int a : A){
			curMax = Math.max(curMax + a, a);
			maxSum = Math.max(maxSum, curMax);
			curMin = Math.min(curMin + a, a);
			minSum = Math.min(minSum, curMin);
			total += a;
		}
		return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
	}
}
