package org.wshuai.leetcode;

/**
 * Created by Wei on 12/28/2020.
 * #1701 https://leetcode.com/problems/average-waiting-time/
 */
public class AverageWaitingTime {

    // time O(n)
    public double averageWaitingTime(int[][] customers) {
        int chef = 0;
        double total = 0.0;
        for(int[] cur : customers){
            if(chef <= cur[0]){
                total += cur[1];
                chef = cur[0] + cur[1];
            }else{
                total += chef - cur[0] + cur[1];
                chef += cur[1];
            }
        }
        return total / customers.length;
    }
}
