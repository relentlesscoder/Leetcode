package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/26/2019.
 * #0542 https://leetcode.com/problems/01-matrix/
 */
public class ZeroOneMatrix {
	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(m*n), space O(m*n)
	public int[][] updateMatrix(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return matrix;
		}
		int m = matrix.length, n = matrix[0].length;
		LinkedList<int[]> queue = new LinkedList<>();
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(matrix[i][j] == 0){
					queue.offerLast(new int[]{i, j});
				}else{
					matrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		while(!queue.isEmpty()){
			int[] cur = queue.pollFirst();
			for(int k = 0; k < 4; k++){
				int x = cur[0] + dirs[k], y = cur[1] + dirs[k + 1];
				if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[cur[0]][cur[1]] + 1){
					continue;
				}
				matrix[x][y] = matrix[cur[0]][cur[1]] + 1;
				queue.offerLast(new int[]{x, y});
			}
		}
		return matrix;
	}
}
