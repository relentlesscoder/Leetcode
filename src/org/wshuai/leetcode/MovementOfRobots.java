package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/28/2025.
 * #2731 https://leetcode.com/problems/movement-of-robots/
 */
public class MovementOfRobots {

    private static final long MOD = (int)1e9 + 7;

    // time O(n * log(n)), space O(n)
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long res = 0, prefixSum = 0;
        long[] robots = new long[n];
        // since all robots are the same so `collision` equals `pass through`
        // r1 -> <- r2
        // r1 <- -> r2 = r2 <- -> r1
        for (int i = 0; i < n; i++) {
            robots[i] = 1L * nums[i] + (s.charAt(i) == 'L' ? -1 : 1) * d;
        }
        Arrays.sort(robots);
        /**
         * For robots array: [-12,-11,8,12,13]
         *
         * pair [1, 0] = [1, 0] -> 1 = 1
         *
         * pair [2, 1] = [2, 1] -> 19 = 20
         * pair [2, 0] = [2, 1] + [1, 0] -> 19 + 1 = 40
         *
         * pair [3, 2] = [3, 2] -> 4 = 44
         * pair [3, 1] = [3, 2] + [2, 1] -> 4 + 19 = 67
         * pair [3, 0] = [3, 2] + [2, 1] + [1, 0] -> 4 + 19 + 1 = 91
         *
         * pair [4, 3] = [4, 3] -> 1 = 92
         * pair [4, 2] = [4, 3] + [3, 2] -> 1 + 4 = 97
         * pair [4, 1] = [4, 3] + [3, 2] + [2, 1] -> 1 + 4 + 19 = 121
         * pair [4, 0] = [4, 3] + [3, 2] + [2, 1] + [1, 0] -> 1 + 4 + 19 + 1 = 146
         */
        for (int i = 1; i < n; i++) {
            prefixSum = (prefixSum + i * (robots[i] - robots[i - 1]) % MOD) % MOD;
            res = (res + prefixSum) % MOD;
        }
        return (int)res;
    }
}
