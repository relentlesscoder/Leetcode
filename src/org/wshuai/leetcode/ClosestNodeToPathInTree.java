package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/30/2023.
 * #2277 https://leetcode.com/problems/closest-node-to-path-in-tree/
 */
public class ClosestNodeToPathInTree {

    // time O(Q * (V + E)), space O(V + E)
    public int[] closestNode(int n, int[][] edges, int[][] query) {
        int[] res = new int[query.length];
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        for (int i = 0; i < query.length; i++) {
            Set<Integer> path = new HashSet<>();
            buildPath(query[i][0], query[i][1], graph, path);
            res[i] = findClosestNode(query[i][2], graph, path);
        }
        return res;
    }

    private boolean buildPath(int node, int target, List<Integer>[] graph, Set<Integer> path) {
        path.add(node);
        if (node == target) {
            return true;
        }
        for (int next : graph[node]) {
            if (!path.contains(next)) {
                if (buildPath(next, target, graph, path)) {
                    return true;
                }
            }
        }
        path.remove(node);
        return false;
    }

    private int findClosestNode(int start, List<Integer>[] graph, Set<Integer> path) {
        boolean[] visited = new boolean[graph.length];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (path.contains(node)) {
                return node;
            }
            for (int next : graph[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        return -1;
    }
}
