package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/30/2023.
 * #2852 https://leetcode.com/problems/sum-of-remoteness-of-all-cells/
 */
public class SumOfRemotenessOfAllCells {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(n^2), space O(n)
    public long sumRemotenessDFS(int[][] grid) {
        long res = 0, total = 0;
        int m = grid.length, n = grid[0].length, id = -1;
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    id--;
                    long sum = dfs(grid, i, j, id);
                    map.put(id, sum);
                    total += sum;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res += grid[i][j] == -1 ? 0 :
                        total - map.getOrDefault(grid[i][j], 0L);
            }
        }
        return res;
    }

    private long dfs(int[][] grid, int i, int j, int id) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] < 0) {
            return 0;
        }
        long res = grid[i][j];
        grid[i][j] = id;
        for (int d = 0; d < 4; d++) {
            res += dfs(grid, i + DIRS[d], j + DIRS[d + 1], id);
        }
        return res;
    }

    // time O(n^2 * α(n^2)), space O(n^2)
    public long sumRemotenessUnionFind(int[][] grid) {
        long res = 0, total = 0;
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    total += grid[i][j];
                    int idx = i * n + j;
                    if (j + 1 < n && grid[i][j + 1] > 0) {
                        uf.union(idx, i * n + j + 1);
                    }
                    if (i + 1 < m && grid[i + 1][j] > 0) {
                        uf.union(idx, (i + 1) * n + j);
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res += grid[i][j] == -1 ? 0 : total - uf.getScore(i * n + j);
            }
        }
        return res;
    }

    private static class UnionFind {

        private final int[] roots;
        private final int[] ranks;
        private final long[] sum;

        public UnionFind(int[][] grid) {
            int m = grid.length, n = grid[0].length, size = m * n;
            roots = new int[size];
            ranks = new int[size];
            sum = new long[size];
            Arrays.setAll(roots, i -> i);
            Arrays.setAll(ranks, i -> 1);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sum[i * n + j] = grid[i][j];
                }
            }
        }

        public void union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) {
                return;
            }
            if (ranks[ra] > ranks[rb]) {
                ranks[ra] += ranks[rb];
                sum[ra] += sum[rb];
                roots[rb] = ra;
            } else {
                ranks[rb] += ranks[ra];
                sum[rb] += sum[ra];
                roots[ra] = rb;
            }
        }

        public long getScore(int a) {
            return sum[find(a)];
        }

        private int find(int a) {
            if (roots[a] != a) {
                roots[a] = find(roots[a]);
            }
            return roots[a];
        }
    }
}
