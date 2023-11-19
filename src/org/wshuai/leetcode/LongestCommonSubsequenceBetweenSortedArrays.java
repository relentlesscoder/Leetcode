package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/06/2023.
 * #1940 https://leetcode.com/problems/longest-common-subsequence-between-sorted-arrays/
 */
public class LongestCommonSubsequenceBetweenSortedArrays {

	// time O(n), space O(1)
	public List<Integer> longestCommonSubsequence(int[][] arrays) {
		List<Integer> res = new ArrayList<>();
		int n = arrays.length;
		int[] counter = new int[101];
		for (int[] arr : arrays) {
			for (int num : arr) {
				counter[num]++;
			}
		}
		for (int i = 1; i <= 100; i++) {
			if (counter[i] == n) {
				res.add(i);
			}
		}
		return res;
	}

	// time O(m * log(n)), space O(m * log(n))
	public List<Integer> longestCommonSubsequenceDivideAndConquer(int[][] arrays) {
		return lcs(arrays, 0, arrays.length - 1);
	}

	private List<Integer> lcs(int[][] arrays, int left, int right) {
		List<Integer> res = new ArrayList<>();
		if (left == right) {
			for (int num : arrays[left]) {
				res.add(num);
			}
			return res;
		}
		int mid = left + (right - left) / 2;
		return merge(lcs(arrays, left, mid), lcs(arrays, mid + 1, right));
	}

	private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
			int num1 = list1.get(i), num2 = list2.get(j);
			if (num1 < num2) {
				i++;
			} else if (num2 < num1) {
				j++;
			} else {
				res.add(num1);
				i++;
				j++;
			}
		}
		return res;
	}
}
