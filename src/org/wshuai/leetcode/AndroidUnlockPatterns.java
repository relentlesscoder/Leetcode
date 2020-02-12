package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/2016.
 * #0351 https://leetcode.com/problems/android-unlock-patterns/
 */
public class AndroidUnlockPatterns {
	// time O(n!)
	public int numberOfPatterns(int m, int n) {
		int res = 0;
		int[][] grid = new int[10][10];
		grid[1][3] = grid[3][1] = 2;
		grid[3][9] = grid[9][3] = 6;
		grid[9][7] = grid[7][9] = 8;
		grid[7][1] = grid[1][7] = 4;
		grid[2][8] = grid[8][2] = grid[4][6]
			= grid[6][4] = grid[1][9] = grid[9][1]
			= grid[3][7] = grid[7][3] = 5;
		boolean[] visited = new boolean[10];
		for(int i = m; i <= n; i++){
			res += dfs(grid, visited, 1, i - 1) * 4; // 1, 3, 7, 9 are symmetric
			res += dfs(grid, visited, 2, i - 1) * 4; // 2, 4, 6, 8 are symmetric
			res += dfs(grid, visited, 5, i - 1);     // 5
		}
		return res;
	}

	private int dfs(int[][] grid, boolean[] visited, int cur, int count){
		if(count == 0){
			return 1;
		}
		visited[cur] = true;
		int res = 0;
		for(int i = 1; i <= 9; i++){
			if(!visited[i] && (grid[cur][i] == 0 || visited[grid[cur][i]])){
				res += dfs(grid, visited, i, count - 1);
			}
		}
		visited[cur] = false;
		return res;
	}
}
