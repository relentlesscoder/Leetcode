package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 11/23/2019.
 * #0499 https://leetcode.com/problems/the-maze-iii/
 */
public class TheMazeIII {
	private int[][] dirs = new int[][]{
		{1, 0, 0, -1},
		{0, -1, 1, 0}
	};
	private String[] map = new String[]{
		"d", "l", "r", "u"
	};

	public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		int m = maze.length, n = maze[0].length;
		PriorityQueue<Ball> pq = new PriorityQueue<>((a, b) -> a.dist == b.dist ?
			a.moves.compareTo(b.moves) : a.dist - b.dist);
		boolean[][] visited = new boolean[m][n];
		pq.offer(new Ball(0, "", ball[0], ball[1]));
		while(!pq.isEmpty()) {
			Ball curr = pq.poll();
			if (curr.x == hole[0] && curr.y == hole[1]) {
				return curr.moves;
			}

			if (!visited[curr.x][curr.y]) {
				visited[curr.x][curr.y] = true;
				for (int dir = 0; dir < 4; dir++) {
					Ball next = moveForward(maze, curr, dir, hole);
					pq.add(new Ball(next.dist, next.moves + map[dir], next.x, next.y));
				}
			}
		}
		return "impossible";
	}

	private Ball moveForward(int[][] maze, Ball curr, int dir, int[] hole) {
		int m = maze.length, n = maze[0].length;
		int nx = curr.x, ny = curr.y, dis = curr.dist;
		while (nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == 0) {
			nx += dirs[0][dir];
			ny += dirs[1][dir];
			dis++;
			if (nx == hole[0] && ny == hole[1]) {
				return new Ball(dis, curr.moves, nx, ny);
			}
		}
		nx -= dirs[0][dir];
		ny -= dirs[1][dir];
		dis--;
		return new Ball(dis, curr.moves, nx, ny);
	}

	private class Ball{
		int dist;
		String moves;
		int x;
		int y;

		public Ball(int dist, String moves, int x, int y){
			this.dist = dist;
			this.moves = moves;
			this.x = x;
			this.y = y;
		}
	}
}