package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 12/25/2025.
 * #3243 https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i/
 */
public class ShortestDistanceAfterRoadAdditionQueriesI {

    // time O(m * n), space O(m + n)
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        // 因为可能存在重叠的查询，本题不可以使用与#3244相同的解法
        int m = queries.length;
        int[] res = new int[m];
        // 构造图的邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            adj[i].add(i + 1);
        }
        for (int i = 0; i < m; i++) {
            // 每次加入新的边然后重新BFS计算最短路径
            adj[queries[i][0]].add(queries[i][1]);
            res[i] = bfs(adj);
        }
        return res;
    }

    private int bfs(List<Integer>[] adj) {
        int steps = 0, n = adj.length;
        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                for (int next : adj[curr]) {
                    if (next == n - 1) {
                        return steps + 1;
                    }
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
