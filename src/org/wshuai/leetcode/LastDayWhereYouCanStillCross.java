package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/03/2023.
 * #1970 https://leetcode.com/problems/last-day-where-you-can-still-cross/
 */
public class LastDayWhereYouCanStillCross {
    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(r * c), space O(r * c)
    public int latestDayToCrossUnionFind(int row, int col, int[][] cells) {
        // 正难则反：按照天数从最后一天向第一天遍历，将淹没的格子变成陆地并且用连通集
        // 连通相邻的四个陆地格子。直到第一行和最后一行属于同一个连通分量。注意我们需
        // 要分别把第一行和最后一行的所有陆地格子连通。
        int n = row * col, m = cells.length, root1 = n, root2 = n + 1;
        Set<Integer> set = new HashSet<>();
        for (int[] c : cells) {
            c[0]--;
            c[1]--;
            set.add(c[0] * col + c[1]);
        }
        UnionFind uf = new UnionFind(n + 2, set, row, col);
        // 遍历矩阵，连通所有的陆地格子
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int pos = i * col + j;
                if (!set.contains(pos)) {
                    if (i + 1 < row && !set.contains((i + 1) * col + j)) {
                        uf.union(pos, (i + 1) * col + j);
                    }
                    if (j + 1 < col && !set.contains(i * col + j + 1)) {
                        uf.union(pos, i * col + j + 1);
                    }
                }
            }
        }
        int day = m - 1;
        // 反向遍历被淹没的格子
        for (; day >= 0; day--) {
            int[] c = cells[day];
            int pos = c[0] * col + c[1];
            if (c[0] == 0) { // 如果格子在第一行，连通它到虚拟格子r * c
                uf.union(pos, root1);
            }
            if (c[0] == row - 1) { // 如果格子在最后一行，连通它到虚拟格子r * c + 1
                uf.union(pos, root2);
            }
            set.remove(pos);
            for (int d = 0; d < 4; d++) { // 连通与之相邻的格子
                int x = c[0] + DIRS[d], y = c[1] + DIRS[d + 1];
                if (x >= 0 && x < row && y >= 0 && y < col && !set.contains(x * col + y)) {
                    uf.union(pos, x * col + y);
                }
            }
            if (uf.find(root1) == uf.find(root2)) { // 第一行和最后一行已连通
                return day;
            }
        }
        return 0;
    }

    private static class UnionFind {

        private int[] root;
        private int[] rank;

        public UnionFind(int n, Set<Integer> set, int r, int c) {
            root = new int[n];
            rank = new int[n];
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
            for (int i = 0; i < c; i++) {
                if (!set.contains(i)) { // 把第一行所有陆地格子连通到虚拟格子r * c
                    root[i] = n - 2;
                    rank[n - 2]++;
                }
                if (!set.contains((r - 1) * c + i)) { // 把最后一行所有陆地格子连通到虚拟格子r * c + 1
                    root[(r - 1) * c + i] = n - 1;
                    rank[n - 1]++;
                }
            }
        }

        private int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        private void union(int x, int y) {
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

    // time O(r * c * log(m)), O(r * c)
    public int latestDayToCrossDFS(int row, int col, int[][] cells) {
		// #0778相似题, 类似的淹水问题都可以考虑用二分 + DFS/BFS来找
        int m = cells.length;
        int low = 0, high = m - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (!isConnected(mid, row, col, cells)) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low + 1;
    }

    private boolean isConnected(int day, int m, int n, int[][] cells) {
        int[][] grid = new int[m][n];
        for (int i = 0; i <= day; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0 && dfs(0, j, m, n, grid)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int m, int n, int[][] grid) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 0) {
            return false;
        }
        if (i == m - 1) {
            return true;
        }
        grid[i][j] = -1;
        for (int d = 0; d < 4; d++) {
            if (dfs(i + DIRS[d], j + DIRS[d + 1], m, n, grid)) {
                return true;
            }
        }
        return false;
    }
}
