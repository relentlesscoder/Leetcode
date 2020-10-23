package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 04/04/2017.
 * #0505 https://leetcode.com/problems/the-maze-ii/
 */
public class TheMazeII {

	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(m*n), space O(m*n)
	public int shortestDistance(int[][] maze, int[] start, int[] destination) {
		int m = maze.length, n = maze[0].length;
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		boolean[][] visited = new boolean[m][n];
		queue.offer(new int[]{start[0], start[1], 0});
		while(!queue.isEmpty()){
			int[] cur = queue.poll();
			if(cur[0] == destination[0] && cur[1] == destination[1]){
				return cur[2];
			}
			if(!visited[cur[0]][cur[1]]){
				visited[cur[0]][cur[1]] = true;
				for(int k = 0; k < 4; k++){
					queue.offer(move(cur, k, maze));
				}
			}
		}
		return -1;
	}

	private int[] move(int[] cur, int k, int[][] maze){
		int x = cur[0], y = cur[1], dist = cur[2];
		while(x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0){
			x += dirs[k];
			y += dirs[k + 1];
			dist++;
		}
		x -= dirs[k];
		y -= dirs[k + 1];
		dist--;
		return new int[]{x, y, dist};
	}
}
