package org.wshuai.leetcode;

/**
 * Created by Wei on 06/06/2020.
 * #1453 https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/
 */
public class MaximumNumberOfDartsInsideOfACircularDartboard {

	// time O(n^3)
	// https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/discuss/636332/cpp-O(N3)-solution-with-pictures.
	public int numPoints(int[][] points, int r) {
		int res = 1, n = points.length;
		for(int i = 0; i < n; i++){
			for(int j = i + 1; j < n; j++){
				int x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 = points[j][1];
				double d = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
				double d1 = Math.pow(d, 0.5);
				double d2 = Math.pow(r*r - d/4.0, 0.5);
				double sin = d2 * (y1 - y2) / d1, cos = d2 * (x2 - x1) / d1;
				double midX = (x2 + x1) / 2.0, midY = (y1 + y2) / 2.0;
				res = Math.max(res, countPoints(midX - sin, midY - cos, points, r));
				res = Math.max(res, countPoints(midX + sin, midY + cos, points, r));
			}
		}
		return res;
	}

	private int countPoints(double x, double y, int[][] points, int r){
		int count = 0;
		for(int[] p : points){
			int x1 = p[0], y1 = p[1];
			double d = Math.pow((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y), 0.5);
			if(d <= r + 0.00001){
				count++;
			}
		}
		return count;
	}
}
