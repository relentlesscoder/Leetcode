package org.wshuai.leetcode;

/**
 * Created by Wei on 07/05/2025.
 * #3492 https://leetcode.com/problems/maximum-containers-on-a-ship/
 */
public class MaximumContainersOnAShip {

    // time O(1), space O(1)
    public int maxContainers(int n, int w, int maxWeight) {
        return Math.min(n * n, maxWeight / w);
    }
}
