package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0348 https://leetcode.com/problems/design-tic-tac-toe/
 */
public class DesignTicTacToe {
	private int n, winner, diagonalSum, antiDiagonalSum;
	private int[] rowSum, colSum;

	/** Initialize your data structure here. */
	public DesignTicTacToe(int n) {
		this.n = n;
		winner = diagonalSum = antiDiagonalSum = 0;
		rowSum = new int[n];
		colSum = new int[n];
	}

	/** Player {player} makes a move at ({row}, {col}).
	 @param row The row of the board.
	 @param col The column of the board.
	 @param player The player, can be either 1 or 2.
	 @return The current winning condition, can be either:
	 0: No one wins.
	 1: Player 1 wins.
	 2: Player 2 wins. */
	public int move(int row, int col, int player) {
		if(winner != 0){
			return winner;
		}
		int val = player == 1 ? 1 : -1;
		rowSum[row] += val;
		colSum[col] += val;
		if(row == col){
			diagonalSum += val;
		}
		if(row + col == n - 1){
			antiDiagonalSum += val;
		}
		if(Math.abs(rowSum[row]) == n
				|| Math.abs(colSum[col]) == n
				|| Math.abs(diagonalSum) == n
				|| Math.abs(antiDiagonalSum) == n){
			winner = player;
		}
		return winner;
	}
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
