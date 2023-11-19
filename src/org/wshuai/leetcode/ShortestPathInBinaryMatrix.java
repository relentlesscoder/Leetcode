package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/27/2019.
 * #1091 https://leetcode.com/problems/shortest-path-in-binary-matrix/
 */
public class ShortestPathInBinaryMatrix {

	private static final int[][] DIRECTIONS = new int[][]{
		{1, -1, 0, 0, 1, 1, -1, -1},
		{0, 0, 1, -1, 1, -1, 1, -1}
	};

	// time O(n^2), space O(1)
	public int shortestPathBinaryMatrix(int[][] grid) {
		if(grid[0][0] != 0){
			return -1;
		}
		int n = grid.length, steps = 0;
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{0, 0});
		grid[0][0] = -1;
		while(!queue.isEmpty()){
			steps++;
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				if(cur[0] == n - 1 && cur[1] == n - 1){
					return steps;
				}
				for(int k = 0; k < 8; k++){
					int x = cur[0] + DIRECTIONS[0][k], y = cur[1] + DIRECTIONS[1][k];
					if(x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0){
						grid[x][y] = -1;
						queue.offerLast(new int[]{x, y});
					}
				}
			}
		}
		return -1;
	}
}
