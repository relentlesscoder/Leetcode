package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/09/2025.
 * #3373 https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii/
 */
public class MaximizeTheNumberOfTargetNodesAfterConnectingTreesII {

    // time O(n + m), space O(n + m)
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1, m = edges2.length + 1;
        List<Integer>[] adj1 = new ArrayList[n];
        Arrays.setAll(adj1, i -> new ArrayList<>());
        for (int[] edge : edges1) {
            adj1[edge[0]].add(edge[1]);
            adj1[edge[1]].add(edge[0]);
        }
        List<Integer>[] adj2 = new ArrayList[m];
        Arrays.setAll(adj2, i -> new ArrayList<>());
        for (int[] edge : edges2) {
            adj2[edge[0]].add(edge[1]);
            adj2[edge[1]].add(edge[0]);
        }
        int[] res = new int[n];
        // Find even/odd attribute for all nodes in tree1
        int[] count1 = findAllTargets(adj1);
        // For each node in tree1, we can connect to any node (even/odd)
        // in tree2 so the number of nodes from tree2 is max(odd, even)
        int count2 = findMaxTargets(adj2);
        for (int i = 0; i < n; i++) {
            res[i] = count1[i] + count2;
        }
        return res;
    }

    private int findMaxTargets(List<Integer>[] adj) {
        int n = adj.length;
        int[] count = new int[] {0, 0};
        int[] colors = new int[n];
        // Use single DFS to calculate for even/odd attributes for all nodes
        dfs(0, -1, adj, 0, count, colors);
        return Math.max(count[0], count[1]);
    }

    private int[] findAllTargets(List<Integer>[] adj) {
        int n = adj.length;
        int[] res = new int[n];
        int[] count = new int[] {0, 0};
        int[] colors = new int[n];
        // Use single DFS to calculate for even/odd attributes for all nodes
        dfs(0, -1, adj, 0, count, colors);
        for (int i = 0; i < n; i++) {
            res[i] = count[colors[i]];
        }
        return res;
    }

    private void dfs(int node, int parent, List<Integer>[] adj, int color, int[] count, int[] colors) {
        count[color]++;
        colors[node] = color;
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            dfs(next, node, adj, color ^ 1, count, colors);
        }
    }
}
