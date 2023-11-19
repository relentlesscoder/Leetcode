package org.wshuai.leetcode;

/**
 * Created by Wei on 11/03/2023.
 * #2079 https://leetcode.com/problems/watering-plants/
 */
public class WateringPlants {

	// time O(n), space O(1)
	public int wateringPlants(int[] plants, int capacity) {
		int res = 0, n = plants.length, curr = capacity;
		for (int i = 0; i < n; i++) {
			// if not enough capacity, we have to return to the river and come back
			// note that:
			//     1. here we only count the extra cost for the round trip
			//     2. the position we return back from is i - 1 not i so extra steps it takes is 2 * i
			if (curr < plants[i]) {
				res += (i << 1);
				curr = capacity;
			}
			curr -= plants[i];
			res++; // add regular step to the next plant
		}
		return res;
	}
}
