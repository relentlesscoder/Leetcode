package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2023.
 * #1760 https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/
 */
public class MinimumLimitOfBallsInABag {

	// time O(n * log(max)), space O(1)
	public int minimumSize(int[] nums, int maxOperations) {
		int n = nums.length, low = 1, high = 0;
		for (int num : nums) {
			high = Math.max(high, num);
		}
		while (low < high) {
			int mid = low + (high - low) / 2, operationsNeeded = 0;
			for (int num : nums) {
				operationsNeeded += (num - 1) / mid;
				if (operationsNeeded > maxOperations) {
					break;
				}
			}
			if (operationsNeeded <= maxOperations) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}
}
