package org.wshuai.leetcode;

/**
 * Created by Wei on 10/1/2019.
 * #1011 https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 */
public class CapacityToShipPackagesWithinDDays {

	// see explanation at https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/discuss/256765/Python-Binary-search-with-detailed-explanation
	public int shipWithinDays(int[] weights, int D) {
		int left = 0, right = 0;
		// lower bound is max of weights - ship needs to be
		// able to ship all the packages
		// upper bound is sum of weights -> ship all in 1 day
		for(int w: weights){
			left = Math.max(left, w);
			right += w;
		}
		while(left < right){
			int mid = (left + right) / 2;
			if(countDays(weights, mid) > D){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}

	private int countDays(int[] weights, int capacity){
		int days = 1, curr = 0;
		for(int w: weights){
			// the same ship can't load more at current day
			if(curr + w > capacity){
				// need one more days
				days += 1;
				// reset the current weight to 0
				curr = 0;
			}
			curr += w;
		}
		return days;
	}
}
