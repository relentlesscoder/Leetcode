package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 09/28/2023.
 * #1868 https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/
 */
public class ProductOfTwoRunLengthEncodedArrays {

	// time O(m + n), space O(m + n)
	public List<List<Integer>> findRLEArrayShort(int[][] encoded1, int[][] encoded2) {
		int m = encoded1.length, n = encoded2.length, i = 0, j = 0;
		List<List<Integer>> res = new ArrayList<>();
		while (i < m && j < n) {
			int[] e1 = encoded1[i], e2 = encoded2[j];
			int min = Math.min(e1[1], e2[1]), product = e1[0] * e2[0];
			if (res.size() > 0 && res.get(res.size() - 1).get(0) == product) {
				res.get(res.size() - 1).set(1, res.get(res.size() - 1).get(1) + min);
			} else {
				res.add(Arrays.asList(product, min));
			}
			e1[1] -= min;
			e2[1] -= min;
			if (e1[1] == 0) {
				i++;
			}
			if (e2[1] == 0) {
				j++;
			}
		}
		return res;
	}

	// time O(m + n), space O(m + n)
	public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
		int m = encoded1.length, n = encoded2.length, prev = -1, count = 0, product = -1;
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0, j = 0; i < m && j < n; ) {
			int[] e1 = encoded1[i], e2 = encoded2[j];
			int min = Math.min(e1[1], e2[1]);
			product = e1[0] * e2[0];
			if (product != prev) {
				if (prev != -1) {
					res.add(Arrays.asList(new Integer[]{prev, count}));
				}
				prev = product;
				count = 0;
			}
			count += min;
			if (e1[1] < e2[1]) {
				e2[1] -= min;
				i++;
			} else if (e1[1] > e2[1]) {
				e1[1] -= min;
				j++;
			} else {
				i++;
				j++;
			}
		}
		res.add(Arrays.asList(new Integer[]{prev, count}));
		return res;
	}
}
