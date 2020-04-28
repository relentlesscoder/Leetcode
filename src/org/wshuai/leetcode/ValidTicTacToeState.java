package org.wshuai.leetcode;

/**
 * Created by Wei on 10/20/2019.
 * #0794 https://leetcode.com/problems/valid-tic-tac-toe-state/
 */
public class ValidTicTacToeState {
	// time O(n^2), space O(n)
	public boolean validTicTacToe(String[] board) {
		int[] rowScore = new int[3], colScore = new int[3];
		int winX = 0, winO = 0, totalScore = 0, diagonalScore = 0, antiDiagonalScore = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				char c = board[i].charAt(j);
				if(c == ' '){
					continue;
				}
				int score = c == 'X' ? 1 : -1;
				rowScore[i] += score;
				colScore[j] += score;
				totalScore += score;
				antiDiagonalScore += i == j ? score : 0;
				if((i == 0 && j == 2) || (i == 1 && j == 1) || (i == 2 && j == 0)){
					diagonalScore += score;
				}
			}
		}
		for(int i = 0; i < 3; i++){
			winX += rowScore[i] == 3 ? 1 : 0;
			winX += colScore[i] == 3 ? 1 : 0;
			winO += rowScore[i] == -3 ? 1 : 0;
			winO += colScore[i] == -3 ? 1 : 0;
		}
		winX += diagonalScore == 3 ? 1 : 0;
		winX += antiDiagonalScore == 3 ? 1 : 0;
		winO += diagonalScore == -3 ? 1 : 0;
		winO += antiDiagonalScore == -3 ? 1 : 0;
		// p1 plays first so p1 will have same
		// or 1 more move than p2
		if(totalScore < 0 || totalScore > 1){
			return false;
		}
		// p1 and p2 can't win at the same time
		if(winX > 0 && winO > 0){
			return false;
		}
		// p1/p2 can't have more than 2
		// rows/columns/diagonals win at the same time
		if(winX > 2 || winO > 2){
			return false;
		}
		// if p1 win the game, p2 can't move any more
		if(winX > 0 && totalScore == 0){
			return false;
		}
		// if p2 win the game, p1 can't move any more
		if(winO > 0 && totalScore != 0){
			return false;
		}
		return true;
	}
}
