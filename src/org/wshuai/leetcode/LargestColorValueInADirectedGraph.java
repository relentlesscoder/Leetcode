package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/03/2025.
 * #1857 https://leetcode.com/problems/largest-color-value-in-a-directed-graph/
 */
public class LargestColorValueInADirectedGraph {

    // time O(m + n), space O(m + n)
    public int largestPathValue(String colors, int[][] edges) {
        int res = 0, n = colors.length();
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (u == v) {
                return -1;
            }
            adj[u].add(v);
        }
        char[] cs = colors.toCharArray();
        int[][] memo = new int[n][];
        for (int i = 0; i < n; i++) {
            int[] lens = dfs(i, adj, cs, memo);
            if (lens.length == 0) {
                return -1;
            }
            res = Math.max(res, lens[cs[i] - 'a']);
        }
        return res;
    }

    private int[] dfs(int node, List<Integer>[] adj, char[] colors, int[][] memo) {
        if (memo[node] != null) {
            return memo[node];
        }
        memo[node] = new int[]{};
        int[] res = new int[26];
        for (int next : adj[node]) {
            int[] lens = dfs(next, adj, colors, memo);
            // cycle detected
            if (lens.length == 0) {
                return lens;
            }
            for (int i = 0; i < 26; i++) {
                res[i] = Math.max(res[i], lens[i]);
            }
        }
        res[colors[node] - 'a']++;
        return memo[node] = res;
    }

    // time O(m + n), space O(m + n)
    public int largestPathValueTopologicalSort(String colors, int[][] edges) {
        int res = 0, n = colors.length();
        char[] cs = colors.toCharArray();
        int[] degree = new int[n];
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (u == v) {
                return -1;
            }
            adj[u].add(v);
            degree[v]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        int visited = 0;
        int[][] memo = new int[n][26];
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;
            int ch = cs[node] - 'a';
            memo[node][ch]++;
            res = Math.max(res, memo[node][ch]);
            for (int next : adj[node]) {
                for (int i = 0; i < 26; i++) {
                    memo[next][i] = Math.max(memo[next][i], memo[node][i]);
                }
                if (--degree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        // visited < n means cycle found
        return visited < n ? -1 : res;
    }
}
