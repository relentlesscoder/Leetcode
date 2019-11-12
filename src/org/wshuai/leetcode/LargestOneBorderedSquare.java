package org.wshuai.leetcode;

/**
 * Created by Wei on 11/9/2019.
 * #1139 https://leetcode.com/problems/largest-1-bordered-square/
 */
public class LargestOneBorderedSquare {
	public int largest1BorderedSquare(int[][] grid) {
		int r = grid.length;
		int c = grid[0].length;
		int[][] left = new int[r][c];
		int[][] top = new int[r][c];
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(grid[i][j] > 0){
					left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
					top[i][j] = i > 0 ? top[i - 1][j] + 1 : 1;
				}
			}
		}

		for(int l = Math.min(r, c); l > 0; l--){
			for(int i = 0; i < r - l + 1; i++){
				for(int j = 0; j < c - l + 1; j++){
					if(top[i + l - 1][j] >= l
						&& top[i + l - 1][j + l - 1] >= l
						&& left[i][j + l - 1] >= l
						&& left[i + l - 1][j + l - 1] >= l){
						return l * l;
					}
				}
			}
		}
		return 0;
	}
}
