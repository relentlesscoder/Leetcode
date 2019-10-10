package org.wshuai.leetcode;

/**
 * Created by Wei on 8/22/19.
 * #999 https://leetcode.com/problems/available-captures-for-rook/
 */
public class AvailableCapturesForRook {
	public int numRookCaptures(char[][] board) {
		int res = 0;
		int r = board.length;
		int c = board[0].length;
		int x = -1, y = -1;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (board[i][j] == 'R') {
					x = i;
					y = j;
					break;
				}
			}
		}
		int i = x;
		int j = y;
		while (i >= 0) {
			if (board[i][j] == 'B' || board[i][j] == 'p') {
				if (board[i][j] == 'p') {
					res++;
				}
				break;
			}
			i--;
		}
		i = x;
		while (j >= 0) {
			if (board[i][j] == 'B' || board[i][j] == 'p') {
				if (board[i][j] == 'p') {
					res++;
				}
				break;
			}
			j--;
		}
		j = y;
		while (i < r) {
			if (board[i][j] == 'B' || board[i][j] == 'p') {
				if (board[i][j] == 'p') {
					res++;
				}
				break;
			}
			i++;
		}
		i = x;
		while (j < c) {
			if (board[i][j] == 'B' || board[i][j] == 'p') {
				if (board[i][j] == 'p') {
					res++;
				}
				break;
			}
			j++;
		}
		return res;
	}
}
