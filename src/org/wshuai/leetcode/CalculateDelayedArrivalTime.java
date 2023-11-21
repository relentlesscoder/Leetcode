package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2023.
 * #2651 https://leetcode.com/problems/calculate-delayed-arrival-time/
 */
public class CalculateDelayedArrivalTime {

    // time O(1), space O(1)
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }
}
