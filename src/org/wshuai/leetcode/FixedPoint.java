package org.wshuai.leetcode;

/**
 * Created by Wei on 8/7/19.
 * #1064 https://leetcode.com/problems/fixed-point/
 */
public class FixedPoint {
	public int fixedPoint(int[] A) {
		int l = 0;
		int r = A.length - 1;
		while (l < r) {
			int m = (l + r) / 2;
			if (A[m] == m) {
				if (m == 0 || A[m - 1] != m - 1) {
					return m;
				} else {
					r = m - 1;
				}
			} else if (A[m] < m) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return (A[l] == l ? l : -1);
	}
}
