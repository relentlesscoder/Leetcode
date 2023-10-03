package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/02/2023.
 * #2570 https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/
 */
public class MergeTwo2DArraysBySummingValues {

	// time O(m + n), space O(m + n)
	public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
		List<int[]> res = new ArrayList<>();
		int m = nums1.length, n = nums2.length;
		int i = 0, j = 0;
		while (i < m || j < n) {
			int[] curr;
			if (i == m) {
				curr = nums2[j++];
			} else if (j == n) {
				curr = nums1[i++];
			} else if (nums1[i][0] < nums2[j][0]) {
				curr = nums1[i++];
			} else {
				curr = nums2[j++];
			}
			if (res.size() > 0 && curr[0] == res.get(res.size() - 1)[0]) {
				res.get(res.size() - 1)[1] += curr[1];
			} else {
				res.add(new int[]{curr[0], curr[1]});
			}
		}
		return res.toArray(new int[0][]);
	}
}
