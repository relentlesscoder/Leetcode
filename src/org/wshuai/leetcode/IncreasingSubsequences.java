package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 1/29/17.
 * #491 https://leetcode.com/problems/increasing-subsequences/
 */
public class IncreasingSubsequences {
	public List<List<Integer>> findSubsequences(int[] nums) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if (nums == null || nums.length < 2) {
			return list;
		}
		findSubsequencesDFS(nums, list, new ArrayList<Integer>(), 0);
		return list;
	}

	private void findSubsequencesDFS(int[] nums, List<List<Integer>> list, List<Integer> curr, int index) {
		if (curr.size() > 1) {
			list.add(new ArrayList<Integer>(curr));
		}
		List<Integer> unique = new ArrayList<Integer>();
		for (int i = index; i < nums.length; i++) {
			if (index > 0 && nums[i] < nums[index - 1]) {
				continue;
			}
			if (unique.contains(nums[i])) {
				continue;
			}
			unique.add(nums[i]);
			curr.add(nums[i]);
			findSubsequencesDFS(nums, list, curr, i + 1);
			curr.remove(curr.size() - 1);
		}
	}
}
