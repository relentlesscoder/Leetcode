package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/08/2023.
 * #2418 https://leetcode.com/problems/sort-the-people/
 */
public class SortThePeople {

	// time O(n * log(n)), space O(n)
	public String[] sortPeople(String[] names, int[] heights) {
		int n = names.length;
		Integer[] sorted = new Integer[n];
		for (int i = 0; i < n; i++) {
			sorted[i] = i;
		}
		Arrays.sort(sorted, (a, b) -> heights[b] - heights[a]);
		String[] res = new String[n];
		for (int i = 0; i < n; i++) {
			res[i] = names[sorted[i]];
		}
		return res;
	}
}
