package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/28/2025.
 * #3694 https://leetcode.com/problems/distinct-points-reachable-after-substring-removal/
 */
public class DistinctPointsReachableAfterSubstringRemoval {

    private static final int[][] DIRS = new int[26][2];

    static {
        DIRS['L' - 'A'] = new int[] {-1, 0};
        DIRS['R' - 'A'] = new int[] {1, 0};
        DIRS['D' - 'A'] = new int[] {0, -1};
        DIRS['U' - 'A'] = new int[] {0, 1};
    }

    // time O(n), space O(1)
    public int distinctPoints(String s, int k) {
        int n = s.length(), x = 0, y = 0;
        Set<Long> points = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char in = s.charAt(i);
            x += DIRS[in - 'A'][0];
            y += DIRS[in - 'A'][1];
            if (i - k + 1 < 0) {
                continue;
            }
            points.add(((long)(x + n) << 20) + (y + n));
            char out = s.charAt(i - k + 1);
            x -= DIRS[out - 'A'][0];
            y -= DIRS[out - 'A'][1];
        }
        return points.size();
    }
}
