package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/06/2025.
 * #2192 https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/
 */
public class AllAncestorsOfANodeInADirectedAcyclicGraph {

    // time O(n^2 + n*m), space O(m + n)
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> ancestors = new ArrayList<>();
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            ancestors.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            adj[from].add(to);
        }
        for (int i = 0; i < n; i++) {
            dfs(i, adj, i, ancestors);
        }
        return ancestors;
    }

    private void dfs(int ancestor, List<Integer>[] adj, int node, List<List<Integer>> ancestors) {
        for (int next : adj[node]) {
            if (ancestors.get(next).isEmpty()
                    || ancestors.get(next).get(ancestors.get(next).size() - 1) != ancestor) {
                ancestors.get(next).add(ancestor);
                dfs(ancestor, adj, next, ancestors);
            }
        }
    }

    // time O(n^2 + m), space O(n^2 + m)
    public List<List<Integer>> getAncestorsTopologicalSort(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            adj[from].add(to);
            indegree[to]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        List<Integer> sorted = new ArrayList<>();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            sorted.add(curr);
            for (int next : adj[curr]) {
                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        List<List<Integer>> ancestorsList = new ArrayList<>();
        List<Set<Integer>> ancestorsSetList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ancestorsList.add(new ArrayList<>());
            ancestorsSetList.add(new HashSet<>());
        }
        for (int node : sorted) {
            for (int next: adj[node]) {
                ancestorsSetList.get(next).add(node);
                // since we iterate by topological order, it is guaranteed that all previous
                // node has all their ancestors already
                ancestorsSetList.get(next).addAll(ancestorsSetList.get(node));
            }
        }
        for (int i = 0; i < n; i++) {
            for (int node = 0; node < n; node++) {
                if (node == i) continue;
                if (ancestorsSetList.get(i).contains(node)) {
                    ancestorsList.get(i).add(node);
                }
            }
        }
        return ancestorsList;
    }
}
