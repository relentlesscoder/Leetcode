package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/16.
 * #419 https://leetcode.com/problems/battleships-in-a-board/
 */
public class BattleshipsInABoard {

	//O(1) space
	public int countBattleships(char[][] board) {
		if (board == null) {
			return 0;
		}
		int rows = board.length;
		if (rows == 0) {
			return 0;
		}
		int cols = board[0].length;
		int count = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == 'X' && (i == 0 || board[i - 1][j] == '.') && (j == 0 || board[i][j - 1] == '.')) {
					count++;
				}
			}
		}
		return count;
	}

	//O(m*n) space
	public int countBattleshipsExtraSpace(char[][] board) {
		if (board == null) {
			return 0;
		}
		int rows = board.length;
		if (rows == 0) {
			return 0;
		}
		int cols = board[0].length;
		int[][] aux = new int[rows][cols];
		int count = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == '.' || aux[i][j] == 1) {
					continue;
				} else if (board[i][j] == 'X') {
					count++;
					int x = i;
					int y = j;
					while (y < cols && board[x][y] == 'X') {
						aux[x][y] = 1;
						y++;
					}
					x = i;
					y = j;
					while (x < rows && board[x][y] == 'X') {
						aux[x][y] = 1;
						x++;
					}
				}
			}
		}
		return count;
	}
}
