package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/01/2020.
 * #0719 https://leetcode.com/problems/find-k-th-smallest-pair-distance/
 */
public class FindKthSmallestPairDistance {

	// time O(n * log(n) + n * log(max pair difference)), space O(1)
	public int smallestDistancePair(int[] nums, int k) {
		Arrays.sort(nums);
		int n = nums.length, low = 0, high = nums[n - 1] - nums[0];
		while (low < high) {
			int mid = (low + high) >> 1, count = 0;
			for (int i = 0, j = 0; j < nums.length; j++) {
				while (nums[j] - nums[i] > mid) {
					i++;
				}
				count += j - i;
			}
			if (count >= k) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}
}
