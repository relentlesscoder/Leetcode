package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/26/19.
 * #934 https://leetcode.com/problems/shortest-bridge/
 */
public class ShortestBridge {

	private int[][] dir;

	public int shortestBridge(int[][] A) {
		LinkedList<int[]> queue = new LinkedList<>();
		dir = new int[][]{
			{1, -1, 0, 0}, {0, 0, 1, -1}
		};
		int r = A.length;
		int c = A[0].length;
		int x = -1, y = -1;
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(A[i][j] == 1){
					x = i;
					y = j;
					break;
				}
			}
		}

		dfs(x, y, A, queue);

		int depth = 1;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size > 0){
				int[] p = queue.poll();
				if(A[p[0]][p[1]] == -1){
					size--;
					continue;
				}
				A[p[0]][p[1]] = -1;
				for(int i = 0; i < 4; i++){
					int m = p[0] + dir[0][i];
					int n = p[1] + dir[1][i];
					if(m >= 0 && m < r && n >= 0 && n < c && A[m][n] != -1){
						if(A[m][n] == 1){
							return depth;
						}else{
							queue.offer(new int[]{m, n});
						}
					}
				}
				size--;
			}
			depth++;
		}
		return depth;
	}

	private void dfs(int x, int y, int[][] A, LinkedList<int[]> queue){
		A[x][y] = -1;
		for(int i = 0; i < 4; i++){
			int m = x + dir[0][i];
			int n = y + dir[1][i];
			if(m >= 0 && m < A.length && n >= 0 && n < A[0].length && A[m][n] != -1){
				if(A[m][n] == 0){
					queue.offer(new int[]{m, n});
				}else{
					dfs(m, n, A, queue);
				}
			}
		}
	}
}
