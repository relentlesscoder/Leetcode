package org.wshuai.leetcode;

/**
 * Created by Wei on 11/01/2025.
 * #1900 https://leetcode.com/problems/the-earliest-and-latest-rounds-where-players-compete/
 */
public class TheEarliestAndLatestRoundsWherePlayersCompete {

    // time O(n^4), space O(n^3)
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        // https://leetcode.cn/problems/the-earliest-and-latest-rounds-where-players-compete/solutions/825860/dpmei-ju-xia-yi-lun-liang-ming-xuan-shou-okfu/
        int[][][][] memo = new int[n + 1][n + 1][n + 1][2];
        return dfs(n, firstPlayer, secondPlayer, memo);
    }

    private int[] dfs(int n, int first, int second, int[][][][] memo) {
        // Player A competes with player B
        if (first + second == n + 1) {
            return new int[]{1, 1};
        }
        // Ensure players left to first is less than
        // players right to second
        if (first + second > n + 1) {
            int tmp = first;
            first = n + 1 - second;
            second = n + 1 - tmp;
        }
        int[] mem = memo[n][first][second];
        if (mem[0] > 0) {
            return mem;
        }
        int m = (n + 1) / 2; // Players for next round - ceiling(n/2)
        int minMid = second <= m ? 0 : second - n / 2 - 1;
        int maxMid = second <= m ? second - first - 1 : m - first - 1;
        int earliest = Integer.MAX_VALUE;
        int latest = 0;

        // Iterate number of players left to first player
        for (int left = 0; left < first; left++) {
            // Iterate number of players between first player and second player
            for (int mid = minMid; mid <= maxMid; mid++) {
                int[] res = dfs(m, left + 1, left + mid + 2, memo);
                earliest = Math.min(earliest, res[0]);
                latest = Math.max(latest, res[1]);
            }
        }
        mem[0] = earliest + 1;
        mem[1] = latest + 1;
        return mem;
    }
}
