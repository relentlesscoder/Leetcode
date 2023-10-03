package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/02/2023.
 * #2200 https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/
 */
public class FindAllKDistantIndicesInAnArray {

	// time O(n), space O(n)
	public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
		List<Integer> res = new ArrayList<>();
		int n = nums.length, count = 0;
		int[] map = new int[n];
		for (int i = 0; i < n; i++) {
			if (nums[i] == key) {
				count = k;
				map[i]++;
			} else if (count > 0) {
				map[i]++;
				count--;
			}
		}
		for (int i = n - 1; i >= 0; i--) {
			if (nums[i] == key) {
				count = k;
				map[i]++;
			} else if (count > 0) {
				map[i]++;
				count--;
			}
		}
		for (int i = 0; i < n; i++) {
			if (map[i] > 0) {
				res.add(i);
			}
		}
		return res;
	}
}
