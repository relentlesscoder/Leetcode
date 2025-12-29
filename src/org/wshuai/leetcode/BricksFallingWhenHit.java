package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/17/2019.
 * #0803 https://leetcode.com/problems/bricks-falling-when-hit/
 */
public class BricksFallingWhenHit {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // O(m * n + l), space O(m * n + l)
    public int[] hitBricksDFS(int[][] grid, int[][] hits) {
        // 正难则反，首先去掉所有hits中的砖块并用DFS来标记所有跟网格顶部相连的(稳定的)砖块。
        // 然后逆序遍历hits数组，把砖一块一块加回来并计算稳定砖块变化的数量。这个变化量即为答
        // 案。
        int n = grid[0].length, l = hits.length;
        int[] res = new int[l];
        for (int[] h : hits) { // O(l)
            grid[h[0]][h[1]]--; // 去掉hits[i]指向的砖块
        }
        for (int i = 0; i < n; i++) { // O(m * n)
            dfs(0, i, grid); // DFS来标记所有跟网格顶部相连的(稳定的)砖块为2
        }
        for (int i = l - 1; i >= 0; i--) { // O(l)
            int x = hits[i][0], y = hits[i][1];
            // 判断新加的砖块是否与某一块稳定的砖块相连通
            if (++grid[x][y] == 1 && isConnected(x, y, grid)) {
                // 对新加的砖块做DFS计算稳定砖块的变化量
                res[i] = dfs(x, y, grid) - 1;
            }
        }
        return res;
    }

    private boolean isConnected(int i, int j, int[][] grid) {
        if (i == 0) {
            return true;
        }
        for (int d = 0; d < 4; d++) {
            int x = i + DIRS[d], y = j + DIRS[d + 1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 2) {
                return true;
            }
        }
        return false;
    }

    private int dfs(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }
        int res = 1;
        grid[i][j] = 2;
        for (int d = 0; d < 4; d++) {
            res += dfs(i + DIRS[d], j + DIRS[d + 1], grid);
        }
        return res;
    }

    // O(m * n + l), space O(m * n + l)
    public int[] hitBricksUnionFind(int[][] grid, int[][] hits) {
        // 正难则反，首先去掉所有hits中的砖块并用连通集来计算所有跟网格顶部相连的(稳定的)砖块。
        // 然后逆序遍历hits数组，把砖一块一块加回来并计算稳定砖块变化的数量。这个变化量即为答
        // 案。
        int m = grid.length, n = grid[0].length, t = m * n, l = hits.length;
        int[] res = new int[l];
        for (int i = 0; i < l; i++) { // O(l)
            int[] h = hits[i];
            grid[h[0]][h[1]]--; // 去掉hits[i]指向的砖块
        }
        UnionFind uf = new UnionFind(t + 1);
        for (int j = 0; j < n; j++) { // O(n)
            if (grid[0][j] == 1) {
                uf.union(t, j); // 把顶部的砖块与虚拟节点t相连
            }
        }
        // 连通所有砖块
        for (int i = 0; i < m; i++) { // O(m * n)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                int id = i * n + j;
                if (i + 1 < m && grid[i + 1][j] == 1) {
                    uf.union(id, (i + 1) * n + j);
                }
                if (j + 1 < n && grid[i][j + 1] == 1) {
                    uf.union(id, i * n + j + 1);
                }
            }
        }
        for (int i = l - 1; i >= 0; i--) { // O(l)
            int[] h = hits[i];
            if (++grid[h[0]][h[1]] == 0) { // 忽略之前标记的hits[i]
                continue;
            }
            int cnt = uf.getRank(t), id = h[0] * n + h[1];
            // 如果砖块在顶部，先和虚拟节点连通
            if (h[0] == 0) {
                uf.union(t, id);
            }
            // 连通相邻的砖块格子
            for (int j = 0; j < 4; j++) {
                int x = h[0] + DIRS[j], y = h[1] + DIRS[j + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                    uf.union(id, x * n + y);
                }
            }
            // 计算稳定砖块的变化量
            res[i] = Math.max(uf.getRank(t) - cnt - 1, 0);
        }
        return res;
    }

    private static class UnionFind {

        private final int[] roots;
        private final int[] ranks;

        public UnionFind(int n) {
            roots = new int[n];
            ranks = new int[n];
            Arrays.setAll(roots, i -> i);
            Arrays.fill(ranks, 1);
        }

        public int getRank(int x) {
            return ranks[find(x)];
        }

        public int find(int x) {
            if (roots[x] != x) {
                roots[x] = find(roots[x]);
            }
            return roots[x];
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return;
            }
            if (ranks[rx] >= ranks[ry]) {
                ranks[rx] += ranks[ry];
                roots[ry] = rx;
            } else {
                ranks[ry] += ranks[rx];
                roots[rx] = ry;
            }
        }
    }
}
