package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/06/2025.
 * #2467 https://leetcode.com/problems/most-profitable-path-in-a-tree/
 */
public class MostProfitablePathInATree {

    private int maxIncome = Integer.MIN_VALUE;

    // time O(n), space O(n)
    public int mostProfitablePathSingleDFS(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        int[] timeToBob = new int[n]; // timeToBob[i] is the time it takes from 0 to node i
        ArrayList<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        return findPaths(0, 0, 0, bob, amount, timeToBob, adj);
    }

    private int findPaths(
            int node,
            int parentNode,
            int time,
            int bob,
            int[] amount,
            int[] timeToBob,
            ArrayList<Integer>[] adj
    ) {
        int n = amount.length;
        int income = 0, maxChild = Integer.MIN_VALUE;
        if (node == bob) {
            timeToBob[node] = 0;
        } else {
            timeToBob[node] = n;
        }
        for (int nextNode : adj[node]) {
            if (nextNode != parentNode) {
                // calculate max profit from child nodes
                maxChild = Math.max(
                        maxChild,
                        findPaths(nextNode, node, time + 1, bob, amount, timeToBob, adj)
                );
                // update time to bob
                timeToBob[node] = Math.min(
                        timeToBob[node],
                        timeToBob[nextNode] + 1
                );
            }
        }
        // above dfs ensures the timeToBob[node] has been calculated
        if (timeToBob[node] > time) {
            income += amount[node];
        } else if (timeToBob[node] == time) {
            income += amount[node] / 2;
        }

        return (maxChild == Integer.MIN_VALUE)
                ? income
                : income + maxChild;
    }

    // time O(n), space O(n)
    public int mostProfitablePathDoubleDFS(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        ArrayList<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        Map<Integer, Integer> bobPath = new HashMap<>();
        boolean[] visited = new boolean[n];
        findBobPath(bob, 0, bobPath, visited, adj);
        Arrays.fill(visited, false);
        findAlicePath(0, 0, 0, amount, bobPath, visited, adj);
        return maxIncome;
    }

    private void findAlicePath(int node, int time, int income, int[] amount, Map<Integer, Integer> bobPath, boolean[] visited, ArrayList<Integer>[] adj) {
        visited[node] = true;
        if (!bobPath.containsKey(node) || time < bobPath.get(node)) {
            income += amount[node];
        } else if (time == bobPath.get(node)) {
            income += amount[node] / 2;
        } else {
            income += 0;
        }
        if (adj[node].size() == 1 && node != 0) {
            maxIncome = Math.max(maxIncome, income);
            return;
        }
        for (int nextNode : adj[node]) {
            if (!visited[nextNode]) {
                findAlicePath(nextNode, time + 1, income, amount, bobPath, visited, adj);
            }
        }
    }

    // time O(n), space O(n)
    public int mostProfitablePathDFSAndBFS(int[][] edges, int bob, int[] amount) {
        int res = Integer.MIN_VALUE, n = amount.length;
        Deque<int[]> queue = new ArrayDeque<>();
        ArrayList<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        Map<Integer, Integer> bobPath = new HashMap<>();
        boolean[] visited = new boolean[n];
        findBobPath(bob, 0, bobPath, visited, adj);
        Arrays.fill(visited, false);
        queue.offer(new int[] {0, 0, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], time = curr[1], income = curr[2];
            visited[node] = true;
            if (!bobPath.containsKey(node) || time < bobPath.get(node)) { // alice arrive the gate first
                income += amount[node];
            } else if (time == bobPath.get(node)) { // alice and bob arrive the gate the same time
                income += amount[node] / 2;
            }
            /** If the gate is already open, no price will be required, nor will
              * there be any cash reward.
              * else {
              *   income += 0;
              * }
            */
            if (adj[node].size() == 1 && node != 0) {
                res = Math.max(res, income);
            }
            for (int nextNode : adj[node]) {
                if (!visited[nextNode]) {
                    queue.offer(new int[] {nextNode, time + 1, income});
                }
            }
        }
        return res;
    }

    private boolean findBobPath(int node, int time, Map<Integer, Integer> bobPath,
                                boolean[] visited, ArrayList<Integer>[] adj) {
        bobPath.put(node, time);
        visited[node] = true;
        if (node == 0) {
            return true;
        }
        for (int nextNode : adj[node]) {
            if (!visited[nextNode]) {
                if (findBobPath(nextNode, time + 1, bobPath, visited, adj)) {
                    return true;
                }
            }
        }
        bobPath.remove(node);
        return false;
    }
}
