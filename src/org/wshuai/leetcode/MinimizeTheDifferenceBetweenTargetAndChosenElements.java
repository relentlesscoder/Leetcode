package org.wshuai.leetcode;

/**
 * Created by Wei on 01/10/2024.
 * #1981 https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/
 */
public class MinimizeTheDifferenceBetweenTargetAndChosenElements {

    // time O(m * 5000), space O(m * 5000)
    public int minimizeTheDifference(int[][] mat, int target) {
        Integer[][] dp = new Integer[mat.length][5_001];
        return minDiff(mat, 0, target, 0, dp);
    }

    private int minDiff(int[][] mat, int i, int target, int val, Integer[][] dp) {
        if (i == mat.length) {
            return Math.abs(val - target);
        }
        if (dp[i][val] != null) {
            return dp[i][val];
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < mat[0].length; j++) {
            res = Math.min(res, minDiff(mat, i + 1, target, val + mat[i][j], dp));
        }
        return dp[i][val] = res;
    }
}
