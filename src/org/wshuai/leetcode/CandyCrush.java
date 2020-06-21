package org.wshuai.leetcode;

/**
 * Created by Wei on 09/12/2019.
 * #0723 https://leetcode.com/problems/candy-crush/
 */
public class CandyCrush {
	// time O((m*n)*(m*n))
	public int[][] candyCrush(int[][] board) {
		int m = board.length, n = board[0].length;
		boolean todo = false;
		// mark all candy to be crushed to negative
		for(int i = 0; i < m; i++){
			for(int j = 0; j + 2 < n; j++){
				int v = Math.abs(board[i][j]);
				if(v > 0 && Math.abs(board[i][j + 1]) == v && Math.abs(board[i][j + 2]) == v){
					board[i][j] = board[i][j + 1] = board[i][j + 2] = -v;
					todo = true;
				}
			}
		}
		for(int j = 0; j < n; j++){
			for(int i = 0; i + 2 < m; i++){
				int v = Math.abs(board[i][j]);
				if(v > 0 && Math.abs(board[i + 1][j]) == v && Math.abs(board[i + 2][j]) == v){
					board[i][j] = board[i + 1][j] = board[i + 2][j] = -v;
					todo = true;
				}
			}
		}
		// for each column, drop the candies vertically
		for(int j = 0; j < n; j++){
			int row = m - 1;
			for(int i = m - 1; i >= 0; i--){
				if(board[i][j] > 0){
					board[row--][j] = board[i][j];
				}
			}
			while(row >= 0){
				board[row--][j] = 0;
			}
		}
		return todo ? candyCrush(board) : board;
	}
}
