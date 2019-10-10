package org.wshuai.leetcode;

/**
 * Created by Wei on 8/9/19.
 * #1037 https://leetcode.com/problems/valid-boomerang/
 */
public class ValidBoomerang {
	public boolean isBoomerang(int[][] points) {
		int[] p1 = points[0];
		int[] p2 = points[1];
		int[] p3 = points[2];

		if (p1[0] == p2[0] && p1[1] == p2[1]) {
			return false;
		}
		if (p1[0] == p3[0] && p1[1] == p3[1]) {
			return false;
		}
		if (p3[0] == p2[0] && p3[1] == p2[1]) {
			return false;
		}

		double slope12 = (double) (p1[1] - p2[1]) / (p1[0] - p2[0]);
		double slope13 = (double) (p1[1] - p3[1]) / (p1[0] - p3[0]);

		return slope12 != slope13;
	}
}
