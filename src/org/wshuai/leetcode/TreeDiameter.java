package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/13/2019.
 * #1245 https://leetcode.com/problems/tree-diameter/
 */
public class TreeDiameter {

    private int res = 0;

    // time O(n), space O(n)
    public int treeDiameter(int[][] edges) {
        res = 0;
        int n = edges.length + 1;
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        dfs(0, -1, adj);
        return res;
    }

    private int dfs(int node, int parent, List<Integer>[] adj) {
        int max = 0; // 通过子节点回来的最长路径
        for (int next : adj[node]) {
            // 树的 DFS 需要排除父节点
            if (next == parent) {
                continue;
            }
            // 递归求最长路径
            int len = dfs(next, node, adj);
            // 通过当前节点的最长路径一定等于子节点的最长的两条路径之和
            res = Math.max(res, len + max);
            // 更新最长路径
            max = Math.max(max, len);
        }
        // 返回通过当前节点返回去父节点的最长路径
        return max + 1;
    }
}
