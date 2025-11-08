package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Wei on 08/24/2025.
 * #2322 https://leetcode.com/problems/minimum-score-after-removals-on-a-tree/
 */
public class MinimumScoreAfterRemovalsOnATree {

    // time O(n^2), space O(n)
    public int minimumScore(int[] nums, int[][] edges) {
        int res = Integer.MAX_VALUE, n = nums.length;
        ArrayList<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        int[] sum = new int[n];
        int[] in = new int[n];
        int[] out = new int[n];
        int[] count = {0};
        dfs(0, -1, sum, in, out, count, nums, adj);
        for (int u = 1; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                // if in[u] < in[v] < out[u] then u is an ancestor for v, so the tree can
                // be divided to 3 subtrees subtree(v), subtree(u) - subtree(v), subtree(0)
                // - subtree(u). Note that root 0 is special case and can't be used to get
                // 3 subtrees since it does not have a parent.
                if (in[u] < in[v] && out[u] > in[v]) {
                    res = Math.min(res, calc(sum[0] ^ sum[u], sum[u] ^ sum[v], sum[v]));
                } else if (in[v] < in[u] && out[v] > in[u]) {
                    // if in[v] < in[u] < out[v] then v is an ancestor for u, so the tree can
                    // be divided to 3 subtrees subtree(u), subtree(v) - subtree(u), subtree(0)
                    // - subtree(v)
                    res = Math.min(res, calc(sum[0] ^ sum[v], sum[v] ^ sum[u], sum[u]));
                } else {
                    // Otherwise, the tree can be divided to 3 subtrees subtree(u), subtree(v),
                    // subtree(0) - subtree(u) - subtree(v)
                    res = Math.min(res, calc(sum[0] ^ sum[u] ^ sum[v], sum[u], sum[v]));
                }
            }
        }
        return res;
    }

    private void dfs(int node, int parent, int[] sum, int[] in, int[] out, int[] count, int[] nums, ArrayList<Integer>[] adj) {
        sum[node] = nums[node];
        in[node] = count[0]++;
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            dfs(next, node, sum, in, out, count, nums, adj);
            sum[node] ^= sum[next];
        }
        out[node] = count[0];
    }

    private int res = Integer.MAX_VALUE;

    // time O(n^2), space O(n)
    public int minimumScoreDoubleDFS(int[] nums, int[][] edges) {
        int n = nums.length;
        ArrayList<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        // For XOR, we have:
        //   a^b = c
        //   c^a = b
        //   c^b = a
        // Calculate the total XOR sum for all nodes
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        dfs(0, -1, sum, nums, adj);
        return res;
    }

    private int dfs(int node, int parent, int sum, int[] nums, ArrayList<Integer>[] adj) {
        // Calculate the XOR sum for the subtree starts with current node
        int sum1 = nums[node];
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            sum1 ^= dfs(next, node, sum, nums, adj);
        }
        // Remove the edge between node and it's parent, do DFS to traverse the subtree
        // rooted at the parent
        if (parent != -1) {
            dfs2(parent, node, node, sum, sum1, nums, adj);
        }
        return sum1;
    }

    private int dfs2(int node, int parent, int ancestor, int sum, int sum1, int[] nums, ArrayList<Integer>[] adj) {
        // Calculate the XOR sum for the subtree starts with current node
        int sum2 = nums[node];
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            sum2 ^= dfs2(next, node, ancestor, sum, sum1, nums, adj);
        }
        // Terminate if we hit back to the root of the subtree
        if (parent == ancestor) {
            return sum2;
        }
        // Calculate result with the XOR sum for the 3 subtree
        res = Math.min(res, calc(sum1, sum2, sum ^ sum1 ^ sum2));
        return sum2;
    }

    private int calc(int sum1, int sum2, int sum3) {
        return Math.max(sum1, Math.max(sum2, sum3)) - Math.min(sum1, Math.min(sum2, sum3));
    }
}
