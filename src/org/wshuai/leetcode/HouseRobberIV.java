package org.wshuai.leetcode;

/**
 * Created by Wei on 09/22/2023.
 * #2560 https://leetcode.com/problems/house-robber-iv/
 */
public class HouseRobberIV {

	// time O(n * log(high)), space O(1)
	public int minCapability(int[] nums, int k) {
		int low = 0, high = 0;
		for (int num : nums) {
			high = Math.max(high, num);
		}
		while (low < high) {
			int mid = low + (high - low) / 2, count = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] <= mid) {
					if (++count == k) {
						break;
					}
					i++;
				}
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
