package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/29/2023.
 * #2670 https://leetcode.com/problems/find-the-distinct-difference-array/
 */
public class FindTheDistinctDifferenceArray {

	// time O(n), space O(n)
	public int[] distinctDifferenceArray(int[] nums) {
		int n = nums.length, right = 0;
		int[] res = new int[n], map = new int[51];
		res[0] = 1;
		map[nums[0]] = 1;
		for (int i = 1; i < n; i++) {
			if (map[nums[i]]++ == 0) {
				res[i] = res[i - 1] + 1;
			} else {
				res[i] = res[i - 1];
			}
		}
		Arrays.fill(map, 0);
		for (int i = n - 1; i >= 0; i--) {
			res[i] -= right;
			right += (map[nums[i]]++ == 0 ? 1 : 0);
		}
		return res;
	}
}
