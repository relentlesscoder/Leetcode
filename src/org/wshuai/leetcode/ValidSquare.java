package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/11/2019.
 * #593 https://leetcode.com/problems/valid-square/
 */
public class ValidSquare {

	// https://leetcode.com/problems/valid-square/solution/
	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
		int[][] p = new int[][]{
				p1, p2, p3, p4
		};
		Arrays.sort(p, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		return dist(p[0], p[1]) != 0
				&& dist(p[0], p[1]) == dist(p[1], p[3])
				&& dist(p[1], p[3]) == dist(p[3], p[2])
				&& dist(p[3], p[2]) == dist(p[2], p[0])
				&& dist(p[0],p[3]) == dist(p[1],p[2]);
	}

	private double dist(int[] p1, int[] p2){
		return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
	}
}
