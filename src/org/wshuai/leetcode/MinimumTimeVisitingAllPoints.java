package org.wshuai.leetcode;

/**
 * Created by Wei on 11/25/19.
 * #1266 https://leetcode.com/problems/minimum-time-visiting-all-points/
 */
public class MinimumTimeVisitingAllPoints {
	public int minTimeToVisitAllPoints(int[][] points) {
		int res = 0;
		for(int i = 1; i < points.length; i++){
			res += distance(points[i], points[i - 1]);
		}
		return res;
	}

	private int distance(int[] p1, int[] p0){
		return Math.max(Math.abs(p1[0] - p0[0]), Math.abs(p1[1] - p0[1]));
	}
}
