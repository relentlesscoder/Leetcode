package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/02/2025.
 * #2477 https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/
 */
public class MinimumFuelCostToReportToTheCapital {

    private long res = 0;

    public long minimumFuelCost(int[][] roads, int seats) {
        // 贡献法，计算每条从子节点回到父节点的边上有多少车经过。
        res = 0;
        int n = roads.length + 1;
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] e : roads) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        dfs(0, -1, adj, seats);
        return res;
    }

    private int dfs(int node, int parent, List<Integer>[] adj, int seats) {
        int cost = 1;
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            cost += dfs(next, node, adj, seats);
        }
        if (node > 0) {
            // 统计需要的汽车 (汽油) 数量
            res += (cost + seats - 1) / seats;
        }
        return cost; // 经过这个城市回到其父节点城市的人数
    }
}
