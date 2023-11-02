package org.wshuai.leetcode;

/**
 * Created by Wei on 11/01/2023.
 * #2056 https://leetcode.com/problems/number-of-valid-move-combinations-on-chessboard/
 */
public class NumberOfValidMoveCombinationsOnChessboard {

	private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
	private int[][][] board = new int[4][8][8];

	public int countCombinations(String[] pieces, int[][] positions) {
		return count(pieces, positions, 0);
	}

	public int count(String[] pieces, int[][] positions, int curr) {
		if (curr >= pieces.length) {
			return 1;
		}
		int res = 0, x = positions[curr][0] - 1, y = positions[curr][1] - 1;
		for (int d = 0; d < 8; d++) {
			if ((d < 4 && pieces[curr].equals("bishop")) || (d >= 4 && pieces[curr].equals("rook"))) {
				continue;
			}
			boolean blocked = false;
			for (int step = (res == 0 ? 1 : 2); !blocked; step++) { // only count initial position once
				int nx = x + (step - 1) * dirs[d][0], ny = y + (step - 1) * dirs[d][1];
				if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) {
					break;
				}
				boolean canStop = true;
				for (int pp = 0; pp < curr; pp++) {
					canStop &= (board[pp][nx][ny] >= 0) && (board[pp][nx][ny] < step); // We can stop at a position if no other pieces stopped at that position, and other pieces already went through that position (transitional position with less number of steps).
					blocked |= ((board[pp][nx][ny] < 0) && (-board[pp][nx][ny] <= step)) || (board[pp][nx][ny] == step); // A position is blocked if it is: a final one for another piece with smaller/same number of steps to get there, or a transitional position for another piece with the same number of steps to get there.
				}
				if (canStop) {
					board[curr][nx][ny] = -step;
					res += count(pieces, positions, curr + 1);
				}
				board[curr][nx][ny] = step;
			}
			board[curr] = new int[8][8]; // reset the board for next move direction
		}
		return res;
	}
}
