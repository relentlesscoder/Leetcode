package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 11/17/2019.
 * #0778 https://leetcode.com/problems/swim-in-rising-water/
 */
public class SwimInRisingWater {
    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(n^2 * log(MAX)), space O(n^2 * log(MAX))
    public int swimInWater(int[][] grid) {
        int n = grid.length, low = 0, high = n * n - 1;
        if (n == 1) {
            return grid[0][0];
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (!dfs(grid, 0, 0, mid, new boolean[n][n])) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private boolean dfs(int[][] grid, int i, int j, int threshold, boolean[][] visited) {
        int n = grid.length;
        if (i < 0 || i >= n || j < 0 || j >= n || visited[i][j] || grid[i][j] > threshold) {
            return false;
        }
        if (i == n - 1 && j == n - 1) {
            return true;
        }
        visited[i][j] = true;
        for (int d = 0; d < 4; d++) {
            int x = i + DIRS[d], y = j + DIRS[d + 1];
            if (dfs(grid, x, y, threshold, visited)) {
                return true;
            }
        }
        return false;
    }

    // time O(n^2 * log(n) * α(n)), space O(n^2)
    public int swimInWaterUnionFind(int[][] grid) {
        int n = grid.length, target = n * n - 1;
        if (n == 1) {
            return grid[0][0];
        }
        UnionFind uf = new UnionFind(n * n);
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                minQueue.offer(new int[]{i, j, grid[i][j]});
            }
        }
        while (!minQueue.isEmpty()) {
            int[] curr = minQueue.poll();
            int i = curr[0], j = curr[1], v = curr[2], id1 = i * n + j;
            uf.add(id1);
            for (int d = 0; d < 4; d++) {
                int x = i + DIRS[d], y = j + DIRS[d + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && v > grid[x][y]) {
                    int id2 = x * n + y;
                    if (uf.exists(id2)) {
                        uf.union(id1, id2);
                        if (uf.exists(0) && uf.exists(target) && uf.find(0) == uf.find(target)) {
                            return v;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private class UnionFind {

        private int[] root, rank;

        public UnionFind(int n) {
            root = new int[n];
            rank = new int[n];
            Arrays.fill(rank, 1);
            Arrays.fill(root, -1);
        }

        public boolean exists(int x) {
            return root[x] != -1;
        }

        public void add(int x) {
            root[x] = x;
        }

        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] > rank[rootY]) {
                rank[rootX] += rank[rootY];
                root[rootY] = rootX;
            } else {
                rank[rootY] += rank[rootX];
                root[rootX] = rootY;
            }
        }
    }
}
