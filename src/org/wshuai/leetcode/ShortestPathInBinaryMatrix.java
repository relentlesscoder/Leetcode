package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 9/27/19.
 * #1091 https://leetcode.com/problems/shortest-path-in-binary-matrix/
 */
public class ShortestPathInBinaryMatrix {
	// BFS
	public int shortestPathBinaryMatrix(int[][] grid) {
		if(grid[0][0] != 0){
			return -1;
		}
		int N = grid.length;
		int[][] visited = new int[N][N];
		int[][] move = new int[2][8];
		move[0] = new int[]{1, -1, 0, 0, -1, -1, 1, 1};
		move[1] = new int[]{0, 0, 1, -1, -1, 1, -1, 1};
		int min = 0;
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{0, 0});
		visited[0][0] = 1;
		while(!queue.isEmpty()){
			// records the number of nodes of the next BFS level
			int size = queue.size();
			for(int i = 0; i < size; i++){
				int[] curr = queue.pollFirst();
				if(curr[0] == N - 1 && curr[1] == N - 1){
					return min + 1;
				}
				for(int k = 0; k < 8; k++){
					int x = curr[0] + move[0][k];
					int y = curr[1] + move[1][k];
					if(x >= 0 && y >= 0 && x < N && y < N && visited[x][y] == 0 && grid[x][y] == 0){
						queue.offerLast(new int[]{x, y});
						visited[x][y] = 1;
					}
				}
			}
			min++;
		}
		return -1;
	}
}
