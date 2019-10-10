package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/11/2016.
 * #46 https://leetcode.com/problems/permutations/
 */
public class Permutations {

	//DFS
	public List<List<Integer>> permuteDFS(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) {
			return result;
		}

		int len = nums.length;
		boolean[] used = new boolean[len];
		List<Integer> lst = new ArrayList<Integer>();
		permuteHelper(nums, lst, used, result, len);
		return result;
	}

	private void permuteHelper(int[] nums, List<Integer> lst, boolean[] used, List<List<Integer>> result, int len) {
		if (lst.size() == len) {
			List<Integer> temp = new ArrayList<Integer>(lst);
			result.add(temp);
		} else {
			for (int i = 0; i < len; i++) {
				if (used[i]) {
					continue;
				}
				used[i] = true;
				lst.add(nums[i]);
				permuteHelper(nums, lst, used, result, len);
				lst.remove(lst.size() - 1);
				used[i] = false;
			}
		}
	}

	//10ms
	public List<List<Integer>> permuteIterative(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (nums == null || nums.length == 0) {
			return result;
		}

		int len = nums.length;
		int count = 1;

		for (int i = 0; i < len; i++) {
			List<Integer> lst = new ArrayList<Integer>();
			lst.add(nums[i]);
			result.add(lst);
		}

		while (count < len) {
			int size = result.size();
			List<List<Integer>> temp = new ArrayList<List<Integer>>();
			for (int i = 0; i < size; i++) {
				List<Integer> lst = result.get(i);
				for (int j = 0; j < len; j++) {
					if (!lst.contains(nums[j])) {
						List<Integer> tlst = new ArrayList<Integer>(lst);
						tlst.add(nums[j]);
						temp.add(tlst);
					}
				}
			}
			result = temp;
			count++;
		}

		return result;
	}

	//9ms backtracking
	public List<List<Integer>> permuteRecursive(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (nums == null || nums.length == 0) {
			return result;
		}

		int len = nums.length;
		return permuteUtil(0, nums, len, result);
	}

	private List<List<Integer>> permuteUtil(int count, int[] nums, int len, List<List<Integer>> result) {
		if (count == len) {
			return result;
		} else {
			if (count == 0) {
				for (int i = 0; i < len; i++) {
					List<Integer> lst = new ArrayList<Integer>();
					lst.add(nums[i]);
					result.add(lst);
				}
			} else {
				List<List<Integer>> temp = new ArrayList<List<Integer>>();
				int rLen = result.size();
				for (int i = 0; i < rLen; i++) {
					List<Integer> lst = result.get(i);
					for (int j = 0; j < len; j++) {
						if (!lst.contains(nums[j])) {
							List<Integer> lstTemp = new ArrayList<Integer>(lst);
							lstTemp.add(nums[j]);
							temp.add(lstTemp);
						}
					}
				}
				result = temp;
			}
			return permuteUtil(count + 1, nums, len, result);
		}
	}
}
