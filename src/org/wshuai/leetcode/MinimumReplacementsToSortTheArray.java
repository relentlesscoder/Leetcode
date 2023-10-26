package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2023.
 * #2366 https://leetcode.com/problems/minimum-replacements-to-sort-the-array/
 */
public class MinimumReplacementsToSortTheArray {

	// time O(n), space O(1)
	public long minimumReplacement(int[] nums) {
		long res = 0;
		int n = nums.length;
		for (int i = n - 2; i >= 0; i--) {
			if (nums[i] > nums[i + 1]) {
				long count = (long)(nums[i] + nums[i + 1] - 1) / nums[i + 1];
				res += count - 1;
				nums[i] /= (int) count;
			}
		}
		return res;
	}
}
