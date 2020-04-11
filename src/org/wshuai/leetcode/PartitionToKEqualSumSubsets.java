package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2019.
 * #0698 https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 */
public class PartitionToKEqualSumSubsets {
	// time O(k*2^n)
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0, n = nums.length;
		for (int num : nums) {
			sum += num;
		}
		if (sum % k != 0) {
			return false;
		}
		int target = sum / k;
		return dfs(0, k, 0, target, nums, new boolean[n]);
	}

	private boolean dfs(int start, int k, int sum, int target, int[] nums, boolean[] used) {
		if (sum == 0 && k == 1) {
			return true;
		}
		if (start == nums.length) {
			return false;
		}
		if (sum > target) {
			return false;
		}
		if (sum == target) {
			return dfs(0, k - 1, 0, target, nums, used);
		}
		for (int i = start; i < nums.length; i++) {
			if (used[i]) {
				continue;
			}
			used[i] = true;
			if (dfs(i + 1, k, sum + nums[i], target, nums, used)) {
				return true;
			}
			used[i] = false;
		}
		return false;
	}
}
