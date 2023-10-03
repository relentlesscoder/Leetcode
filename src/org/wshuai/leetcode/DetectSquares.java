package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/02/2023.
 * #2013 https://leetcode.com/problems/detect-squares/
 */
public class DetectSquares {

	private int[][] pointsCount;
	private List<int[]> points;

	public DetectSquares() {
		pointsCount = new int[1_001][1_001];
		points = new ArrayList<>();
	}

	// time O(1), space O(1)
	public void add(int[] point) {
		pointsCount[point[0]][point[1]]++;
		points.add(point);
	}

	// time O(n), space O(1)
	public int count(int[] point) {
		int res = 0, x1 = point[0], y1 = point[1];
		for (int[] p : points) {
			int x2 = p[0], y2 = p[1];
			if (Math.abs(x1 - x2) == 0 || Math.abs(x1 - x2) != Math.abs(y1 - y2)) {
				continue;
			}
			res += pointsCount[x1][y2] * pointsCount[x2][y1];
		}
		return res;
	}
}
