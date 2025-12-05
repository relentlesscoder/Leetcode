package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/01/2025.
 * #2493 https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/
 */
public class DivideNodesIntoTheMaximumNumberOfGroups {

    // time O(n * m), space O(n + m)
    public int magnificentSets(int n, int[][] edges) {
        int res = 0;
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            adj[u].add(v);
            adj[v].add(u);
        }
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            List<Integer> nodes = new ArrayList<>();
            if (colors[i] != 0) {
                continue;
            }
            if (!isBipartite(i, 1, colors, adj, nodes)) {
                return -1;
            }
            int maxDepth = 0;
            for (int node : nodes) {
                maxDepth = Math.max(maxDepth, bfs(node, adj));
            }
            res += maxDepth;
        }
        return res;
    }

    private int bfs(int node, List<Integer>[] adj) {
        int depth = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(node);
        boolean[] visited = new boolean[adj.length];
        visited[node] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                for (int next : adj[curr]) {
                    if (visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    queue.offer(next);
                }
            }
            depth++;
        }
        return depth;
    }

    private boolean isBipartite(int node, int color, int[] colors, List<Integer>[] adj, List<Integer> nodes) {
        nodes.add(node);
        colors[node] = color;
        for (int next : adj[node]) {
            if (colors[next] == color
                    || (colors[next] == 0 && !isBipartite(next, -color, colors, adj, nodes))) {
                return false;
            }
        }
        return true;
    }
}
