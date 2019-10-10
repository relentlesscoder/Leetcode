package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #216 https://leetcode.com/problems/combination-sum-iii/
 */
public class CombinationSumIII {
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> lst = new ArrayList<List<Integer>>();
		combinationSum3DFS(1, k, n, lst, new ArrayList<Integer>(), 0);
		return lst;
	}

	private void combinationSum3DFS(int start, int k, int n, List<List<Integer>> lst, List<Integer> curr, int sum) {
		int size = curr.size();
		if (size > k) {
			return;
		}
		if (sum > n) {
			return;
		}
		if (size == k) {
			if (sum == n) {
				List<Integer> x = new ArrayList<Integer>(curr);
				lst.add(x);
			}
			return;
		} else {
			for (int i = start; i < 10; i++) {
				int nsum = sum;
				nsum += i;
				curr.add(i);
				combinationSum3DFS(i + 1, k, n, lst, curr, nsum);
				curr.remove(curr.size() - 1);
			}
		}
	}
}
