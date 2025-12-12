package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 09/30/2025.
 * #3552 https://leetcode.com/problems/grid-teleportation-traversal/
 */
public class GridTeleportationTraversal {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(min(m, n))
    public int minMoves(String[] matrix) {
        int m = matrix.length, n = matrix[0].length();
        List<int[]>[] adj = new ArrayList[26];
        Arrays.setAll(adj, i -> new ArrayList<>());
        boolean[] visited = new boolean[26];
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                char c = matrix[i].charAt(j);
                if (c >= 'A' && c <= 'Z') {
                    adj[c - 'A'].add(new int[] {i, j});
                }
            }
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerFirst(new int[] {0, 0});
        dist[0][0] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.pollFirst();
            int i = curr[0], j = curr[1];
            if (i == m - 1 && j == n - 1) {
                return dist[i][j];
            }
            // 如果格子里是字符，因为不费步数传送到任何有相同字符的格所以把这些格子的距离都设为当前值并且加入
            // 队列前面。这些格子会在接下来的遍历里马上处理，等同于对所有相同字符的格子做一个多源BFS。
            char c = matrix[i].charAt(j);
            if (c >= 'A' && c <= 'Z' && !visited[c - 'A']) {
                // 如果同种字符的格子已经被处理过，那所有相同字符的格子不应该再被处理
                visited[c - 'A'] = true;
                for (int[] next : adj[c - 'A']) {
                    dist[next[0]][next[1]] = dist[i][j];
                    queue.offerFirst(new int[] {next[0], next[1]});
                }
            }
            // 遍历四周的格子，到他们需要的步数为当前步数加1并加到队列后面.
            for (int d = 0; d < 4; d++) {
                int x = i + DIRS[d], y = j + DIRS[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x].charAt(y) != '#') {
                    if (dist[x][y] > dist[i][j] + 1) {
                        dist[x][y] = dist[i][j] + 1;
                        queue.offerLast(new int[] {x, y});
                    }
                }
            }
        }
        return -1;
    }
}
