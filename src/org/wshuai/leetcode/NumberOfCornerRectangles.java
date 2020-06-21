package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2019.
 * #0750 https://leetcode.com/problems/number-of-corner-rectangles/
 */
public class NumberOfCornerRectangles {

	// time O(m^2*n)
	public int countCornerRectangles(int[][] grid) {
		int res = 0, m = grid.length, n = grid[0].length;
		for(int i = 0; i < m - 1; i++){
			for(int j = i + 1; j < m; j++){
				int c = 0;
				for(int k = 0; k < n; k++){
					// find two cells on the same column both equal to 1 between the two rows
					if(grid[i][k] == 1 && grid[j][k] == 1){
						c++;
					}
				}
				while(c-- > 0){
					res += c;
				}
			}
		}
		return res;
	}
}
