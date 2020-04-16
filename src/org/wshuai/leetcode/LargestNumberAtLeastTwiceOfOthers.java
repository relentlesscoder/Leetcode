package org.wshuai.leetcode;

/**
 * Created by Wei on 08/30/2019.
 * #0747 https://leetcode.com/problems/largest-number-at-least-twice-of-others/
 */
public class LargestNumberAtLeastTwiceOfOthers {
	// time O(n)
	public int dominantIndex(int[] nums) {
		int n = nums.length, res = 0, max = nums[0];
		for (int i = 1; i < n; i++) {
			if (nums[i] > max) {
				max = nums[i];
				res = i;
			}
		}
		for (int i = 0; i < n; i++) {
			if (i == res) {
				continue;
			}
			if (max < (nums[i] << 1)) {
				return -1;
			}
		}
		return res;
	}
}
