package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/30/2023.
 * #2812 https://leetcode.com/problems/find-the-safest-path-in-a-grid/
 */
public class FindTheSafestPathInAGrid {
    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(n^2 * α(n^2)), space O(n^2)
    public int maximumSafenessFactorUnionFind(List<List<Integer>> grid) {
        int n = grid.size(), m = n * n;
        List<List<int[]>> groups = new ArrayList<>();
        List<int[]> cells = new ArrayList<>();
        int[][] sf = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    cells.add(new int[]{i, j});
                } else {
                    sf[i][j] = -1;
                }
            }
        }
        while (!cells.isEmpty()) {
            groups.add(cells);
            List<int[]> temp = new ArrayList<>();
            for (int[] c : cells) {
                for (int d = 0; d < 4; d++) {
                    int x = c[0] + DIRS[d], y = c[1] + DIRS[d + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && sf[x][y] == -1) {
                        temp.add(new int[]{x, y});
                        sf[x][y] = groups.size();
                    }
                }
            }
            cells = temp;
        }
        UnionFind uf = new UnionFind(m);
        // 逆序遍历安全系数，用连通集来连通大于等于当前安全系数的相邻的格子。最早
        // 使得格子[0,0]和[n - 1, n - 1]连通的安全系数即为答案。
        for (int i = groups.size() - 1; i >= 0; i--) {
            for (int[] curr : groups.get(i)) {
                int pos = curr[0] * n + curr[1];
                for (int d = 0; d < 4; d++) {
                    int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && sf[x][y] >= sf[curr[0]][curr[1]]) {
                        uf.union(pos, x * n + y);
                    }
                }
            }
            if (uf.find(0) == uf.find(m - 1)) {
                return i;
            }
        }
        return -1;
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

    // time O(n^2 * log(n)), space O(n^2)
    public int maximumSafenessFactorDijkstra1(List<List<Integer>> grid) {
        int n = grid.size();
        List<List<int[]>> groups = new ArrayList<>();
        List<int[]> cells = new ArrayList<>();
        int[][] sf = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    cells.add(new int[]{i, j});
                } else {
                    sf[i][j] = -1;
                }
            }
        }
        // 用一个multi source BFS来计算所有格子的安全系数
        // 示例1:
        // [[0,0,1],
        //  [0,0,0],
        //  [1,0,0]]
        // 安全系数矩阵：
        // [[2,1,0],
        //  [1,2,1],
        //  [0,1,2]]
        while (!cells.isEmpty()) {
            groups.add(cells);
            List<int[]> temp = new ArrayList<>();
            for (int[] c : cells) {
                for (int d = 0; d < 4; d++) {
                    int x = c[0] + DIRS[d], y = c[1] + DIRS[d + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && sf[x][y] == -1) {
                        temp.add(new int[]{x, y});
                        sf[x][y] = groups.size();
                    }
                }
            }
            cells = temp;
        }
        // 使用Dijkstra算法来计算最大安全系数，维护一个最大堆每次取出当前的最大安全系数
        // 进行计算。
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int[][] dist = new int[n][n];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MIN_VALUE);
        }
        queue.offer(new int[]{sf[0][0], 0, 0});
        dist[0][0] = sf[0][0];
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[1] == n - 1 && curr[2] == n - 1) {
                return curr[0];
            }
            for (int d = 0; d < 4; d++) {
                int x = curr[1] + DIRS[d], y = curr[2] + DIRS[d + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && sf[x][y] != -1) {
                    // 注意这里我们只能取当前值与相邻格子的最小值，因为整个路径的安全系数
                    // 是由最低的那个格子的安全系数决定的
                    if (dist[x][y] < Math.min(curr[0], sf[x][y])) {
                        dist[x][y] = Math.min(curr[0], sf[x][y]);
                        queue.offer(new int[]{dist[x][y], x, y});
                    }
                }
            }
        }
        return dist[n - 1][n - 1];
    }

    // time O(n^2), space O(n^2)
    public int maximumSafenessFactorDijkstra2(List<List<Integer>> grid) {
        int n = grid.size();
        List<List<int[]>> groups = new ArrayList<>();
        List<int[]> cells = new ArrayList<>();
        int[][] sf = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    cells.add(new int[]{i, j});
                } else {
                    sf[i][j] = -1;
                }
            }
        }
        while (!cells.isEmpty()) {
            groups.add(cells);
            List<int[]> temp = new ArrayList<>();
            for (int[] c : cells) {
                for (int d = 0; d < 4; d++) {
                    int x = c[0] + DIRS[d], y = c[1] + DIRS[d + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && sf[x][y] == -1) {
                        temp.add(new int[]{x, y});
                        sf[x][y] = groups.size();
                    }
                }
            }
            cells = temp;
        }
        // 效率比最大堆要低
        Deque<int[]> queue = new ArrayDeque<>();
        int[][] dist = new int[n][n];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MIN_VALUE);
        }
        queue.offer(new int[]{sf[0][0], 0, 0});
        dist[0][0] = sf[0][0];
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int d = 0; d < 4; d++) {
                int x = curr[1] + DIRS[d], y = curr[2] + DIRS[d + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && sf[x][y] != -1) {
                    if (dist[x][y] < Math.min(curr[0], sf[x][y])) {
                        dist[x][y] = Math.min(curr[0], sf[x][y]);
                        queue.offer(new int[]{dist[x][y], x, y});
                    }
                }
            }
        }
        return dist[n - 1][n - 1];
    }

    // time O(n^2 * log(n)), space O(n^2)
    public int maximumSafenessFactorBinarySearch(List<List<Integer>> grid) {
        int n = grid.size();
        List<List<int[]>> groups = new ArrayList<>();
        List<int[]> cells = new ArrayList<>();
        int[][] sf = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    cells.add(new int[]{i, j});
                } else {
                    sf[i][j] = -1;
                }
            }
        }
        while (!cells.isEmpty()) {
            groups.add(cells);
            List<int[]> temp = new ArrayList<>();
            for (int[] c : cells) {
                for (int d = 0; d < 4; d++) {
                    int x = c[0] + DIRS[d], y = c[1] + DIRS[d + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && sf[x][y] == -1) {
                        temp.add(new int[]{x, y});
                        sf[x][y] = groups.size();
                    }
                }
            }
            cells = temp;
        }
        int low = 0, high = groups.size() - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            boolean isValid = false;
            if (sf[0][0] >= mid && sf[n - 1][n - 1] >= mid) {
                Deque<int[]> queue = new ArrayDeque<>();
                boolean[][] visited = new boolean[n][n];
                queue.offer(new int[]{0, 0});
                visited[0][0] = true;
                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    if (curr[0] == n - 1 && curr[1] == n - 1) {
                        isValid = true;
                        break;
                    }
                    for (int d = 0; d < 4; d++) {
                        int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n
                                && !visited[x][y] && sf[x][y] >= mid) {
                            visited[x][y] = true;
                            queue.offer(new int[]{x, y});
                        }
                    }
                }
            }
            if (isValid) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
