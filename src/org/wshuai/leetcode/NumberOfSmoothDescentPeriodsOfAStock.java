package org.wshuai.leetcode;

/**
 * Created by Wei on 09/17/2023.
 * #2110 https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/
 */
public class NumberOfSmoothDescentPeriodsOfAStock {

    // time O(n), space O(1)
    public long getDescentPeriods(int[] prices) {
        long res = 0;
        for (int i = 0, j = 0; j < prices.length; j++) {
            if (j > 0 && prices[j] != prices[j - 1] - 1) {
                i = j;
            }
            res += j - i + 1;
        }
        return res;
    }

    // similar question - #2762 https://leetcode.com/problems/continuous-subarrays/
}
