package org.wshuai.leetcode;

/**
 * Created by Wei on 11/6/16.
 * #289 https://leetcode.com/problems/game-of-life/
 */
public class GameOfLife {
	//O(n^2)
	public void gameOfLife(int[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return;
		}
		int rows = board.length;
		int cols = board[0].length;

		//Use 4 codes to represent cell status:
		//0 -> dead to dead
		//1 -> live to live
		//2 -> dead to live
		//3 -> live to dead
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int live = 0;
				//up
				if (i - 1 >= 0 && (board[i - 1][j] == 1 || board[i - 1][j] == 3)) {
					live++;
				}
				//down
				if (i + 1 < rows && (board[i + 1][j] == 1 || board[i + 1][j] == 3)) {
					live++;
				}
				//left
				if (j - 1 >= 0 && (board[i][j - 1] == 1 || board[i][j - 1] == 3)) {
					live++;
				}
				//right
				if (j + 1 < cols && (board[i][j + 1] == 1 || board[i][j + 1] == 3)) {
					live++;
				}
				//up-left
				if (i - 1 >= 0 && j - 1 >= 0 && (board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == 3)) {
					live++;
				}
				//up-right
				if (i - 1 >= 0 && j + 1 < cols && (board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == 3)) {
					live++;
				}
				//bottom-left
				if (i + 1 < rows && j - 1 >= 0 && (board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == 3)) {
					live++;
				}
				//bottom-right
				if (i + 1 < rows && j + 1 < cols && (board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == 3)) {
					live++;
				}
				if (board[i][j] == 0 && live == 3) {
					board[i][j] = 2;
				}
				if (board[i][j] == 1 && (live < 2 || live > 3)) {
					board[i][j] = 3;
				}
			}
		}

		//Update cells with value of 2 or 3
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int val = board[i][j];
				if (val == 2) {
					board[i][j] = 1;
				} else if (val == 3) {
					board[i][j] = 0;
				}
			}
		}
	}
}
