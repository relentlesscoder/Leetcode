package org.wshuai.leetcode;

/**
 * Created by Wei on 12/09/2019.
 * #1283 https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
 */
public class FindTheSmallestDivisorGivenAThreshold {

	// time O(n * log(max)), space O(1)
	public int smallestDivisor(int[] nums, int threshold) {
		int low = 1, high = 1;
		for (int num : nums) {
			high = Math.max(high, num);
		}
		while (low < high) {
			int mid = low + (high - low) / 2, sum = 0;
			for (int num : nums) {
				sum += (num + mid - 1) / mid;
			}
			if (sum <= threshold) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

}
