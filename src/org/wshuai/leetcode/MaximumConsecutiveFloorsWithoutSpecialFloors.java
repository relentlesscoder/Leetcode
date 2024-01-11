package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/11/2024.
 * #2274 https://leetcode.com/problems/maximum-consecutive-floors-without-special-floors/
 */
public class MaximumConsecutiveFloorsWithoutSpecialFloors {

    // time O(n * log(n)), space O(log(n))
    public int maxConsecutive(int bottom, int top, int[] special) {
        int res = 0, prev = bottom - 1;
        Arrays.sort(special);
        for (int i = 0; i < special.length; i++) {
            res = Math.max(res, special[i] - prev - 1);
            prev = special[i];
        }
        res = Math.max(res, top - prev);
        return res;
    }
}
