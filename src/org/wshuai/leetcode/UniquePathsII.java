package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/6/16.
 */
public class UniquePathsII {

	public int uniquePathsWithObstaclesSimple(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
			return 0;
		}

		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		int[] aux = new int[n];
		Arrays.fill(aux, 1);
		aux[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
		for (int i = 1; i < n; i++) {
			aux[i] = obstacleGrid[0][i] == 1 ? 0 : aux[i - 1];
		}

		for (int i = 1; i < m; i++) {
			aux[0] = obstacleGrid[i][0] == 1 ? 0 : aux[0];
			for (int j = 1; j < n; j++) {
				aux[j] = obstacleGrid[i][j] == 1 ? 0 : aux[j] + aux[j - 1];
			}
		}

		return aux[n - 1];
	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
			return 0;
		}

		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		int[] aux = new int[n];
		boolean blocked = false;
		for (int i = 0; i < n; i++) {
			aux[i] = blocked || obstacleGrid[0][i] == 1 ? 0 : 1;
			blocked = blocked || (aux[i] == 0);
		}

		blocked = (aux[0] == 0);
		for (int i = 1; i < m; i++) {
			aux[0] = blocked || obstacleGrid[i][0] == 1 ? 0 : 1;
			blocked = blocked || (aux[0] == 0);
			for (int j = 1; j < n; j++) {
				aux[j] = obstacleGrid[i][j] == 1 ? 0 : aux[j] + aux[j - 1];
			}
		}

		return aux[n - 1];
	}
}
