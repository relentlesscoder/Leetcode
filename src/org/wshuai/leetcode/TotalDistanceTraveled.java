package org.wshuai.leetcode;

/**
 * Created by Wei on 08/02/2025.
 * #2739 https://leetcode.com/problems/total-distance-traveled/
 */
public class TotalDistanceTraveled {

    // time O(1), space O(1)
    public int distanceTraveled(int mainTank, int additionalTank) {
        return (mainTank + Math.min((mainTank - 1) / 4, additionalTank)) * 10;
    }
}
