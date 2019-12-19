package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 12/18/2019.
 * #1293 https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 */
public class ShortestPathInAGridWithObstaclesElimination {
	public int shortestPath(int[][] grid, int k) {
		int r = grid.length;
		int c = grid[0].length;
		boolean[][][] visited = new boolean[r][c][k + 1];
		int[][] dirs = new int[][]{
			{1, -1, 0, 0},
			{0, 0, 1, -1}
		};
		LinkedList<int[]> queue = new LinkedList<>();
		visited[0][0][0] = true;
		queue.offerLast(new int[]{0, 0, 0});
		int step = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				if(cur[0] == r - 1 && cur[1] == c - 1){
					return step;
				}
				for(int i = 0; i < 4; i++){
					int x = cur[0] + dirs[0][i];
					int y = cur[1] + dirs[1][i];
					// every obstacle "removed" at the same level count as 1
					int count = cur[2];
					if(x >= 0 && x < r && y >= 0 && y < c){
						if(grid[x][y] == 1){
							count++;
						}
						if(count <= k && !visited[x][y][count]){
							visited[x][y][count] = true;
							queue.offerLast(new int[]{x, y, count});
						}
					}
				}
			}
			step++;
		}
		return -1;
	}
}
