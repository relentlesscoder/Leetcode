package org.wshuai.leetcode;

/**
 * Created by Wei on 8/21/19.
 * #746 https://leetcode.com/problems/min-cost-climbing-stairs/
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int c1 = cost[0];
        int c2 = cost[1];
        for(int i = 2; i < cost.length + 1; i++){
            int curr = i == cost.length ? 0 : cost[i];
            int min = Math.min(c1, c2) + curr;
            c1 = c2;
            c2 = min;
        }
        return c2;
    }
}
