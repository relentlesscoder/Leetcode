package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/04/2023.
 * #1971 https://leetcode.com/problems/find-if-path-exists-in-graph/
 */
public class FindIfPathExistsInGraph {

    // time O(V+E), space O(V+E)
    public boolean validPathBFS(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], val -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], val -> new ArrayList<>()).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        visited[source] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        while(!queue.isEmpty()){
            int curr = queue.poll();
            if (curr == destination) {
                return true;
            }
            for(int next : graph.get(curr)) {
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                queue.offer(next);
            }
        }
        return false;
    }

    // time O(V+E), space O(V+E)
    public boolean validPathDFSRecursive(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], val -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], val -> new ArrayList<>()).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        return dfs(graph, visited, source, destination);
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int curr, int destination) {
        if (curr == destination) {
            return true;
        }
        if (!visited[curr]) {
            visited[curr] = true;
            for (int next : graph.get(curr)) {
                if (dfs(graph, visited, next, destination)) {
                    return true;
                }
            }
        }
        return false;
    }

    // time O(V+E), space O(V+E)
    public boolean validPathDFSIterative(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], val -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], val -> new ArrayList<>()).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        visited[source] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (curr == destination) {
                return true;
            }
            for (int next : graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    stack.push(next);
                }
            }
        }
        return false;
    }

    // time O(E*log(V)), space O(V)
    public boolean validPathUnionFind(int n, int[][] edges, int source, int destination) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        return unionFind.find(source) == unionFind.find(destination);
    }
}

class UnionFind{
    private int[] root;
    private int[] rank;
    public UnionFind(int n) {
        root = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (root[x] != x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }
            root[rootX] = rootY;
            rank[rootY] += rank[rootX];
        }
    }
}
