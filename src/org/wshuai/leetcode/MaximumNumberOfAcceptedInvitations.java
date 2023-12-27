package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/26/2023.
 * #1820 https://leetcode.com/problems/maximum-number-of-accepted-invitations/
 */
public class MaximumNumberOfAcceptedInvitations {

    // time O(m * n), space O(m)
    public int maximumInvitations(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        int[] invited = new int[n];
        Arrays.fill(invited, -1);
        for (int u = 0; u < m; u++) {
            boolean[] visited = new boolean[n];
            if (bpm(grid, u, n, visited, invited)) {
                res++;
            }
        }
        return res;
    }

    private boolean bpm(int[][] grid, int u, int n, boolean[] visited, int[] invited) {
        for (int v = 0; v < n; v++) {
            if (grid[u][v] == 1 && !visited[v]) {
                visited[v] = true;
                if (invited[v] < 0 || bpm(grid, invited[v], n, visited, invited)) {
                    invited[v] = u;
                    return true;
                }
            }
        }
        return false;
    }
}
