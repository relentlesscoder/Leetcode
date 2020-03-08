package org.wshuai.leetcode;

/**
 * Created by Wei on 11/06/2016.
 * #0289 https://leetcode.com/problems/game-of-life/
 */
public class GameOfLife {
	private static final int[][] dirs = new int[][]{
			{1, -1, 0, 0, 1, 1, -1, -1},
			{0, 0, 1, -1, 1, -1, 1, -1}
	};

	// time O(r*c)
	public void gameOfLife(int[][] board) {
		if(board == null || board.length == 0 || board[0].length == 0){
			return;
		}
		int r = board.length, c = board[0].length;
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				int lives = findLiveCells(i, j, r, c, board);
				if(board[i][j] == 1 && (lives >= 2 && lives <= 3)){
					board[i][j] += 2;
				}
				if(board[i][j] == 0 && lives == 3){
					board[i][j] += 2;
				}
			}
		}
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				board[i][j] >>= 1;
			}
		}
	}

	private int findLiveCells(int i, int j, int r, int c, int[][] board){
		int res = 0;
		for(int k = 0; k < 8; k++){
			int x = i + dirs[0][k];
			int y = j + dirs[1][k];
			if(x >= 0 && x < r && y >= 0 && y < c && (board[x][y] & 1) == 1){
				res++;
			}
		}
		return res;
	}
}
