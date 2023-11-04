package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/03/2023.
 * #2248 https://leetcode.com/problems/intersection-of-multiple-arrays/
 */
public class IntersectionOfMultipleArrays {

	// time O(m), space O(m)
	public List<Integer> intersection(int[][] nums) {
		List<Integer> res = new ArrayList<>();
		int[] count = new int[1_001];
		int n = nums.length;
		for (int[] arr : nums) {
			for (int num : arr) {
				++count[num];
			}
		}
		for (int i = 1; i <= 1_000; i++) {
			if (count[i] == n) {
				res.add(i);
			}
		}
		return res;
	}
}
