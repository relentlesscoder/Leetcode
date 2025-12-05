package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 01/09/2024.
 * #2861 https://leetcode.com/problems/maximum-number-of-alloys/
 */
public class MaximumNumberOfAlloys {

    // time O(n * k * log(r)), space O(1)
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        long low = 0, high = (long) 1e9;
        while (low < high) {
            long mid = low + (high - low + 1) / 2;
            if (canCreate(budget, composition, stock, cost, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return (int)low;
    }

    private boolean canCreate(int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost, long threshold) {
        long minCost = Integer.MAX_VALUE;
        for (List<Integer> comp : composition) {
            long currCost = 0;
            for (int j = 0; j < comp.size(); j++) {
                long curr = threshold * comp.get(j);
                if (stock.get(j) >= curr) {
                    continue;
                } else {
                    long extra = (curr - stock.get(j)) * cost.get(j);
                    currCost += extra;
                }
            }
            minCost = Math.min(currCost, minCost);
        }
        return minCost <= budget;
    }
}
