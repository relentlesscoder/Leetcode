package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 02/02/2021.
 * #1742 https://leetcode.com/problems/maximum-number-of-balls-in-a-box/
 */
public class MaximumNumberOfBallsInABox {

    // time O(n*d), space O(n)
    public int countBalls(int lowLimit, int highLimit) {
        int max = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for(int d = lowLimit; d <= highLimit; d++){
            int val = 0;
            for(int i = d; i != 0; i /= 10){
                val += i % 10;
            }
            count.put(val, count.getOrDefault(val, 0) + 1);
            max = Math.max(max, count.get(val));
        }
        return max;
    }
}
