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
        int[] count1 = findAllTargets(adj1, k);
        int count2 = findMaxTargets(adj2, k - 1);
        for (int i = 0; i < n; i++) {
            res[i] = count1[i] + count2;
        }
        return res;
    }

    private int[] findAllTargets(List<Integer>[] adj, int k) {
        int n = adj.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = dfs(i, -1, adj, k);
        }
        return res;
    }

    private int dfs(int node, int parent, List<Integer>[] adj, int k) {
        if (k < 0) {
            return 0;
        }
        int res = 1;
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            res += dfs(next, node, adj, k - 1);
        }
        return res;
    }

    private int findMaxTargets(List<Integer>[] adj, int k) {
        int[] counts = findAllTargets(adj, k);
        int res = 0;
        for (int count : counts) {
            res = Math.max(res, count);
        }
        return res;
    }
}
