package org.wshuai.leetcode;

/**
 * Created by Wei on 9/15/19.
 * #1007 https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
 */
public class MinimumDominoRotationsForEqualRow {
	public int minDominoRotations(int[] A, int[] B) {
		int res1 = rotate(A[0], A, B);
		int res2 = rotate(B[0], A, B);
		int min = Math.min(res1, res2);
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	private int rotate(int val, int[] A, int[] B) {
		int ra = 0;
		int rb = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] != val && B[i] != val) {
				return Integer.MAX_VALUE;
			} else if (A[i] != val) {
				ra++;
			} else if (B[i] != val) {
				rb++;
			}
		}
		return Math.min(ra, rb);
	}
}
