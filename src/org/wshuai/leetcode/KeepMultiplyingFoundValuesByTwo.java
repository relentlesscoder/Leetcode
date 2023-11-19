package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/19/2023.
 * #2154 https://leetcode.com/problems/keep-multiplying-found-values-by-two/
 */
public class KeepMultiplyingFoundValuesByTwo {

	// time O(n), space O(n)
	public int findFinalValue(int[] nums, int original) {
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}
		while (set.contains(original)) {
			original <<= 1;
		}
		return original;
	}

	// time O(n * log(n)), space O(1)
	public int findFinalValueSorting(int[] nums, int original) {
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == original) {
				original <<= 1;
			}
		}
		return original;
	}
}
