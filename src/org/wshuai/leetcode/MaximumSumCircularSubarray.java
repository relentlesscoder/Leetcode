package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2019.
 * #918 https://leetcode.com/problems/maximum-sum-circular-subarray/
 */
public class MaximumSumCircularSubarray {

	// time O(n)
	// https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/178422/One-Pass
	public int maxSubarraySumCircular(int[] A) {
		int total = 0, maxSum = Integer.MIN_VALUE, curMax = 0, minSum = Integer.MAX_VALUE, curMin = 0;
		for(int a : A){
			curMax = Math.max(curMax + a, a);
			maxSum = Math.max(maxSum, curMax);
			curMin = Math.min(curMin + a, a);
			minSum = Math.min(minSum, curMin);
			total += a;
		}
		/**
		 *  In case that all integer are negative, based on the description, maxSum = max(A) and minSum = sum(A).
		 *  We need to return the max(A), instead of sum of am empty subarray. max(maxSum, total - minSum) = 0 in this case.
		 */
		return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
	}
}
