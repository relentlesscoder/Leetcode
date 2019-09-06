package org.wshuai.leetcode;

/**
 * Created by Wei on 9/5/19.
 * #1176 https://leetcode.com/problems/diet-plan-performance/
 */
public class DietPlanPerformance {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int points = 0;
        int sum = 0;
        int i = 0;
        int j = -1;
        for(; i < k-1; i++){
            sum += calories[i];
        }
        while(i < calories.length){
            sum += calories[i++];
            sum -= j == -1 ? 0 : calories[j];
            j++;
            if(sum > upper){
                points++;
            }else if(sum < lower){
                points--;
            }
        }
        return points;
    }
}
