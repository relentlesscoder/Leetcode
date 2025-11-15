package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/15/2025.
 * #2001 https://leetcode.com/problems/number-of-pairs-of-interchangeable-rectangles/
 */
public class NumberOfPairsOfInterchangeableRectangles {

    // time O(n), space O(n)
    public long interchangeableRectangles(int[][] rectangles) {
        long res = 0;
        Map<Double, Integer> map = new HashMap<>();
        for (int[] rect : rectangles) {
            double ratio = 1.0 * rect[0] / rect[1];
            int count = map.getOrDefault(ratio, 0);
            res += count;
            map.put(ratio, count + 1);
        }
        return res;
    }
}
