package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/26/2019.
 * #0934 https://leetcode.com/problems/shortest-bridge/
 */
public class ShortestBridge {

	private static final int[] DIRECTIONS = new int[]{0, -1, 0, 1, 0};

	// time O(m*n), space O(m*n)
	public int shortestBridge(int[][] A) {
		LinkedList<int[]> queue = new LinkedList<>();
		int m = A.length, n = A[0].length, a = -1, b = -1;
		boolean stop = false;
		for(int i = 0; i < m && !stop; i++){
			for(int j = 0; j < n && !stop; j++){
				if(A[i][j] == 1){
					a = i;
					b = j;
					stop = true;
				}
			}
		}

		// dfs find the surrounding water cells of the first island
		dfs(a, b, A, m, n, queue);

		// bfs to find the shortest path to the second island
		int steps = 1;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				for(int k = 0; k < 4; k++){
					int x = cur[0] + DIRECTIONS[k], y = cur[1] + DIRECTIONS[k + 1];
					if(x < 0 || x >= m || y < 0 || y >= n || A[x][y] == -1){
						continue;
					}
					if(A[x][y] == 1){
						return steps;
					}else{
						A[x][y] = -1;
						queue.offerLast(new int[]{x, y});
					}
				}
			}
			steps++;
		}
		return -1;
	}

	private void dfs(int i, int j, int[][] A, int m, int n, LinkedList<int[]> queue){
		A[i][j] = -1;
		for(int k = 0; k < 4; k++){
			int x = i + DIRECTIONS[k], y = j + DIRECTIONS[k + 1];
			if(x < 0 || x >= m || y < 0 || y >= n || A[x][y] == -1){
				continue;
			}
			if(A[x][y] == 1){
				dfs(x, y, A, m, n, queue);
			}else{
				A[x][y] = -1;
				queue.offerLast(new int[]{x, y});
			}
		}
	}
}
