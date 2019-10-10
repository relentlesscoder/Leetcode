package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #39 https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> lst = new ArrayList<List<Integer>>();
		if (candidates == null || candidates.length == 0) {
			return lst;
		}

		Arrays.sort(candidates);
		int len = candidates.length;
		getCombinationSum(candidates, target, lst, len, 0, new ArrayList<Integer>(), 0);
		return lst;
	}

	private void getCombinationSum(int[] cans, int target, List<List<Integer>> lst, int len, int sum, List<Integer> vals, int start) {
		if (sum > target) {
			return;
		}
		if (sum == target) {
			lst.add(vals);
			return;
		}
		int newSum = 0;
		for (int i = start; i < len; i++) {
			int x = cans[i];
			List<Integer> last = new ArrayList<Integer>(vals);
			newSum = sum;
			newSum += x;
			last.add(x);
			getCombinationSum(cans, target, lst, len, newSum, last, i);
		}
	}
}
