package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/13/2016.
 * #0452 https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 */
public class MinimumNumberOfArrowsToBurstBalloons {

	// time O(n*log(n))
	public int findMinArrowShots(int[][] points) {
		if(points == null || points.length == 0){
			return 0;
		}
		Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
		int n = points.length, res = 1, end = points[0][1];
		for(int i = 1; i < n; i++){
			if(Integer.compare(points[i][0], end) > 0){
				res++;
				end = points[i][1];
			}
		}
		return res;
	}
}
