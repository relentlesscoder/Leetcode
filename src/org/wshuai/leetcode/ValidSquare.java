package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/11/2019.
 * #0593 https://leetcode.com/problems/valid-square/
 */
public class ValidSquare {

	// time O(n*log(n))
	// https://leetcode.com/problems/valid-square/solution/
	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
		int[][] points = new int[][]{
				p1, p2, p3, p4
		};
		Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		return dist(points[0], points[1]) > 0 &&
				dist(points[0], points[1]) == dist(points[1], points[3]) &&
				dist(points[1], points[3]) == dist(points[3], points[2]) &&
				dist(points[3], points[2]) == dist(points[2], points[0]) &&
				dist(points[0], points[3]) == dist(points[1], points[2]);
	}

	private int dist(int[] p1, int[] p2){
		int x = p1[0] - p2[0], y = p1[1] - p2[1];
		return x*x + y*y;
	}
}
