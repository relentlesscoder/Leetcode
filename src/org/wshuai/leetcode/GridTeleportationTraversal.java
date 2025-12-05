package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/30/2025.
 * #3552 https://leetcode.com/problems/grid-teleportation-traversal/
 */
public class GridTeleportationTraversal {

    private static final int[] DIRS = new int[] {-1, 0, 1, 0, -1};

    // time O(m * n), space O(m * n)
    public int minMoves(String[] matrix) {
        int m = matrix.length, n = matrix[0].length();
        char[][] grid = new char[m][n];
        boolean[][] visited = new boolean[m][n];
        boolean[] used = new boolean[26];
        List<int[]>[] adj = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = matrix[i].charAt(j);
                grid[i][j] = c;
                if (c == '.' || c == '#') {
                    continue;
                }
                adj[c - 'A'].add(new int[]{i, j});
            }
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{grid[0][0] == '.' ? 100 : grid[0][0] - 'A', 0, 0, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.pollFirst();
            int val = curr[0], steps = curr[1], row = curr[2], col = curr[3];
            if (row == m - 1 && col == n - 1) {
                return steps;
            }
            if (val != 100 && !used[val]) {
                used[val] = true;
                for (int j = 0; j < adj[val].size(); j++) {
                    int a = adj[val].get(j)[0], b = adj[val].get(j)[1];
                    visited[a][b] = true;
                    queue.offerFirst(new int[]{grid[a][b] == '.' ? 100 : grid[a][b] - 'A', steps, a, b});
                }
            }
            for (int i = 0; i < 4; i++) {
                int x = row + DIRS[i], y = col + DIRS[i + 1];
                if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '#' || visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                queue.offerLast(new int[]{grid[x][y] == '.' ? 100 : grid[x][y] - 'A', steps + 1, x, y});
            }
        }
        return -1;
    }

    // time O(m * n * log(m * n)), space O(m * n)
    public int minMovesMinQueue(String[] matrix) {
        int m = matrix.length, n = matrix[0].length();
        char[][] grid = new char[m][n];
        boolean[][] visited = new boolean[m][n];
        boolean[] used = new boolean[26];
        List<int[]>[] adj = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = matrix[i].charAt(j);
                grid[i][j] = c;
                if (c == '.' || c == '#') {
                    continue;
                }
                adj[c - 'A'].add(new int[]{i, j});
            }
        }
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        minQueue.offer(new int[]{grid[0][0] == '.' ? 100 : grid[0][0] - 'A', 0, 0, 0});
        while (!minQueue.isEmpty()) {
            int[] curr = minQueue.poll();
            int val = curr[0], steps = curr[1], row = curr[2], col = curr[3];
            if (row == m - 1 && col == n - 1) {
                return steps;
            }
            for (int i = 0; i < 4; i++) {
                int x = row + DIRS[i], y = col + DIRS[i + 1];
                if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '#' || visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                minQueue.offer(new int[]{grid[x][y] == '.' ? 100 : grid[x][y] - 'A', steps + 1, x, y});
            }
            if (val != 100 && !used[val]) {
                used[val] = true;
                for (int j = 0; j < adj[val].size(); j++) {
                    int a = adj[val].get(j)[0], b = adj[val].get(j)[1];
                    visited[a][b] = true;
                    minQueue.offer(new int[]{grid[a][b] == '.' ? 100 : grid[a][b] - 'A', steps, a, b});
                }
            }
        }
        return -1;
    }
}
