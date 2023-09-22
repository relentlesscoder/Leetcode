package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/21/2023.
 * #2420 https://leetcode.com/problems/find-all-good-indices/
 */
public class FindAllGoodIndices {

	// time O(n), space O(n)
	public List<Integer> goodIndices(int[] nums, int k) {
		List<Integer> res = new ArrayList<>();
		int n = nums.length, left = 1;
		int[] right = new int[n];
		right[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			if (nums[i] <= nums[i + 1]) {
				right[i] = right[i + 1] + 1;
			} else {
				right[i] = 1;
			}
		}
		for (int i = 1; i < n - 1; i++) {
			if (left >= k && right[i + 1] >= k) {
				res.add(i);
			}
			if (nums[i] <= nums[i - 1]) {
				left++;
			} else {
				left = 1;
			}
		}
		return res;
	}
}
