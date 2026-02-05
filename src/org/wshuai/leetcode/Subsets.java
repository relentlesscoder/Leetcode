package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/04/2016.
 * #0078 https://leetcode.com/problems/subsets/
 */
public class Subsets {

	// time O(n * 2^n), space O(1)
	public List<List<Integer>> subsetsBitMask(int[] nums) {
		// 根据二进制掩码 [0, 2^n - 1] 中 1 的比特位决定选不选数组中对应位置的数
		int n = nums.length, m = 1 << n;
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < m; i++) { // O(2^n)
			List<Integer> curr = new ArrayList<>();
			for (int j = 0; j < n; j++) { // O(n)
				if ((i & (1 << j)) > 0) {
					curr.add(nums[j]);
				}
			}
			res.add(curr);
		}
		return res;
	}

	// time O(n * 2^n), space O(n)
	public List<List<Integer>> subsetsBackTracking(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(0, nums, new ArrayList<>(), res);
		return res;
	}

	private void dfs(int i, int[] nums, List<Integer> path, List<List<Integer>> res) {
		res.add(new ArrayList<>(path));
		for (int j = i; j < nums.length; j++) {
			path.add(nums[j]);
			dfs(j + 1, nums, path, res);
			// 回溯完复原现场
			path.remove(path.size() - 1);
		}
	}
}
