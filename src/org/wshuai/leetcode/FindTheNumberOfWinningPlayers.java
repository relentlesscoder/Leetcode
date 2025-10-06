package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3238 https://leetcode.com/problems/find-the-number-of-winning-players/
 */
public class FindTheNumberOfWinningPlayers {

    // time O(m + n), space O(n)
    public int winningPlayerCount(int n, int[][] pick) {
        int res = 0;
        int[][] balls = new int[n][11];
        for (int[] p : pick) {
            balls[p[0]][p[1]]++;
        }
        for (int i = 0; i < n; i++) {
            int target = i + 1;
            for (int count : balls[i]) {
                if (count >= target) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}
