package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #40 https://leetcode.com/problems/combination-sum-ii/
 */
public class CombinationSumII {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> lst = new ArrayList<List<Integer>>();
		if (candidates == null || candidates.length == 0) {
			return lst;
		}
		Arrays.sort(candidates);
		int len = candidates.length;
		boolean[] used = new boolean[len];
		List<Integer> last = new ArrayList<Integer>();
		combinationSum2DFS(candidates, target, 0, len, lst, last, 0, used);
		return lst;
	}

	private void combinationSum2DFS(int[] candidates, int target, int start, int len, List<List<Integer>> lst, List<Integer> last, int sum, boolean[] used) {
		if (sum > target) {
			return;
		}
		if (sum == target) {
			lst.add(last);
			return;
		} else {
			for (int i = start; i < len; i++) {
				//Exclude duplicate, the same usage as https://leetcode.com/problems/permutations-ii/
				if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
					continue;
				}
				int nsum = sum;
				nsum += candidates[i];
				List<Integer> curr = new ArrayList<Integer>(last);
				curr.add(candidates[i]);
				used[i] = true;
				combinationSum2DFS(candidates, target, i + 1, len, lst, curr, nsum, used);
				used[i] = false;
			}
		}
	}
}
