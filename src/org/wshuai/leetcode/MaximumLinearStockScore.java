package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/01/2024.
 * #2898 https://leetcode.com/problems/maximum-linear-stock-score/
 */
public class MaximumLinearStockScore {

    // time O(n), space O(n)
    public long maxScore(int[] prices) {
        long res = 0L;
        int n = prices.length;
        Map<Integer, Long> sumMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int diff = prices[i - 1] - i;
            long sum = sumMap.getOrDefault(diff, 0L) + prices[i - 1];
            sumMap.put(diff, sum);
            res = Math.max(res, sum);
        }
        return res;
    }
}
