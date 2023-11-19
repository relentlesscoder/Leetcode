package org.wshuai.leetcode;

/**
 * Created by Wei on 11/03/2023.
 * #2643 https://leetcode.com/problems/row-with-maximum-ones/
 */
public class RowWithMaximumOnes {

	// time O(m * n), space O(1)
	public int[] rowAndMaximumOnes(int[][] mat) {
		int m = mat.length;
		int[] res = new int[] {0, 0};
		for (int i = 0; i < m; i++) {
			int count = 0;
			for (int v : mat[i]) {
				count += v;
			}
			if (count > res[1]) {
				res[1] = count;
				res[0] = i;
			}
		}
		return res;
	}
}
