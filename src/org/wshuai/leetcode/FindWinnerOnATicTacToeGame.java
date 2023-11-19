package org.wshuai.leetcode;

/**
 * Created by Wei on 12/02/2019.
 * #1275 https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
 */
public class FindWinnerOnATicTacToeGame {

	public String tictactoe(int[][] moves) {
		int[] rowSum = new int[3], colSum = new int[3];
		int value = 1, diagonalSum = 0, antiDiagonalSum = 0;
		for(int[] move : moves){
			rowSum[move[0]] += value;
			colSum[move[1]] += value;
			if(move[0] == move[1]){
				diagonalSum += value;
			}
			if(move[0] + move[1] == 2){
				antiDiagonalSum += value;
			}
			int state = checkBoard(rowSum[move[0]], colSum[move[1]],
					diagonalSum, antiDiagonalSum);
			if(Math.abs(state) == 3){
				return state == 3 ? "A" : "B";
			}
			value = -value;
		}
		return moves.length < 9 ? "Pending" : "Draw";
	}

	private int checkBoard(int row, int col, int diagonal, int antiDiagonal){
		if(row == 3 || col == 3 || diagonal == 3 || antiDiagonal == 3){
			return 3;
		}
		if(row == -3 || col == -3 || diagonal == -3 || antiDiagonal == -3){
			return -3;
		}
		return 0;
	}
}
