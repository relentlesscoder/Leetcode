package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/09/2025.
 * #3372 https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/
 */
public class MaximizeTheNumberOfTargetNodesAfterConnectingTreesI {

    // time O(n^2 + m^2), space O(n + m)
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        // 思路: 从 edges2 中选一个节点与 edges1 中的每个节点相连，题目要求最大化目标节点数。
        // 因为连接需要花费一条边，所以我们总是可以从 edges2 里选择那个距离为 k - 1 的目标节
        // 点数最大的节点与edges1 中的每一个节点相连。
        int n = edges1.length + 1, m = edges2.length + 1;
        int[] res = new int[n];
        // 构造邻接表
        List<Integer>[] adj1 = buildTree(edges1), adj2 = buildTree(edges2);
        // 对 edges2 中的每一个节点用 DFS 找出距离为 k - 1 (节点数 k) 的目标节点数的最大值
        int max = 0;
        for (int i = 0; i < m; i++) { // O(m^2)
            max = Math.max(max, dfs(i, -1, k, adj2));
        }
        // // 对 edges1 中的每一个节点用 DFS 找出距离为 k (节点数 k + 1) 的目标节点数并将
        // 最大值 max 加到 edges1 的每个答案中
        for (int i = 0; i < n; i++) { // O(n^2)
            res[i] = dfs(i, -1, k + 1, adj1) + max;
        }
        return res;
    }

    private int dfs(int node, int parent, int k, List<Integer>[] adj) {
        if (k == 0) {
            return 0;
        }
        int res = 1;
        for (int next : adj[node]) {
            // 树的 DFS 只需要保证下一个节点不是父节点即可
            if (next == parent) {
                continue;
            }
            res += dfs(next, node, k - 1, adj);
        }
        return res;
    }

    private List<Integer>[] buildTree(int[][] edges) {
        List<Integer>[] tree = new ArrayList[edges.length + 1];
        Arrays.setAll(tree, i -> new ArrayList<>());
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }
        return tree;
    }
}
