package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/07/2025.
 * #3108 https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph/
 */
public class MinimumCostWalkInWeightedGraph {

    // time O(n + (e + q) * α(n)), space O(n)
    public int[] minimumCostUnionFind(int n, int[][] edges, int[][] query) {
        int m = query.length;
        int[] res = new int[m];
        int[] roots = new int[n], ands = new int[n], rank = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
            ands[i] = -1; // 1111 1111 1111 1111 1111 1111 1111 1111
            rank[i] = 1;
        }
        for (int[] edge : edges) {
            int root1 = find(edge[0], roots), root2 = find(edge[1], roots);
            if (rank[root1] > rank[root2]) {
                rank[root1] += rank[root2];
                ands[root1] &= edge[2];
                ands[root1] &= ands[root2];
                roots[root2] = root1;
            } else {
                rank[root2] += rank[root1];
                ands[root2] &= edge[2];
                ands[root2] &= ands[root1];
                roots[root1] = root2;
            }
        }
        for (int i = 0; i < m; i++) {
            int[] q = query[i];
            int root1 = find(q[0], roots), root2 = find(q[1], roots);
            if (root1 != root2) {
                res[i] = -1;
            } else {
                res[i] = ands[root1];
            }
        }
        return res;
    }

    private int find(int i, int[] roots) {
        if (i != roots[i]) {
            roots[i] = find(roots[i], roots);
        }
        return roots[i];
    }

    // time O(n + e + q), space O(n + e)
    public int[] minimumCostDFS(int n, int[][] edges, int[][] query) {
        int m = query.length;
        int[] res = new int[m];
        ArrayList<int[]>[] adj = new ArrayList[n];
        Arrays.setAll(adj, value -> new ArrayList<>());
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
            adj[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        int[] ids = new int[n];
        Arrays.fill(ids, -1);
        List<Integer> ands = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (ids[i] < 0) {
                ands.add(dfs(i, ands.size(), adj, ids));
            }
        }
        for (int i = 0; i < m; i++) {
            int[] q = query[i];
            if (ids[q[0]] != ids[q[1]]) {
                res[i] = -1;
            } else {
                res[i] = ands.get(ids[q[0]]);
            }
        }
        return res;
    }

    private int dfs(int i, int id, ArrayList<int[]>[] adj, int[] ids) {
        ids[i] = id;
        int and = -1;
        for (int[] j : adj[i]) {
            // always AND the edge to handle more than one edges between two nodes
            // like example 2
            and &= j[1];
            if (ids[j[0]] < 0) { // already visited
                and &= dfs(j[0], id, adj, ids);
            }
        }
        return and;
    }
}
