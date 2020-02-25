package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/16.
 * #419 https://leetcode.com/problems/battleships-in-a-board/
 */
public class BattleshipsInABoard {

	// time O(m*n), space O(1)
	public int countBattleships(char[][] board) {
		if(board == null || board.length == 0 || board[0].length == 0){
			return 0;
		}
		int res = 0, m = board.length, n = board[0].length;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				// the left and upper cell needs to be water
				// for the head of the battleship
				if(board[i][j] == 'X'
					&& (i == 0 || board[i - 1][j] == '.')
					&& (j == 0 || board[i][j - 1] == '.')){
					res++;
				}
			}
		}
		return res;
	}
}
