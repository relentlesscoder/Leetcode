package org.wshuai.leetcode;

/**
 * Created by Wei on 08/21/2019.
 * #0849 https://leetcode.com/problems/maximize-distance-to-closest-person/
 */
public class MaximizeDistanceToClosestPerson {
	// time O(n)
	public int maxDistToClosest(int[] seats) {
		// 1. sits at the middle of two consecutive 1s
		// 2. check base case for two sides
		int res = 0, prev = -1, n = seats.length;
		for(int i = 0; i < n; i++){
			if(seats[i] == 0){
				continue;
			}
			res = prev == -1 ? i : Math.max(res, (i - prev) / 2);
			prev = i;
		}
		return Math.max(res, n - 1 - prev);
	}
}
