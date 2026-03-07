package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 02/01/2026.
 * #2368 https://leetcode.com/problems/reachable-nodes-with-restrictions/
 */
public class ReachableNodesWithRestrictions {

    // time O(n + m), space O(n + m)
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int r : restricted) { // 用哈希集存被限制的节点
            set.add(r);
        }
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] e : edges) {
            // 如果边中的一个点被限制则这条边不可以被加入邻接表
            if (set.contains(e[0]) || set.contains(e[1])) {
                continue;
            }
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        // DFS 寻找所有可以到达的点 (也可以用BFS 或者 并查集)
        return dfs(0, -1, adj);
    }

    private int dfs(int node, int parent, List<Integer>[] adj) {
        int res = 1;
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            res += dfs(next, node, adj);
        }
        return res;
    }
}
