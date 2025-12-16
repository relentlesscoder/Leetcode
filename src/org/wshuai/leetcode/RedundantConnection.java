package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 10/24/2019.
 * #0684 https://leetcode.com/problems/redundant-connection/
 */
public class RedundantConnection {

    // time O(n * α(n)), space O(n)
    public int[] findRedundantConnectionUnionFind(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            int u = edges[i][0] - 1, v = edges[i][1] - 1;
            // 如果在连通两个节点之前他们就已经有相同的根结点，说明加入当前的边
            // 导致了环。
            if (!uf.union(u, v)) {
                return edges[i];
            }
        }
        return new int[0];
    }

    private static class UnionFind {

        private final int[] root;
        private final int[] rank;

        public UnionFind(int n) {
            root = new int[n];
            rank = new int[n];
            Arrays.setAll(root, i -> i);
            Arrays.fill(rank, 1);
        }

        private int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public boolean union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return false;
            }
            if (rank[rx] > rank[ry]) {
                rank[rx] += rank[ry];
                root[ry] = rx;
            } else {
                rank[ry] += rank[rx];
                root[rx] = ry;
            }
            return true;
        }
    }

    private int start = -1;
    private Set<Integer> cycle = new HashSet<>();

    // time O(n), space O(n)
    public int[] findRedundantConnectionSingleDFS(int[][] edges) {
        int n = edges.length, start = -1;
        cycle = new HashSet<>();
        List<Integer>[] adj = new ArrayList[n + 1];
        Arrays.setAll(adj, i -> new ArrayList<>());
        boolean[] visited = new boolean[n + 1];
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        dfs(1, -1, adj, visited);
        for (int i = n - 1; i >= 0; i--) {
            if (cycle.contains(edges[i][0]) && cycle.contains(edges[i][1])) {
                return edges[i];
            }
        }
        return new int[0];
    }

    private void dfs(int node, int parent, List<Integer>[] adj, boolean[] visited) {
        if (visited[node]) {
            start = node;
            return;
        }
        visited[node] = true;
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            if (cycle.isEmpty()) {
                dfs(next, node, adj, visited);
            }
            if (start != -1) {
                cycle.add(node);
            }
            if (node == start) {
                start = -1;
                return;
            }
        }
    }

    // time O(n^2), space O(n)
    public int[] findRedundantConnectionMultiDFS(int[][] edges) {
        int n = edges.length;
        List<Integer>[] adj = new ArrayList[n + 1];
        Arrays.setAll(adj, i -> new ArrayList<>());
        boolean[] visited = new boolean[n + 1];
        for (int[] edge : edges) { // O(n)
            Arrays.fill(visited, false);
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
            if (dfs1(u, -1, adj, visited)) { // O(n)
                return edge;
            }
        }
        return new int[0];
    }

    private boolean dfs1(int node, int parent, List<Integer>[] adj, boolean[] visited) {
        if (visited[node]) {
            return true;
        }
        visited[node] = true;
        for (int next : adj[node]) {
            if (next != parent && dfs1(next, node, adj, visited)) {
                return true;
            }
        }
        return false;
    }
}
