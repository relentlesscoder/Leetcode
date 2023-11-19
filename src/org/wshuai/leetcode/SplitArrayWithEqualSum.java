package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/05/2019.
 * #0548 https://leetcode.com/problems/split-array-with-equal-sum/
 */
public class SplitArrayWithEqualSum {

	// time O(n^3), space O(n)
	public boolean splitArray(int[] nums) {
		int n = nums.length;
		if (n < 7) {
			return false;
		}
		int[] prefix = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			prefix[i] = prefix[i - 1] + nums[i - 1];
		}
		for (int i = 1; i < n - 5; i++) {
			if (i > 1 && nums[i - 1] == 0 && nums[i] == 0) {
				continue;
			}
			int sum = prefix[i];
			if (dfs(sum, i + 1, 1, prefix, n)) {
				return true;
			}
		}
		return false;
	}

	private boolean dfs(int target, int start, int count, int[] prefix, int n) {
		if (count == 3) {
			return prefix[n] - prefix[start] == target;
		}
		for (int i = start + 1; i < n - 5 + count * 2; i++) {
			int sum = prefix[i] - prefix[start];
			if (sum != target) {
				continue;
			}
			if (dfs(target, i + 1, count + 1, prefix, n)) {
				return true;
			}
		}
		return false;
	}

	// time O(n^2), space O(n^2)
	public boolean splitArrayHashSet(int[] nums) {
		if (nums.length < 7) {
			return false;
		}
		int n = nums.length;
		int[] prefix = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			prefix[i] = prefix[i - 1] + nums[i - 1];
		}
		// middle partition point
		for (int j = 3; j < n - 3; j++) {
			if (j != 3 && nums[j] == 0 && nums[j - 1] == 0) {
				continue;
			}
			Set<Integer> set = new HashSet<>();
			// left partition point
			for (int i = 1; i < j - 1; i++) {
				if (prefix[i] == prefix[j] - prefix[i + 1]) {
					set.add(prefix[i]);
				}
			}
			// right partition point
			for (int k = j + 2; k < n - 1; k++) {
				int temp = prefix[k] - prefix[j + 1];
				if (set.contains(temp) && prefix[n] - prefix[k + 1] == temp) {
					return true;
				}
			}
		}
		return false;
	}
}
