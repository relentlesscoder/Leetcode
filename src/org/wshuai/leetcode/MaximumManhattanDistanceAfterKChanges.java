package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2025.
 * #3443 https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes/
 */
public class MaximumManhattanDistanceAfterKChanges {

    // time O(n), space O(1)
    public int maxDistance(String s, int k) {
        int res = 0, horizontal = 0, vertical = 0;
        k <<= 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'N') {
                vertical++;
            } else if (c == 'S') {
                vertical--;
            } else if (c == 'E') {
                horizontal++;
            } else {
                horizontal--;
            }
            res = Math.max(res, Math.min(Math.abs(vertical) + Math.abs(horizontal) + k, i + 1));
        }
        return res;
    }

    // time O(n), space O(1)
    public int maxDistanceDirections(String s, int k) {
        int res = 0, north = 0, south = 0, east = 0, west = 0;
        for (char c : s.toCharArray()) {
            if (c == 'N') {
                north++;
            } else if (c == 'S') {
                south++;
            } else if (c == 'E') {
                east++;
            } else {
                west++;
            }
            int changes1 = Math.min(k, Math.min(north, south));
            int changes2 = Math.min(k - changes1, Math.min(east, west));
            res = Math.max(res, calc(north, south, changes1)
                    + calc(east, west, changes2));
        }
        return res;
    }

    private int calc(int d1, int d2, int changes) {
        return Math.abs(d1 - d2) + changes * 2;
    }
}
