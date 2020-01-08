package org.wshuai.leetcode;

/**
 * Created by Wei on 11/09/2016.
 * #0037 https://leetcode.com/problems/sudoku-solver/
 */
public class SudokuSolver {
	public void solveSudoku(char[][] board) {
		dfs(0, 0, board);
	}

	private boolean dfs(int row, int col, char[][] board){
		if(row == 9){
			return true;
		}
		if(col == 9){
			return dfs(row + 1, 0, board);
		}
		if(board[row][col] == '.'){
			for(char i = 1; i <= 9; i++){
				board[row][col] = (char)(i + '0');
				if(isValid(board, row, col)){
					if(dfs(row, col + 1, board)){
						return true;
					}
				}
				board[row][col] = '.';
			}
		}else{
			return dfs(row, col + 1, board);
		}
		return false;
	}

	private boolean isValid(char[][] board, int i, int j){
		// check columns affected by board[i][j]
		for(int k = 0; k < 9; k++){
			if(k != i && board[k][j] == board[i][j]){
				return false;
			}
		}

		// check the current row
		for(int k = 0; k < 9; k++){
			if(k != j && board[i][j] == board[i][k]){
				return false;
			}
		}

		// check the 3x3 sub-box
		int r = i/3*3;
		int c = j/3*3;
		for(int x = r; x < r + 3; x++){
			for(int y = c; y < c + 3; y++){
				if(x != i && y != j && board[x][y] == board[i][j]){
					return false;
				}
			}
		}

		return true;
	}
}
