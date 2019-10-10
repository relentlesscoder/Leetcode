package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 9/26/19.
 * #542 https://leetcode.com/problems/01-matrix/
 */
public class ZeroOneMatrix {
	public int[][] updateMatrix(int[][] matrix) {
		int[][] move = new int[2][4];
		move[0] = new int[]{1, -1, 0, 0};
		move[1] = new int[]{0, 0, 1, -1};
		int r = matrix.length;
		int c = matrix[0].length;
		int[][] visited = new int[r][c];
		LinkedList<int[]> queue = new LinkedList<>();
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				matrix[i][j] = matrix[i][j] == 0 ? 0 : Integer.MAX_VALUE;
			}
		}
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(matrix[i][j] == 0){
					visited[i][j] = 1;
					for(int k = 0; k < 4; k++){
						int x = i + move[0][k];
						int y = j + move[1][k];
						if(x >= 0 && y >= 0 && x < r && y < c && matrix[x][y] == Integer.MAX_VALUE && visited[x][y] != 1){
							matrix[x][y] = 1;
							visited[x][y] = 1;
							queue.offerLast(new int[]{x, y});
						}
					}
				}
			}
		}
		Set<int[]> set = new HashSet<>();
		while(!queue.isEmpty()){
			int[] curr = queue.pollFirst();
			for(int k = 0; k < 4; k++){
				int x = curr[0] + move[0][k];
				int y = curr[1] + move[1][k];
				if(x >= 0 && y >= 0 && x < r && y < c && matrix[x][y] == Integer.MAX_VALUE && visited[x][y] != 1){
					matrix[x][y] = Math.min(matrix[x][y], matrix[curr[0]][curr[1]] + 1);
					set.add(new int[]{x, y});
				}
			}
			if(queue.isEmpty()){
				for(int[] next: set){
					visited[next[0]][next[1]] = 1;
					queue.offerLast(new int[]{next[0], next[1]});
				}
				set = new HashSet<>();
			}
		}
		return matrix;
	}
}
