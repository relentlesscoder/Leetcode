package org.wshuai.leetcode;

/**
 * Created by Wei on 09/22/2023.
 * #2226 https://leetcode.com/problems/maximum-candies-allocated-to-k-children/
 */
public class MaximumCandiesAllocatedToKChildren {

	// time O(n * log(max)), space O(1)
	public int maximumCandies(int[] candies, long k) {
		int low = 0, high = 0;
		for (int candy : candies) {
			high = Math.max(high, candy);
		}
		while (low < high) {
			int mid = (low + high + 1) >> 1; // mid = (left + right) / 2 to find first element valid, mid = (left + right + 1) / 2to find last element valid
			if (canDistribute(candies, k, mid)) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

	private boolean canDistribute(int[] candies, long k, int threshold) {
		for (int candy : candies) {
			k -= candy / threshold;
			if (k <= 0) {
				return true;
			}
		}
		return false;
	}
}
