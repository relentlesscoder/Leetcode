package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/06/2024.
 * #2144 https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/
 */
public class MinimumCostOfBuyingCandiesWithDiscount {

    // time O(n * log(n)), space O(log(n))
    public int minimumCost(int[] cost) {
        int res = 0, n = cost.length;
        Arrays.sort(cost);
        for (int i = n - 1; i >= 0; i -= 3) {
            res += cost[i];
            if (i - 1 >= 0) {
                res += cost[i - 1];
            }
        }
        return res;
    }
}
