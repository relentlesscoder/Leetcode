package org.wshuai.leetcode;

/**
 * Created by Wei on 08/20/2019.
 * #0840 https://leetcode.com/problems/magic-squares-in-grid/
 */
public class MagicSquaresInGrid {

	// https://www.dr-mikes-math-games-for-kids.com/3x3-magic-square.html
	public int numMagicSquaresInside(int[][] grid) {
		int res = 0, m = grid.length, n = grid[0].length;
		if(m < 3 || n < 3){
			return 0;
		}
		for(int i = 0; i <= m - 3; i++){
			for(int j = 0; j <= n - 3; j++){
				if(grid[i + 1][j + 1] != 5){
					continue;
				}
				if(magicSqaure(grid[i][j], grid[i][j + 1], grid[i][j + 2]
						,grid[i + 1][j], grid[i + 1][j + 1], grid[i + 1][j + 2]
						,grid[i + 2][j], grid[i + 2][j + 1], grid[i + 2][j + 2])){
					res++;
				}
			}
		}
		return res;
	}

	private boolean magicSqaure(int... nums){
		int[] count = new int[16];
		for(int n: nums){
			count[n]++;
		}
		for(int i = 1; i < 10; i++){
			if(count[i] != 1){
				return false;
			}
		}
		return (nums[0] + nums[1] + nums[2] == 15 &&
				nums[3] + nums[4] + nums[5] == 15 &&
				nums[6] + nums[7] + nums[8] == 15 &&
				nums[0] + nums[3] + nums[6] == 15 &&
				nums[1] + nums[4] + nums[7] == 15 &&
				nums[2] + nums[5] + nums[8] == 15 &&
				nums[0] + nums[4] + nums[8] == 15 &&
				nums[2] + nums[4] + nums[6] == 15
		);
	}
}
