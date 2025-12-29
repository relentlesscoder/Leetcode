package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Created by Wei on 11/23/2019.
 * #0499 https://leetcode.com/problems/the-maze-iii/
 */
public class TheMazeIII {

    private static final int[][] DIRS = new int[][]{{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private static final char[] MOVES = new char[]{'d', 'l', 'r', 'u'};

    // time O(m * n * L), space O(m * n * L)
    public String findShortestWayBFS(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length, sx = ball[0], sy = ball[1],
                dx = hole[0], dy = hole[1];
        int[][] cost = new int[m][n];
        String[][] path = new String[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
            Arrays.fill(path[i], "impossible");
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 0});
        cost[sx][sy] = 0;
        path[sx][sy] = "";
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = curr[0], y = curr[1], steps = 0;
                while (x + DIRS[i][0] >= 0 && x + DIRS[i][0] < m
                        && y + DIRS[i][1] >= 0 && y + DIRS[i][1] < n
                        && maze[x + DIRS[i][0]][y + DIRS[i][1]] == 0) {
                    x += DIRS[i][0];
                    y += DIRS[i][1];
                    steps++;
                    if (x == dx && y == dy) {
                        break;
                    }
                }
                int dist = cost[curr[0]][curr[1]] + steps;
                String next = path[curr[0]][curr[1]] + MOVES[i];
                if (cost[x][y] > dist
                        || (cost[x][y] == dist && path[x][y].compareTo(next) > 0)) {
                    cost[x][y] = dist;
                    path[x][y] = next;
                    queue.offer(new int[]{x, y, cost[x][y]});
                }
            }
        }
        return path[dx][dy];
    }

	private record Ball(int x, int y, int cost, String path) {}

	// time O(m * n * log(m * n)), space O(m * n * L)
	public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		int m = maze.length, n = maze[0].length, sx = ball[0], sy = ball[1],
				dx = hole[0], dy = hole[1];
		boolean[][] visited = new boolean[m][n];
		PriorityQueue<Ball> queue = new PriorityQueue<>((a, b) -> a.cost == b.cost ?
				a.path.compareTo(b.path) : a.cost - b.cost);
		queue.offer(new Ball(sx, sy, 0, ""));
		while (!queue.isEmpty()) {
			Ball curr = queue.poll();
			if (curr.x == dx && curr.y == dy) {
				return curr.path;
			}
			// 注意需要在dequeue的时候判断当前格子是否已经被遍历，因为我们需要找到最短路径
			// 而不是第一个路径。如果题目改成返回任何一个可行的路径则可以在enqueue之前更新
			// 遍历信息。
			if (!visited[curr.x][curr.y]) {
				visited[curr.x][curr.y] = true;
				for (int i = 0; i < 4; i++) {
					int x = curr.x, y = curr.y, steps = curr.cost;
					String path = curr.path + MOVES[i];
					while (x + DIRS[i][0] >= 0 && x + DIRS[i][0] < m
							&& y + DIRS[i][1] >= 0 && y + DIRS[i][1] < n
							&& maze[x + DIRS[i][0]][y + DIRS[i][1]] == 0) {
						x += DIRS[i][0];
						y += DIRS[i][1];
						steps++;
						if (x == dx && y == dy) {
							break;
						}
					}
					queue.offer(new Ball(x, y, steps, path));
				}
			}
		}
		return "impossible";
	}
}
