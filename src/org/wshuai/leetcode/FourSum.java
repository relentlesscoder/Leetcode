package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/25/2016.
 * #0018 https://leetcode.com/problems/4sum/
 */
public class FourSum {

	// time O(n^3), space O(1)
	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		int n = nums.length;
		for (int a = 0; a < n - 3; a++) {
			long va = nums[a];
			if (a > 0 && va == nums[a - 1]) {
				continue;
			}
			if (va + nums[a + 1] + nums[a + 2] + nums[a + 3] > target) {
				break;
			}
			if (va + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
				continue;
			}
			for (int b = a + 1; b < n - 2; b++) {
				long vb = nums[b];
				if (b > a + 1 && vb == nums[b - 1]) {
					continue;
				}
				if (va + vb + nums[b + 1] + nums[b + 2] > target) {
					break;
				}
				if (va + vb + nums[n - 2] + nums[n - 1] < target) {
					continue;
				}
				int c = b + 1;
				int d = n - 1;
				while (c < d) {
					long sum = va + vb + nums[c] + nums[d];
					if (sum > target) {
						d--;
					} else if (sum < target) {
						c++;
					} else {
						res.add(List.of((int) va, (int) vb, nums[c], nums[d]));
						for (c++; c < d && nums[c] == nums[c - 1]; c++) {
						}
						for (d--; c < d && nums[d] == nums[d + 1]; d--) {
						}
					}
				}
			}
		}
		return res;
	}
}
