package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2016.
 * #0130 https://leetcode.com/problems/surrounded-regions/
 */
public class SurroundedRegions {
	private static int[][] dirs = new int[][]{
			{1, -1, 0, 0}, {0, 0, 1, -1}
	};

	// time O(r*c), space O(1)
	public void solve(char[][] board) {
		if(board == null || board.length == 0 || board[0].length == 0){
			return;
		}
		int r = board.length, c = board[0].length;
		for(int i = 0; i < c; i++){
			if(board[0][i] == 'O'){
				dfs(0, i, board, r, c);
			}
			if(board[r - 1][i] == 'O'){
				dfs(r - 1, i, board, r, c);
			}
		}
		for(int i = 1; i < r - 1; i++){
			if(board[i][0] == 'O'){
				dfs(i, 0, board, r, c);
			}
			if(board[i][c - 1] == 'O'){
				dfs(i, c - 1, board, r, c);
			}
		}
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(board[i][j] == 'X'){
					continue;
				}
				board[i][j] = (board[i][j] == 'O' ? 'X' : 'O');
			}
		}
		return;
	}

	private void dfs(int i, int j, char[][] board, int r, int c){
		if(i < 0 || j < 0 || i >= r || j >= c || board[i][j] != 'O'){
			return;
		}
		board[i][j] = 'V';
		for(int k = 0; k < 4; k++){
			int x = i + dirs[0][k];
			int y = j + dirs[1][k];
			dfs(x, y, board, r, c);
		}
	}
}
