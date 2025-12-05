package org.wshuai.leetcode;

/**
 * Created by Wei on 07/05/2025.
 * #2017 https://leetcode.com/problems/grid-game/
 */
public class GridGame {

    // time O(n), space O(1)
    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long res = Long.MAX_VALUE, firstRowSum = 0, secondRowSum = 0;
        for (int num : grid[0]) {
            firstRowSum += num;
        }
        for (int index = 0; index < n; index++) {
            firstRowSum -= grid[0][index];
            res = Math.min(res, Math.max(secondRowSum, firstRowSum));
            secondRowSum += grid[1][index];
        }
        return res;
    }
}
