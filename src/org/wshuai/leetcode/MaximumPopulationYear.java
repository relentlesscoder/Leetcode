package org.wshuai.leetcode;

/**
 * Created by Wei on 05/21/2021.
 * #1854 https://leetcode.com/problems/maximum-population-year/
 */
public class MaximumPopulationYear {

    // time O(n)
    public int maximumPopulation(int[][] logs) {
        int res = 0;
        int[] year = new int[2051];
        for(int[] log : logs){
            year[log[0]]++;
            year[log[1]]--;
        }
        for(int i = 1950; i <= 2050; i++){
            year[i] += year[i - 1];
            res = year[i] > year[res] ? i : res;
        }
        return res;
    }
}
