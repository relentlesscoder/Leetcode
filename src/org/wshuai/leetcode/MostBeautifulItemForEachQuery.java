package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/22/2023.
 * #2070 https://leetcode.com/problems/most-beautiful-item-for-each-query
 */
public class MostBeautifulItemForEachQuery {

	// time O(m * log(n)), space O(m)
	public int[] maximumBeauty(int[][] items, int[] queries) {
		int[] res = new int[queries.length];
		Arrays.sort(items, (a, b) -> a[0] - b[0]);
		int max = 0;
		for (int[] item : items) {
			max = Math.max(max, item[1]);
			item[1] = max;
		}
		for (int i = 0; i < queries.length; i++) {
			res[i] = search(items, queries[i]);
		}
		return res;
	}

	private int search(int[][] items, int query) {
		if (items[0][0] > query) {
			return 0;
		}
		int res = 0;
		int left = 0, right = items.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (items[mid][0] <= query) {
				res = items[mid][1];
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		if (items[left][0] <= query) {
			res = items[left][1];
		}
		return res;
	}
}
