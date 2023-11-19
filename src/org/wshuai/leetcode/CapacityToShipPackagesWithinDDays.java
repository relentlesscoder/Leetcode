package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2019.
 * #1011 https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 */
public class CapacityToShipPackagesWithinDDays {

	// time O(log(sum)), space O(1)
	public int shipWithinDays(int[] weights, int days) {
		int low = 0, high = 0;
		for (int weight : weights) {
			low = Math.max(low, weight); // lower bound is max of weights - ship needs to be able to ship any package
			high += weight; // upper bound is sum of weights -> ship all in 1 day
		}
		while (low < high) {
			int mid = (low + high) >> 1;
			if (canShip(weights, days, mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	private boolean canShip(int[] weights, int days, int threshold) {
		int daysNeeded = 0, sum = 0;
		for (int weight : weights) {
			if (sum + weight > threshold) {
				sum = 0;
				if (++daysNeeded >= days) {
					return false;
				}
			}
			sum += weight;
		}
		return true;
	}
}
