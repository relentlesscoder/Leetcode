package org.wshuai.leetcode;

/**
 * Created by Wei on 08/03/2025.
 * #1995 https://leetcode.com/problems/count-special-quadruplets/
 */
public class CountSpecialQuadruplets {

    // time O(400 * n), space O(400 * n)
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int[][][] dp = new int[n + 1][101][4];
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int v = nums[i - 1];
            for (int j = 0; j <= 100; j++) {
                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] += dp[i - 1][j][k];
                    if (j - v >= 0 && k - 1 >= 0) {
                        dp[i][j][k] += dp[i - 1][j - v][k - 1];
                    }
                }
            }
        }
        int res = 0;
        for (int i = 3; i < n; i++) {
            res += dp[i][nums[i]][3];
        }
        return res;
    }

    // time O(n^2), space O(MAX)
    public int countQuadrupletsN2(int[] nums) {
        int res = 0, n = nums.length;
        int[] map = new int[500];
        for (int b = n - 3; b >= 1; b--) {
            for (int d = b + 2; d < n; d++) {
                // Since all num in nums are positive, we don't need to add negatives
                if (nums[d] > nums[b + 1]) {
                    map[nums[d] - nums[b + 1]]++;
                }
            }
            for (int a = 0; a < b; a++) {
                res += map[nums[a] + nums[b]];
            }
        }
        return res;
    }

    // time O(n^3), space O(MAX)
    public int countQuadrupletsN3(int[] nums) {
        int res = 0, n = nums.length;
        int[] map = new int[500];
        for (int c = n - 2; c >= 2; c--) {
            map[nums[c + 1]]++;
            for (int a = 0; a < n; a++) {
                for (int b = a + 1; b < c; b++) {
                    res += map[nums[a] + nums[b] + nums[c]];
                }
            }
        }
        return res;
    }

    // time O(n^4), space O(1)
    public int countQuadrupletsN4(int[] nums) {
        int res = 0, n = nums.length;
        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                for (int c = b + 1; c < n; c++) {
                    for (int d = c + 1; d < n; d++) {
                        if (nums[a] + nums[b] + nums[c] == nums[d]) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
