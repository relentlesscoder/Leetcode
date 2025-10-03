package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/02/2025.
 * #2477 https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/
 */
public class MinimumFuelCostToReportToTheCapital {

    private long cost = 0;

    // time O(n), space O(n)
    public long minimumFuelCost(int[][] roads, int seats) {
        // https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital/solutions/1981361/kao-lu-mei-tiao-bian-shang-zhi-shao-xu-y-uamv/
        int n = roads.length + 1;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] r : roads) {
            adj[r[0]].add(r[1]);
            adj[r[1]].add(r[0]);
        }
        dfs(0, -1, adj, seats);
        return cost;
    }

    private int dfs(int node, int parent, List<Integer>[] adj, int seats) {
        int res = 1;
        for (int child : adj[node]) {
            if (child == parent) {
                continue;
            }
            res += dfs(child, node, adj, seats);
        }
        if (node > 0) {
            // calculate the contribution ceiling[size/seats] to the total cost for the edge node -> parent
            cost += (res + seats - 1) / seats;
        }
        return res;
    }
}
