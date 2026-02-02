package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 02/01/2026.
 * #3067 https://leetcode.com/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network/
 */
public class CountPairsOfConnectableServersInAWeightedTreeNetwork {

    // time O(n^2), space O(n)
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        // 对每个服务器计算从其出发的每条路径上路径和可以整除 signalSpeed 的服务器的数量，这些
        // 数量的乘积即为该服务器可连通的服务器对的总数。
        int n = edges.length + 1;
        int[] res = new int[n];
        // 构造邻接表
        List<int[]>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] e : edges) {
            adj[e[0]].add(new int[] {e[1], e[2]});
            adj[e[1]].add(new int[] {e[0], e[2]});
        }
        // 遍历每个服务器
        for (int i = 0; i < n; i++) {
            // 如果出发的路径只有一条，则能通过它连接的服务器对数为 0 。
            if (adj[i].size() == 1) {
                continue;
            }
            int sum = 0;
            for (int[] child : adj[i]) {
                // 计算每条路径上符合要求的服务器数量数量
                int cnt = dfs(child[0], i, child[1], adj, signalSpeed);
                // 将当前路径上符合要求的服务器与前面所有的符合要求的服务器的数量相乘
                res[i] += cnt * sum;
                // 更新符合要求的服务器总数
                sum += cnt;
            }
        }
        return res;
    }

    private int dfs(int node, int parent, int path, List<int[]>[] adj, int signalSpeed) {
        int res = path % signalSpeed == 0 ? 1 : 0;
        for (int[] next : adj[node]) {
            // 树的 DFS 只需要保证下一个节点不是父节点即可
            if (next[0] == parent) {
                continue;
            }
            res += dfs(next[0], node, path + next[1], adj, signalSpeed);
        }
        return res;
    }
}
