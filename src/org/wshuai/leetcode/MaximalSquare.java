package org.wshuai.leetcode;

/**
 * Created by Wei on 10/5/16.
 */
public class MaximalSquare {
	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[][] aux = new int[rLen][cLen];

		for (int i = 0; i < cLen; i++) {
			aux[0][i] = Character.getNumericValue(matrix[0][i]);
		}

		for (int i = 0; i < rLen; i++) {
			aux[i][0] = Character.getNumericValue(matrix[i][0]);
		}

		for (int i = 1; i < rLen; i++) {
			for (int j = 1; j < cLen; j++) {
				if (matrix[i][j] == '1') {
					int min = Math.min(aux[i - 1][j], aux[i][j - 1]);
					min = Math.min(min, aux[i - 1][j - 1]);
					aux[i][j] = min + 1;
				} else {
					aux[i][j] = 0;
				}
			}
		}

		int max = 0;
		for (int i = 0; i < rLen; i++) {
			for (int j = 0; j < cLen; j++) {
				max = Math.max(max, aux[i][j]);
			}
		}

		return max * max;
	}
}
