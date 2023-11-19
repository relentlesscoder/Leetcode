package org.wshuai.leetcode;

/**
 * Created by Wei on 09/13/2023.
 * #2833 https://leetcode.com/problems/furthest-point-from-origin/
 */
public class FurthestPointFromOrigin {

    // time O(n), space O(1)
    public int furthestDistanceFromOrigin(String moves) {
        int maxLeft = 0, maxRight = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                maxLeft++;
                maxRight--;
            } else if (c == 'R') {
                maxLeft--;
                maxRight++;
            } else {
                maxLeft++;
                maxRight++;
            }
        }
        return Math.max(maxLeft, maxRight);
    }
}
