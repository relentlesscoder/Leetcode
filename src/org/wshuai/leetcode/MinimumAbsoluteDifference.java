package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 9/29/2019.
 * #1200 https://leetcode.com/problems/minimum-absolute-difference/
 */
public class MinimumAbsoluteDifference {
	public List<List<Integer>> minimumAbsDifference(int[] arr) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(arr);
		int minDiff = Integer.MAX_VALUE;
		for (int i = 1; i < arr.length; i++) {
			int diff = arr[i] - arr[i - 1];
			minDiff = Math.min(minDiff, diff);
		}
		for (int i = 1; i < arr.length; i++) {
			int diff = arr[i] - arr[i - 1];
			if (diff == minDiff) {
				List<Integer> lst = new ArrayList<>();
				lst.add(arr[i - 1]);
				lst.add(arr[i]);
				res.add(lst);
			}
		}
		return res;
	}
}
