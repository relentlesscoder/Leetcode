package org.wshuai.leetcode;

/**
 * Created by Wei on 12/26/2023.
 * #2162 https://leetcode.com/problems/minimum-cost-to-set-cooking-time/
 */
public class MinimumCostToSetCookingTime {

    // time O(1), space O(1)
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int minutes = targetSeconds / 60, seconds = targetSeconds % 60;
        return Math.min(getCost(minutes, seconds, startAt, moveCost, pushCost),
                getCost(minutes - 1, seconds + 60, startAt, moveCost, pushCost));
    }

    // time O(100 * 100), space O(1)
    public int minCostSetTimeBrutalForce(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int minCost = Integer.MAX_VALUE, maxMins = targetSeconds / 60;
        for (int minutes = 0; minutes <= Math.min(maxMins, 99); minutes++) {
            minCost = Math.min(minCost, getCost(minutes, targetSeconds - minutes * 60, startAt, moveCost, pushCost));
        }
        return minCost;
    }

    private int getCost(int minutes, int seconds, int startAt, int moveCost, int pushCost) {
        if (minutes < 0 || minutes >= 100 || seconds < 0 || seconds >= 100) {
            return Integer.MAX_VALUE;
        }
        int cost = 0, prev = startAt;
        StringBuilder sb = new StringBuilder();
        if (minutes > 0) {
            sb.append(minutes);
        }
        if (minutes > 0 && seconds <= 9) {
            sb.append("0");
        }
        sb.append(seconds);
        for (char c : sb.toString().toCharArray()) {
            int digit = c - '0';
            if (digit != prev) {
                cost += moveCost;
            }
            cost += pushCost;
            prev = digit;
        }
        return cost;
    }
}
