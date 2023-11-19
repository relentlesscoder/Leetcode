package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/10/2023.
 * #2735 https://leetcode.com/problems/collecting-chocolates/
 */
public class CollectingChocolates {

    // time O(n^2), space O(n)
    public long minCost(int[] nums, int x) {
        long res = Long.MAX_VALUE;
        int n = nums.length;
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        for (int k = 0; k < n; k++) {
            long sum = 1L * k * x;
            for (int i = 0; i < n; i++) {
                cost[i] = Math.min(cost[i], nums[(i + k) % n]);
                sum += cost[i];
            }
            res = Math.min(res, sum);
        }
        return res;
    }
}
