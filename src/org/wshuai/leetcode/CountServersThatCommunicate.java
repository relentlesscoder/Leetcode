package org.wshuai.leetcode;

/**
 * Created by Wei on 11/24/19.
 * #1267 https://leetcode.com/problems/count-servers-that-communicate/
 */
public class CountServersThatCommunicate {
	public int countServers(int[][] grid) {
		int R = grid.length;
		int C = grid[0].length;
		int[] rows = new int[R];
		int[] cols = new int[C];
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				if(grid[i][j] == 1){
					rows[i]++;
					cols[j]++;
				}
			}
		}
		int res = 0;
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				if(grid[i][j] == 1 && (rows[i] > 1 || cols[j] > 1)){
					res++;
				}
			}
		}
		return res;
	}
}
