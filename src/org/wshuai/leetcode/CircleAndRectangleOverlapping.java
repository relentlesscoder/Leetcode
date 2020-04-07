package org.wshuai.leetcode;

/**
 * Created by Wei on 04/07/2020.
 * #1401 https://leetcode.com/problems/circle-and-rectangle-overlapping/
 */
public class CircleAndRectangleOverlapping {
	// time O(1)
	// https://leetcode.com/problems/circle-and-rectangle-overlapping/discuss/563441/JAVA-compare-distance-between-radius-and-closest-point-on-rectangle-to-circle
	// https://yal.cc/rectangle-circle-intersection-test/
	public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
		int closestX = Math.max(x1, Math.min(x_center, x2));
		int closestY = Math.max(y1, Math.min(y_center, y2));

		int distanceX = x_center - closestX;
		int distanceY = y_center - closestY;

		int distance = distanceX * distanceX + distanceY * distanceY;
		return distance <= (radius * radius);
	}
}
