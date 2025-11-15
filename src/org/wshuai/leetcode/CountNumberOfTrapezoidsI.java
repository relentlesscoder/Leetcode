package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 11/15/2025.
 * #3623 https://leetcode.com/problems/count-number-of-trapezoids-i/
 */
public class CountNumberOfTrapezoidsI {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n + m), space O(n + m)
    public int countTrapezoids(int[][] points) {
        long res = 0, n = points.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        // For each y coordinate, find all its unique x coordinates.
        for (int i = 0; i < n; i++) {
            int[] p = points[i];
            map.computeIfAbsent(p[1], k -> new HashSet<>()).add(p[0]);
        }
        int m = map.size();
        long[] nums = new long[m];
        int idx = 0;
        for (Set<Integer> set : map.values()) {
            // For each of the line at y, calculate number of pairs
            nums[idx] = 1L * (1 + set.size() - 1) * (set.size() - 1) / 2;
            nums[idx++] %= MOD;
        }
        long prefix = 0;
        // Each point pair at y and that of all previous y' can form a trapezoid.
        // For example, pairs count array is [5, 4, 7, 2, 6], for each line (index):
        //   0: --
        //   1: 5 * 4
        //   2: 5 * 7 + 4 * 7 = (5 + 4) * 7
        //   3: 5 * 2 + 4 * 2 + 7 * 2 = (5 + 4 + 7) * 2
        //   4: 5 * 6 + 4 * 6 + 7 * 6 + 2 * 6 = (5 + 4 + 7 + 2) * 6
        // So the number is just prefix * nums[i]
        for (int i = 0; i < m; i++) {
            res = (res + prefix * nums[i] % MOD) % MOD;
            prefix = (prefix + nums[i]) % MOD;
        }
        return (int) res;
    }
}
