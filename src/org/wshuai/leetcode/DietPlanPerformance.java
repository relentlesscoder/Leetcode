package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #1176 https://leetcode.com/problems/diet-plan-performance/
 */
public class DietPlanPerformance {

    // time O(n), space O(1)
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int n = calories.length, score = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += calories[i];
            if (i - k + 1 < 0) {
                continue;
            }
            score += sum > upper ? 1 : (sum < lower ? -1 : 0);
            sum -= calories[i - k + 1];
        }
        return score;
    }
}
