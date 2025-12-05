package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/04/2025.
 * #2603 https://leetcode.com/problems/collect-coins-in-a-tree/
 */
public class CollectCoinsInATree {

    // time O(n), space O(n)
    public int collectTheCoins(int[] coins, int[][] edges) {
        // https://leetcode.cn/problems/collect-coins-in-a-tree/solutions/2191371/tuo-bu-pai-xu-ji-lu-ru-dui-shi-jian-pyth-6uli/
        int n = coins.length, edgesLeft = edges.length;
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
            indegree[u]++;
            indegree[v]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1 && coins[i] == 0) {
                queue.offer(i);
            }
        }
        // "Remove" leave nodes with no coins
        while (!queue.isEmpty()) {
            edgesLeft--;
            int node = queue.poll();
            for (int next : adj[node]) {
                if (--indegree[next] == 1 && coins[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1 && coins[i] == 1) {
                queue.offer(i);
            }
        }
        edgesLeft -= queue.size(); // "Remove" the last level leave nodes
        for (int node : queue) {
            for (int next : adj[node]) { // "Remove" the second last level leave nodes
                if (--indegree[next] == 1) {
                    edgesLeft--;
                }
            }
        }
        // Times 2 since each edge needs to be travelled twice
        return Math.max(edgesLeft * 2, 0);
    }
}
