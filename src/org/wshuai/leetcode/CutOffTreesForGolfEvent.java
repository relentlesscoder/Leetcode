package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 11/24/2019.
 * #0675 https://leetcode.com/problems/cut-off-trees-for-golf-event/
 */
public class CutOffTreesForGolfEvent {
    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

	// time O(m * n * log(m * n)), space O(m * n)
    public int cutOffTree(List<List<Integer>> forest) {
        int res = 0, m = forest.size(), n = forest.get(0).size();
        int[][] grid = new int[m][n];
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = forest.get(i).get(j);
                if (grid[i][j] > 1) {
                    minQueue.offer(new int[]{i, j, grid[i][j]});
                }
            }
        }
		// 从初始位置由大到小开始砍树，每次砍完一棵树用BFS得到砍下一棵树的最短距离。
		// 所有距离的总和即为答案。
        int[] curr = new int[]{0, 0};
        while (!minQueue.isEmpty()) {
            int[] next = minQueue.poll();
            int steps = bfs(grid, curr[0], curr[1], next[0], next[1]);
            if (steps == -1) {
                return -1;
            }
            res += steps;
            curr[0] = next[0];
            curr[1] = next[1];
        }
        return res;
    }

    private int bfs(int[][] grid, int sx, int sy, int tx, int ty) {
        int steps = 0, m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                if (curr[0] == tx && curr[1] == ty) {
                    return steps;
                }
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0 && !visited[x][y]) {
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
