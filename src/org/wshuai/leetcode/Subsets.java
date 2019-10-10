package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/4/16.
 * #78 https://leetcode.com/problems/subsets/
 */
public class Subsets {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> lst = new ArrayList<List<Integer>>();
		lst.add(new ArrayList<Integer>());
		Arrays.sort(nums);
		getAllSubsets(nums, 0, lst, new ArrayList<Integer>());
		return lst;
	}

	private void getAllSubsets(int[] nums, int idx, List<List<Integer>> lst, List<Integer> last) {
		int len = nums.length;
		if (idx == len) {
			return;
		}
		for (int i = idx; i < len; i++) {
			last.add(nums[i]);
			lst.add(new ArrayList<Integer>(last));
			getAllSubsets(nums, i + 1, lst, last);
			last.remove(last.size() - 1);
		}
	}
}
