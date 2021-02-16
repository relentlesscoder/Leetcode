package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2019.
 * #1011 https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 */
public class CapacityToShipPackagesWithinDDays {

	// time O(log(sum - max))
	public int shipWithinDays(int[] weights, int D) {
		// lower bound is max of weights - ship needs to be
		// able to ship all the packages
		// upper bound is sum of weights -> ship all in 1 day
		int low = 0, high = 0;
		for(int w : weights){
			low = Math.max(low, w);
			high += w;
		}
		while(low < high){
			int mid = low + (high - low) / 2;
			if(canShip(mid, D, weights)){
				high = mid;
			}else{
				low = mid + 1;
			}
		}
		return low;
	}

	private boolean canShip(int capacity, int D, int[] weights){
		int days = 1, sum = 0;
		for(int w : weights){
			if(sum + w > capacity){
				sum = 0;
				days++;
			}
			sum += w;
		}
		return days <= D;
	}
}
