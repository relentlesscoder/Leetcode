package org.wshuai.leetcode;

/**
 * Created by Wei on 9/17/19.
 * #1184 https://leetcode.com/problems/distance-between-bus-stops/
 */
public class DistanceBetweenBusStops {
	public int distanceBetweenBusStops(int[] distance, int start, int destination) {
		int clockwise = 0;
		int sum = 0;
		int s = Math.min(start, destination);
		int e = Math.max(start, destination);
		for (int i = 0; i < distance.length; i++) {
			sum += distance[i];
			if (i >= s && i < e) {
				clockwise += distance[i];
			}
		}
		return Math.min(sum - clockwise, clockwise);
	}
}
