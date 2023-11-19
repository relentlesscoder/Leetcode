package org.wshuai.leetcode;

/**
 * Created by Wei on 10/04/2020.
 * #1599 https://leetcode.com/problems/maximum-profit-of-operating-a-centennial-wheel/
 */
public class MaximumProfitOfOperatingACentennialWheel {

    // time O(n)
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int res = -1, n = customers.length, max = 0;
        for(int i = 0, sum = 0, profit = 0, round = 0; i < n || sum > 0; i++){
            sum += i < n ? customers[i] : 0;
            int cur = Math.min(4, sum);
            sum -= cur;
            round++;
            profit += cur * boardingCost - runningCost;
            if(profit > max){
                max = profit;
                res = round;
            }
        }
        return res;
    }
}
