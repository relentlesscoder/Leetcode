package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/2016.
 * #348 https://leetcode.com/problems/design-tic-tac-toe/
 */
public class DesignTicTacToe {
	private int len;
	private int[] rows;
	private int[] cols;
	private int dia = 0;
	private int anti = 0;

	/**
	 * Initialize your data structure here.
	 */
	public DesignTicTacToe(int n) {
		len = n;
		rows = new int[n];
		cols = new int[n];
	}

	/**
	 * Player {player} makes a move at ({row}, {col}).
	 *
	 * @param row    The row of the board.
	 * @param col    The column of the board.
	 * @param player The player, can be either 1 or 2.
	 * @return The current winning condition, can be either:
	 * 0: No one wins.
	 * 1: Player 1 wins.
	 * 2: Player 2 wins.
	 */
	public int move(int row, int col, int player) {
		int val = player == 1 ? 1 : -1;
		int rlen = -1 * len;
		rows[row] += val;
		int x = rows[row];
		if (x == len) {
			return 1;
		} else if (x == rlen) {
			return 2;
		}
		cols[col] += val;
		x = cols[col];
		if (x == len) {
			return 1;
		} else if (x == rlen) {
			return 2;
		}
		if (row == col) {
			dia += val;
			if (dia == len) {
				return 1;
			} else if (dia == rlen) {
				return 2;
			}
		}
		if (row + col == len - 1) {
			anti += val;
			if (anti == len) {
				return 1;
			} else if (anti == rlen) {
				return 2;
			}
		}
		return 0;
	}
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
