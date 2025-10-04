package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Wei on 10/03/2025.
 * #2685 https://leetcode.com/problems/count-the-number-of-complete-components/
 */
public class CountTheNumberOfCompleteComponents {

    // time O(n + m), space O(n + m)
    public int countCompleteComponents(int n, int[][] edges) {
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
}
