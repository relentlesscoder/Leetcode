package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Wei on 11/24/2019.
 * #1036 https://leetcode.com/problems/escape-a-large-maze/
 */
public class EscapeALargeMaze {
    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};
    private static final int N = (int) 1e6;

    // time O(n^2), space O(n^2)
    public boolean isEscapePossibleBFSWithDiscretization(int[][] blocked, int[] source, int[] target) {
        // 分别对能对结果产生影响的行和列做离散化，然后对基于离散化的结果创建新的网格做BFS。
        // 新的网格的大小取决与blocked的大小n，最多不会超过3 x (n + 2) x 3 x (n + 2)。
        TreeSet<Integer> rows = new TreeSet<>();
        TreeSet<Integer> cols = new TreeSet<>();
        add(source[0], rows);
        add(target[0], rows);
        add(source[1], cols);
        add(target[1], cols);
        for (int[] b : blocked) {
            int x = b[0], y = b[1];
            add(x, rows);
            add(y, cols);
        }
        Map<Integer, Integer> rowMap = new HashMap<>();
        Map<Integer, Integer> colMap = new HashMap<>();
        int n = 0, m = 0;
        for (int r : rows) {
            rowMap.put(r, m++);
        }
        for (int c : cols) {
            colMap.put(c, n++);
        }
        int[][] grid = new int[m][n];
        for (int[] b : blocked) {
            grid[rowMap.get(b[0])][colMap.get(b[1])] = 1;
        }
        int sx = rowMap.get(source[0]), sy = colMap.get(source[1]);
        int tx = rowMap.get(target[0]), ty = colMap.get(target[1]);
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy});
        grid[sx][sy] = -1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == tx && curr[1] == ty) {
                return true;
            }
            for (int d = 0; d < 4; d++) {
                int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n
                        && grid[x][y] == 0) {
                    queue.add(new int[]{x, y});
                    grid[x][y] = -1;
                }
            }
        }
        return false;
    }

    private void add(int num, TreeSet<Integer> set) {
        set.add(num);
        if (num > 0) {
            set.add(num - 1);
        }
        if (num < N - 1) {
            set.add(num + 1);
        }
    }

    // time O(n^2), space O(n^2)
    public boolean isEscapePossibleLimitedBFS(int[][] blocked, int[] source, int[] target) {
        // 如果起始格子和目标格子都没有被障碍物包围则必然存在连通路径。n个格子可以包围的
        // 最大面积是以n个格子作为斜边的三角形。
        // 图见：https://leetcode.cn/problems/escape-a-large-maze/solutions/1204417/bian-cheng-xiong-bfs-acmjin-pai-ti-jie-b-dquc/
        int n = blocked.length, max = n * (n - 1) / 2;
        Set<Long> set = new HashSet<>();
        for (int[] b : blocked) {
            set.add((long) b[0] * N + b[1]);
        }
        return bfs(source, target, set, max) && bfs(target, source, set, max);
    }

    private boolean bfs(int[] source, int[] target, Set<Long> blocked, int max) {
        Deque<int[]> queue = new ArrayDeque<>();
        Set<Long> visited = new HashSet<>();
        queue.offer(source);
        visited.add((long) source[0] * N + source[1]);
        while (!queue.isEmpty() && visited.size() <= max) {
            int[] curr = queue.poll();
            if (curr[0] == target[0] && curr[1] == target[1]) {
                return true;
            }
            for (int d = 0; d < 4; d++) {
                int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                long pos = (long) x * N + y;
                if (x >= 0 && x < N && y >= 0 && y < N
                        && !blocked.contains(pos) && !visited.contains(pos)) {
                    queue.add(new int[]{x, y});
                    visited.add(pos);
                }
            }
        }
        return visited.size() > max;
    }
}
