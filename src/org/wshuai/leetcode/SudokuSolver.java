package org.wshuai.leetcode;

/**
 * Created by Wei on 11/9/16.
 * #37 https://leetcode.com/problems/sudoku-solver/
 */
public class SudokuSolver {
	public void solveSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9) {
			return;
		}
		solveSudokuUtil(board, 0, 0);
	}

	private boolean solveSudokuUtil(char[][] board, int i, int j) {
		if (i == 9) {
			return true;
		}
		if (j >= 9) {
			return solveSudokuUtil(board, i + 1, 0);
		}
		if (board[i][j] == '.') {
			for (int k = 1; k <= 9; k++) {
				board[i][j] = (char) (k + '0');
				if (isValid(board, i, j)) {
					if (solveSudokuUtil(board, i, j + 1)) {
						return true;
					}
				}
				board[i][j] = '.';
			}
		} else {
			return solveSudokuUtil(board, i, j + 1);
		}
		return false;
	}

	private boolean isValid(char[][] board, int i, int j) {
		for (int k = 0; k < 9; k++) {
			if (k != i && board[k][j] == board[i][j]) {
				return false;
			}
		}

		for (int k = 0; k < 9; k++) {
			if (k != j && board[i][j] == board[i][k]) {
				return false;
			}
		}

		int r = i / 3 * 3;
		int c = j / 3 * 3;
		for (int x = r; x < r + 3; x++) {
			for (int y = c; y < c + 3; y++) {
				if (x != i && y != j && board[x][y] == board[i][j]) {
					return false;
				}
			}
		}

		return true;
	}
}
