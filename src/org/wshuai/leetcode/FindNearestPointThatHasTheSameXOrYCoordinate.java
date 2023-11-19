package org.wshuai.leetcode;

/**
 * Created by Wei on 03/06/2021.
 * #1779 https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/
 */
public class FindNearestPointThatHasTheSameXOrYCoordinate {

	// time O(n)
	public int nearestValidPoint(int x, int y, int[][] points) {
		int res = -1, diff = Integer.MAX_VALUE;
		for(int i = 0; i < points.length; i++){
			if(points[i][0] == x && points[i][1] == y){
				return i;
			}
			if(points[i][0] != x && points[i][1] != y){
				continue;
			}
			int cur = points[i][0] == x ?
				Math.abs(points[i][1] - y) : Math.abs(points[i][0] - x);
			if(cur < diff){
				diff = cur;
				res = i;
			}
		}
		return res;
	}
}
