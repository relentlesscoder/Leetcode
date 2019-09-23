package org.wshuai.leetcode;

/**
 * Created by Wei on 7/28/2017.
 * #329 https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPathInAMatrix {

	//DFS with cache
	public int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		int max = 0;
		int[][] move = new int[][]{{1, -1, 0, 0}, {0, 0, 1, -1}};
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] aux = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int depth = longestIncreasingPathDFS(i, j, row, col, matrix, aux, move);
				max = Math.max(max, depth);
			}
		}
		return max;
	}

	private int longestIncreasingPathDFS(int i, int j, int row, int col, int[][] matrix, int[][] aux, int[][] move) {
		if (aux[i][j] > 0) {
			return aux[i][j];
		}

		int max = 1;
		for (int k = 0; k < 4; k++) {
			int x = i + move[0][k];
			int y = j + move[1][k];
			if (x >= 0 && x < row && y >= 0 && y < col && matrix[x][y] > matrix[i][j]) {
				int depth = 1 + longestIncreasingPathDFS(x, y, row, col, matrix, aux, move);
				max = Math.max(max, depth);
			}
		}
		aux[i][j] = max;
		return max;
	}
}
