package org.wshuai.leetcode;

/**
 * Created by Wei on 11/03/2023.
 * #2079 https://leetcode.com/problems/watering-plants/
 */
public class WateringPlants {

    // time O(n), space O(1)
    public int wateringPlants(int[] plants, int capacity) {
        int res = 0, n = plants.length, water = capacity;
        for (int i = 0; i < n; i++) {
            // If not enough capacity, we have to return to the river and
            // come back.
            // Note that:
            //     1. We only count the extra cost for the round trip
            //     2. The position we return back from is i - 1 not i
            //        so extra steps it takes is 2 * i
            if (water < plants[i]) {
                res += (i << 1);
                water = capacity;
            }
            res++; // Step to the next plant
            water -= plants[i];
        }
        return res;
    }
}
