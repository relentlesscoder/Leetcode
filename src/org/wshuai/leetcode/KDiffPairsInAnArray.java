package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 3/5/17.
 * #532 https://leetcode.com/problems/k-diff-pairs-in-an-array/
 */
public class KDiffPairsInAnArray {
	public int findPairs(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		Arrays.sort(nums);
		int len = nums.length;
		int cnt = 0;
		for (int i = 0; i < len - 1; i++) {
			//skip duplicate values
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			//two pointers
			int j = i + 1;
			int x = len - 1;
			while (j <= x) {
				int m = (j + x) / 2;
				int diff = nums[m] - nums[i];
				if (diff == k) {
					cnt++;
					break;
				} else if (diff > k) {
					x = m - 1;
				} else {
					j = m + 1;
				}
			}
		}
		return cnt;
	}
}
