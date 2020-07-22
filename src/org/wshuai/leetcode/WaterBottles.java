package org.wshuai.leetcode;

/**
 * Created by Wei on 07/22/2020.
 * #1518 https://leetcode.com/problems/water-bottles/
 */
public class WaterBottles {

    // time O(log(n))
    public int numWaterBottles(int numBottles, int numExchange) {
        int res = 0, full = numBottles, empty = 0;
        while((full + empty) >= numExchange){
            res += full;
            empty += full;
            full = empty / numExchange;
            empty = empty % numExchange;
        }
        return res + full;
    }
}
