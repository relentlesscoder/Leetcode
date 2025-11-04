package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/04/2025.
 * #2608 https://leetcode.com/problems/shortest-cycle-in-a-graph/
 */
public class ShortestCycleInAGraph {

    // time O(n * (m + n)), space O(m + n)
    public int findShortestCycle(int n, int[][] edges) {
        // https://leetcode.cn/problems/shortest-cycle-in-a-graph/solutions/2203585/yi-tu-miao-dong-mei-ju-qi-dian-pao-bfspy-ntck/
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (u == v) {
                return 1;
            }
            adj[u].add(v);
            adj[v].add(u);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, bfs(i, n, adj));
        }
        return res < Integer.MAX_VALUE ? res : -1;
    }

    private int bfs(int start, int n, List<Integer>[] adj) {
        int res = Integer.MAX_VALUE;
        int[] distance = new int[n];
        Arrays.fill(distance, -1);
        distance[start] = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start, -1});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], parent = curr[1];
            for (int next : adj[node]) {
                if (distance[next] < 0) {
                    distance[next] = distance[node] + 1;
                    queue.offer(new int[] {next, node});
                } else if (next != parent) {
                    res = Math.min(res, distance[node] + distance[next] + 1);
                }
            }
        }
        return res;
    }
}
