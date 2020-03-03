package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 01/29/2017.
 * #0491 https://leetcode.com/problems/increasing-subsequences/
 */
public class IncreasingSubsequences {
	public List<List<Integer>> findSubsequences(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums == null || nums.length < 2) {
			return res;
		}
		dfs(0, nums, new ArrayList<Integer>(), res);
		return res;
	}

	private void dfs(int start, int[] nums, List<Integer> cur, List<List<Integer>> res) {
		if (cur.size() > 1) {
			res.add(new ArrayList<Integer>(cur));
		}
		Set<Integer> used = new HashSet<Integer>();
		for (int i = start; i < nums.length; i++) {
			if(cur.size() > 0 && nums[i] < cur.get(cur.size() - 1)){
				continue;
			}
			// remove duplicate on the same level
			if (used.contains(nums[i])) {
				continue;
			}
			used.add(nums[i]);
			cur.add(nums[i]);
			dfs(i + 1, nums, cur, res);
			cur.remove(cur.size() - 1);
		}
	}
}
