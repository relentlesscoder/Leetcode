package org.wshuai.leetcode.array;

/**
 * Created by Wei on 05/21/2021.
 * #1854 https://leetcode.com/problems/maximum-population-year/
 */
public class MaximumPopulationYear {

    // time O(n + MAX), space O(MAX)
    public int maximumPopulation(int[][] logs) {
        // 差分数组应用
        int res = 0, cnt = 0, max = 0;
        for (int[] l : logs) {
            max = Math.max(max, l[1]);
        }
        int[] diff = new int[max + 1];
        for (int[] l : logs) {
            diff[l[0]]++;
            diff[l[1]]--;
        }
        for (int i = 1950, sum = 0; i <= max; i++) {
            sum += diff[i];
            if (sum > cnt) {
                res = i;
                cnt = sum;
            }
        }
        return res;
    }
}
