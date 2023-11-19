package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/19/2023.
 * #2924 https://leetcode.com/problems/find-champion-ii/
 */
public class FindChampionII {

    // time O(n^2), space O(n)
    public int findChampion(int n, int[][] edges) {
        int res = -1, count = 0;
        int[] indegree = new int[n];
        for (int[] e : edges) {
            indegree[e[1]]++;
        }
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                count++;
                res = i;
            }
        }
        return count == 1 ? res : -1;
    }

    // time O(n^2), space O(n)
    public int findChampionDFS(int n, int[][] edges) {
        List<Integer> champions = new ArrayList<>();
        boolean[] visited = new boolean[n];
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[1]].add(e[0]);
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited, champions);
            }
        }
        return champions.size() == 1 ? champions.get(0) : -1;
    }

    private void dfs(int i, List<Integer>[] graph, boolean[] visited, List<Integer> champions) {
        visited[i] = true;
        if (graph[i].isEmpty()) {
            champions.add(i);
            return;
        }
        for (int next : graph[i]) {
            if (!visited[i]) {
                dfs(next, graph, visited, champions);
            }
        }
    }
}
