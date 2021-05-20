package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 05/20/2021.
 * #1833 https://leetcode.com/problems/maximum-ice-cream-bars/
 */
public class MaximumIceCreamBars {

    // time O(n*log(n))
    public int maxIceCream(int[] costs, int coins) {
        int res = 0;
        Arrays.sort(costs);
        for(int i = 0; i < costs.length && coins >= costs[i]; i++){
            res++;
            coins -= costs[i];
        }
        return res;
    }
}
