package org.wshuai.leetcode;

/**
 * Created by Wei on 10/09/2019.
 * #0812 https://leetcode.com/problems/largest-triangle-area/
 */
public class LargestTriangleArea {

	// time O(n^3)
	// https://en.wikipedia.org/wiki/Shoelace_formula
	public double largestTriangleArea(int[][] points) {
		int N = points.length;
		double ans = 0;
		for(int i = 0; i < N; i++){
			for(int j = i + 1; j < N; j++){
				for(int k = j + 1; k < N; k++){
					ans = Math.max(ans, area(points[i], points[j], points[k]));
				}
			}
		}
		return ans;
	}

	private double area(int[] p, int[] q, int[] r){
		return 0.5 * Math.abs(p[0]*q[1] + q[0]*r[1] + r[0]*p[1]
				- p[1]*q[0] - q[1]*r[0] - r[1]*p[0]);
	}
}
