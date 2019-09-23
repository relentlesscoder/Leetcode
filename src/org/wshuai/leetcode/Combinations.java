package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #77 https://leetcode.com/problems/combinations/
 */
public class Combinations {
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> lst = new ArrayList<List<Integer>>();
		combineDFS(1, n, k, lst, new ArrayList<Integer>());
		return lst;
	}

	private void combineDFS(int start, int n, int k, List<List<Integer>> lst, List<Integer> curr) {
		int size = curr.size();
		if (size > k) {
			return;
		}
		if (size == k) {
			List<Integer> res = new ArrayList<Integer>(curr);
			lst.add(res);
			return;
		} else {
			for (int i = start; i <= n; i++) {
				curr.add(i);
				combineDFS(i + 1, n, k, lst, curr);
				curr.remove(curr.size() - 1);
			}
		}
	}
}
