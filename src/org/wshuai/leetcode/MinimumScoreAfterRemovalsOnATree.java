package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/24/2025.
 * #2322 https://leetcode.com/problems/minimum-score-after-removals-on-a-tree/
 */
public class MinimumScoreAfterRemovalsOnATree {

    // time O(n^2), space O(n)
    public int minimumScore(int[] nums, int[][] edges) {
        int res = Integer.MAX_VALUE;
        int n = nums.length;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        int[] sum = new int[n], in = new int[n], out = new int[n];
        int[] count = {0};
        dfs(0, -1, nums, adj, sum, in, out, count);
        for (int u = 1; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                if (in[v] > in[u] && in[v] < out[u]) {
                    res = Math.min(res, calc(sum[0]^sum[u], sum[u]^sum[v], sum[v]));
                } else if (in[u] > in[v] && in[u] < out[v]) {
                    res = Math.min(res, calc(sum[0]^sum[v], sum[u]^sum[v], sum[u]));
                } else {
                    res = Math.min(res, calc(sum[0]^sum[u]^sum[v], sum[u], sum[v]));
                }
            }
        }
        return res;
    }

    private void dfs(int node, int parent, int[] nums, List<Integer>[] adj, int[] sum, int[] in, int[] out, int[] count) {
        in[node] = count[0]++;
        sum[node] = nums[node];
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            dfs(next, node, nums, adj, sum, in, out, count);
            sum[node] ^= sum[next];
        }
        out[node] = count[0];
    }

    private int RES = Integer.MAX_VALUE;

    // time O(n^2), space O(n)
    public int minimumScoreDoubleDFS(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        dfs(0, -1, nums, adj, sum);
        return RES;
    }

    private int dfs(int node, int parent, int[] nums, List<Integer>[] adj, int sum) {
        int val = nums[node];
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            val ^= dfs(next, node, nums, adj, sum);
        }
        for (int next : adj[node]) {
            if (next == parent) {
                dfs2(next, node, val, node, nums, adj, sum);
            }
        }
        return val;
    }

    private int dfs2(int node, int parent, int other, int ancestor, int[] nums, List<Integer>[] adj, int sum) {
        int val = nums[node];
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            val ^= dfs2(next, node, other, ancestor, nums, adj, sum);
        }
        if (parent == ancestor) {
            return val;
        }
        RES = Math.min(RES, calc(other, val, sum ^ other ^ val));
        return val;
    }

    private int calc(int sum1, int sum2, int sum3) {
        return Math.max(sum1, Math.max(sum2, sum3)) - Math.min(sum1, Math.min(sum2, sum3));
    }
}
