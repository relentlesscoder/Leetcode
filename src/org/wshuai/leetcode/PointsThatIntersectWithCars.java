package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 11/22/2023.
 * #2848 https://leetcode.com/problems/points-that-intersect-with-cars/
 */
public class PointsThatIntersectWithCars {

    // time O(n), space O(n)
    public int numberOfPoints(List<List<Integer>> nums) {
        int[] line = new int[102];
        for (List<Integer> car : nums) {
            line[car.get(0)]++;
            line[car.get(1) + 1]--;
        }
        int res = 0, sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += line[i];
            if (sum > 0) {
                res++;
            }
        }
        return res;
    }
}
