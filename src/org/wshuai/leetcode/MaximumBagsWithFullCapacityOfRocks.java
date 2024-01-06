package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/03/2024.
 * #2279 https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks/
 */
public class MaximumBagsWithFullCapacityOfRocks {

    // time O(n * log(n)), space O(1)
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int res = 0, n = capacity.length, j = 0;
        for (int i = 0; i < n; i++) {
            capacity[i] -= rocks[i];
        }
        Arrays.sort(capacity);
        while (j < n && additionalRocks - capacity[j] >= 0) {
            additionalRocks -= capacity[j++];
            res++;
        }
        return res;
    }
}
