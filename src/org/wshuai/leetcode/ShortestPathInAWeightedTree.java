package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/08/2025.
 * #3515 https://leetcode.com/problems/shortest-path-in-a-weighted-tree/
 */
public class ShortestPathInAWeightedTree {

    // time O(n * log(n) + q * log(n)), space O(n)
    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        List<Integer>[] adj = new ArrayList[n + 1];
        Arrays.setAll(adj, i -> new ArrayList<>()); // O(n)
        for (int[] edge : edges) { // O(m)
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        int[] count = {0};
        // DFS to calculate timestamp for each node starting from the root node
        dfs(1, 0, adj, in, out, count); // O(n + m)
        // Array weights to record weight change
        int[] weights = new int[n + 1];
        // Maximum in/out timestamp is n
        BIT bit = new BIT(n);
        for (int[] edge : edges) { // O(m)
            int u = edge[0], v = edge[1], w = edge[2];
            update(u, v, w, in, out, weights, bit); // O(log(n))
        }
        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) { // O(q)
            if (query[0] == 1) {
                int u = query[1], v = query[2], w = query[3];
                update(u, v, w, in, out, weights, bit); // O(log(n))
            } else {
                // The shortest path distance from the root node 1 to node q[1]
                // is the sum for all nodes j with in[j] < in[q[1]]
                res.add(bit.pre(in[query[1]])); // O(log(n))
            }
        }
        return res.stream().mapToInt(i -> i).toArray(); // O(q)
    }

    private void dfs(int node, int parent, List<Integer>[] adj, int[] in, int[] out, int[] count) {
        in[node] = ++count[0];
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            dfs(next, node, adj, in, out, count);
        }
        out[node] = count[0];
    }

    private void update(int n1, int n2, int weight, int[] in, int[] out, int[] weights, BIT bit) {
        // Ensure n2 is child using timestamp
        if (in[n1] > in[n2]) {
            n2 = n1;
        }
        int diff = weight - weights[n2];
        weights[n2] = weight;
        bit.update(in[n2], diff);
        // out[n2] + 1 to remove all weights in current path after out timestamp
        bit.update(out[n2] + 1, -diff);
    }

    private static class BIT {

        private final int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += index & -index;
            }
        }

        public int pre(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
