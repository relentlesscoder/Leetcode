package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2023.
 * #2088 https://leetcode.com/problems/count-fertile-pyramids-in-a-land/
 */
public class CountFertilePyramidsInALand {

	// time O(m * n), space O(m * n)
	public int countPyramids(int[][] grid) {
		int[][] inverseGrid = inverse(grid);
		return dp(grid) + dp(inverseGrid);
	}

	private int dp(int[][] grid) {
		int res = 0, m = grid.length, n = grid[0].length;
		// https://leetcode.com/problems/count-fertile-pyramids-in-a-land/solutions/1598908/simple-dp-solution-with-explanation-c-o-n-m/
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n - 1; j++) {
				if (grid[i][j] > 0) {
					// For cell [i, j], if it can be used as the peak of pyramids. Then the valid pyramid is count - 1.
					//         1
					//      1 1 1
					//    1 1 1 1 1
					grid[i][j] = Math.min(Math.min(grid[i - 1][j], grid[i - 1][j - 1]), grid[i - 1][j + 1]) + 1;
					res += grid[i][j] - 1;
				}
			}
		}
		return res;
	}

	private int[][] inverse(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] res = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				res[i][j] = grid[m - i - 1][j];
			}
		}
		return res;
	}
}
