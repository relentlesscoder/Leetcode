package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/01/2023.
 * #2503 https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/
 */
public class MaximumNumberOfPointsFromGridQueries {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n * log(m * n) + k * log(k)), space O(m * n)
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length, k = queries.length;
        int[][] qs = new int[k][2];
        int[] res = new int[k];
        Arrays.setAll(qs, i -> new int[]{queries[i], i});
        // 离线排序
        Arrays.sort(qs, (a, b) -> a[0] - b[0]); // O(k * log(k))
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        minQueue.offer(new int[]{0, 0, grid[0][0]});
        grid[0][0] = -1;
        for (int i = 0, score = 0; i < k; i++) {
            // 维护一个最小堆，每次弹出所有比当前查询值小的值
            while (!minQueue.isEmpty() && minQueue.peek()[2] < qs[i][0]) { // O(m * n * log(m * n))
                int[] curr = minQueue.poll();
                score++;
                // 将相邻的格子入队
                for (int d = 0; d < 4; d++) {
                    int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != -1) {
                        minQueue.offer(new int[]{x, y, grid[x][y]});
                        grid[x][y] = -1;
                    }
                }
            }
            res[qs[i][1]] = score;
        }
        return res;
    }

    // time O(m * n * log(m * n) + k * log(k)), space O(m * n)
    public int[] maxPointsUnionFind(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length, k = queries.length;
        int[][] qs = new int[k][2];
        int[] res = new int[k];
        Arrays.setAll(qs, i -> new int[]{queries[i], i});
        // 离线排序
        Arrays.sort(qs, (a, b) -> a[0] - b[0]); // O(k * log(k))
        int[][] sorted = new int[m * n][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sorted[i * n + j] = new int[]{i, j, grid[i][j]};
            }
        }
        // 把网格中的数从小到大排序
        Arrays.sort(sorted, (a, b) -> a[2] - b[2]); // O(m * n * log(m * n))
        UnionFind uf = new UnionFind(m * n);
        for (int i = 0, j = 0; i < k; i++) {
            // 把所有小于当前查询值的格子与跟它相邻的相等或更小的格子连通
            while (j < m * n && sorted[j][2] < qs[i][0]) {
                for (int d = 0; d < 4; d++) {
                    int x = sorted[j][0] + DIRS[d], y = sorted[j][1] + DIRS[d + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n
                            && grid[x][y] <= grid[sorted[j][0]][sorted[j][1]]) {
                        uf.union(x * n + y, sorted[j][0] * n + sorted[j][1]);
                    }
                }
                j++;
            }
            // 答案即为第一个格子所属连通分量的大小
            res[qs[i][1]] = grid[0][0] >= qs[i][0] ? 0 : uf.getRank(0);
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
            if (ranks[rx] > ranks[ry]) {
                ranks[rx] += ranks[ry];
                roots[ry] = rx;
            } else {
                ranks[ry] += ranks[rx];
                roots[rx] = ry;
            }
        }
    }
}
