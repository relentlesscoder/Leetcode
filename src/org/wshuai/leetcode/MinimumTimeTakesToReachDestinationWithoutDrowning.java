package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 10/01/2023.
 * #2814 https://leetcode.com/problems/minimum-time-takes-to-reach-destination-without-drowning/
 */
public class MinimumTimeTakesToReachDestinationWithoutDrowning {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(m * n)
    public int minimumSeconds(List<List<String>> land) {
		// 模拟题：在每次人移动前，洪水会先扩张一步。
        int dist = 0, m = land.size(), n = land.get(0).size(), dx = 0, dy = 0;
        Deque<int[]> water = new ArrayDeque<>(), person = new ArrayDeque<>();
        char[][] grid = new char[m][n];
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = land.get(i).get(j).charAt(0);
                grid[i][j] = c;
                if (c == 'D') {
                    dx = i;
                    dy = j;
                } else if (c == 'S') {
					// 注意：需要把出发点设成空地因为它也是可以被水淹没的
                    grid[i][j] = '.';
                    person.offer(new int[]{i, j});
                    visited[i][j] = true;
                } else if (c == '*') {
                    water.offer(new int[]{i, j});
                }
            }
        }
        while (!person.isEmpty()) {
            int size = person.size();
			// 洪水先扩张
            flood(grid, water);
            while (size-- > 0) {
                int[] curr = person.poll();
                int i = curr[0], j = curr[1];
                if (i == dx && j == dy) {
                    return dist;
                }
                for (int d = 0; d < 4; d++) {
                    int x = i + DIRS[d], y = j + DIRS[d + 1];
					// 人只可以走空地格子或者目的地格子
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length
							&& (grid[x][y] == '.' || grid[x][y] == 'D') && !visited[x][y]) {
                        visited[x][y] = true;
                        person.offer(new int[]{x, y});
                    }
                }
            }
            dist++;
        }
        return -1;
    }

    private void flood(char[][] grid, Deque<int[]> water) {
        int size = water.size();
        while (size-- > 0) {
            int[] curr = water.poll();
            for (int d = 0; d < 4; d++) {
                int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '.') {
                    grid[x][y] = '*';
                    water.offer(new int[]{x, y});
                }
            }
        }
    }
}
