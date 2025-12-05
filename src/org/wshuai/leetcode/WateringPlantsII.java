package org.wshuai.leetcode;

/**
 * Created by Wei on 12/03/2025.
 * #2105 https://leetcode.com/problems/watering-plants-ii/
 */
public class WateringPlantsII {

    // time O(n), space O(1)
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int res = 0, n = plants.length, waterA = capacityA, waterB = capacityB;
        for (int a = 0, b = n - 1; a <= b; a++, b--) {
            if (a == b) {
                if (waterA >= waterB) {
                    // Alice
                    if (waterA < plants[a]) {
                        res++;
                        waterA = capacityA;
                    }
                    waterA -= plants[a];
                } else {
                    // Bob
                    if (waterB < plants[b]) {
                        res++;
                        waterB = capacityB;
                    }
                    waterB -= plants[b];
                }
            } else {
                // Alice
                if (waterA < plants[a]) {
                    res++;
                    waterA = capacityA;
                }
                waterA -= plants[a];

                // Bob
                if (waterB < plants[b]) {
                    res++;
                    waterB = capacityB;
                }
                waterB -= plants[b];
            }
        }
        return res;
    }
}
