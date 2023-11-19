package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/02/2023.
 * #2363 https://leetcode.com/problems/merge-similar-items/
 */
public class MergeSimilarItems {

	// time O(m + n), space O(m + n)
	public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
		List<List<Integer>> res = new ArrayList<>();
		int[] map = new int[1_001];
		for (int[] item : items1) {
			map[item[0]] += item[1];
		}
		for (int[] item : items2) {
			map[item[0]] += item[1];
		}
		for (int i = 1; i <= 1_000; i++) {
			if (map[i] > 0) {
				ArrayList<Integer> curr = new ArrayList<>();
				curr.add(i);
				curr.add(map[i]);
				res.add(curr);
			}
		}
		return res;
	}
}
