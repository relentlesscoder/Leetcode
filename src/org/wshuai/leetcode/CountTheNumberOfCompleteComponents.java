package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 10/03/2025.
 * #2685 https://leetcode.com/problems/count-the-number-of-complete-components/
 */
public class CountTheNumberOfCompleteComponents {

    // time O(n + m), space O(n + m)
    public int countCompleteComponentsDFS(int n, int[][] edges) {
        int res = 0;
        boolean[] visited = new boolean[n];
        ArrayList<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            int[] stats = dfs(i, adj, visited);
            int nodeCount = stats[0], edgeCount = stats[1];
            if (nodeCount * (nodeCount - 1) == edgeCount) {
                res++;
            }
        }
        return res;
    }

    private int[] dfs(int node, ArrayList<Integer>[] adj, boolean[] visited) {
        int nodeCount = 1, edgeCount = adj[node].size();
        visited[node] = true;
        for (int next : adj[node]) {
            if (visited[next]) {
                continue;
            }
            int[] stats = dfs(next, adj, visited);
            nodeCount += stats[0];
            edgeCount += stats[1];
        }
        return new int[] {nodeCount, edgeCount};
    }

    // time O(m + n), space O(m + n)
    public int countCompleteComponentsBFS(int n, int[][] edges) {
        int res = 0;
        boolean[] visited = new boolean[n];
        ArrayList<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            int nodeCount = 0, edgeCount = 0;
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            visited[i] = true;
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                nodeCount++;
                edgeCount += adj[curr].size();
                for (int next : adj[curr]) {
                    if (visited[next]) {
                        continue;
                    }
                    queue.offer(next);
                    visited[next] = true;
                }
            }
            if (nodeCount * (nodeCount - 1) == edgeCount) {
                res++;
            }
        }
        return res;
    }

    // time O(n + m * α(n)) , space O(n)
    // where α(n) is the inverse Ackermann function, which grows extremely slowly
    // and is practically constant
    public int countCompleteComponentsUnionFind(int n, int[][] edges) {
        int res = 0;
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]); // union nodes by edges
        }
        int[] edgeCount = new int[n];
        for (int[] edge : edges) { // calculate total number of edges for each root node
            int root = uf.find(edge[0]);
            edgeCount[root] += 2;
        }
        for (int i = 0; i < n; i++) {
            // for root node of each connected component, check if the component is complete
            if (uf.find(i) == i && edgeCount[i] == uf.rank[i] * (uf.rank[i] - 1)) {
                res++;
            }
        }
        return res;
    }

    private class UnionFind {

        int[] root;
        int[] rank;
        int count;

        public UnionFind(int n) {
            count = n;
            root = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int node) {
            if (root[node] != node) {
                root[node] = find(root[node]);
            }
            return root[node];
        }

        public void union(int node1, int node2) {
            int root1 = find(node1), root2 = find(node2);
            if (root1 == root2) {
                return;
            }
            if (rank[root1] > rank[root2]) {
                rank[root1] += rank[root2];
                root[root2] = root1;
            } else {
                rank[root2] += rank[root1];
                root[root1] = root2;
            }
            count--;
        }

        public int countDisjointSet() {
            return count;
        }
    }
}
