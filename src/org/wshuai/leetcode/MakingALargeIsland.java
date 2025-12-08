package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 12/20/2019.
 * #0827 https://leetcode.com/problems/making-a-large-island/
 */
public class MakingALargeIsland {
    private static final int[] DIRS = new int[]{0, 1, 0, -1, 0};

    // time O(m * n), space O(m * n)
    public int largestIslandDFS(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length, id = 1;
        // 遍历网格, 对网格中值为1的格子进行DFS来统计它所在岛的面积并将所有岛的面积
        // 编号存在哈希表中。
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                id++; // 岛的编号
                int area = dfs(grid, i, j, id);
                map.put(id, area);
                res = Math.max(res, area);
            }
        }
        // 再次遍历网格，对每个值为0的格子用哈希表来查看与之相邻的值为1的格子所在的
        // 岛的面积。将这个格子的0变为1所能得到的最大面积即为这些岛（去重后）面积的
        // 总和加1。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    int area = 0;
                    for (int d = 0; d < 4; d++) {
                        int x = i + DIRS[d], y = j + DIRS[d + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 1) {
                            int cid = grid[x][y];
                            if (set.add(cid)) {
                                area += map.getOrDefault(cid, 0);
                            }
                        }
                    }
                    res = Math.max(res, area + 1);
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, int id) {
        // 判断边界合法性以及当前格子是否已经遍历过
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }
        int res = 1;
        // 标记当前格子的编号，这个编号同时也可以用来判断格子是否遍历过
        grid[i][j] = id;
        for (int d = 0; d < 4; d++) {
            res += dfs(grid, i + DIRS[d], j + DIRS[d + 1], id);
        }
        return res;
    }

    // time O(m * n), space O(m * n)
    public int largestIslandUnionFind(int[][] grid) {
        int res = 0, m = grid.length, n = grid[0].length;
        // 遍历网格对网格中值为1的格子，把它与相邻右边或者下边值为1的格子用并查集连通。
        // 这样遍历结束后可以得到网格中所有岛的面积。
        UnionFind uf = new UnionFind(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                // 处理网格中只有一个1的特殊情况
                // 示例1:
                //   [[1]]
                res = Math.max(res, 1);
                if (j + 1 < n && grid[i][j + 1] == grid[i][j]) {
                    res = Math.max(res, uf.union(i * n + j, i * n + j + 1));
                }
                if (i + 1 < m && grid[i + 1][j] == grid[i][j]) {
                    res = Math.max(res, uf.union(i * n + j, (i + 1) * n + j));
                }
            }
        }
        // 再次遍历网格，对每个值为0的格子用并查集来查看与之相邻的值为1的格子所在的
        // 岛的面积。将这个格子的0变为1所能得到的最大面积即为这些岛（去重后）面积的
        // 总和加1。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    int area = 0;
                    for (int d = 0; d < 4; d++) {
                        int x = i + DIRS[d], y = j + DIRS[d + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                            int root = uf.find(x * n + y);
                            // 对相同的岛不能重复计数
                            if (set.add(root)) {
                                area += uf.getSize(root);
                            }
                        }
                    }
                    res = Math.max(res, area + 1);
                }
            }
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

        public int getSize(int r) {
            return ranks[r];
        }

        public int union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra != rb) {
                if (ranks[ra] > ranks[rb]) {
                    ranks[ra] += ranks[rb];
                    roots[rb] = ra;
                } else {
                    ranks[rb] += ranks[ra];
                    roots[ra] = rb;
                }
            }
            return Math.max(ranks[ra], ranks[rb]);
        }

        public int find(int a) {
            if (roots[a] != a) {
                roots[a] = find(roots[a]);
            }
            return roots[a];
        }
    }
}
