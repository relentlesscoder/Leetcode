package org.wshuai.leetcode;

/**
 * Created by Wei on 12/2/19.
 * #1275 https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
 */
public class FindWinnerOnATicTacToeGame {
	public String tictactoe(int[][] moves) {
		int m = moves.length;
		int[][] grid = new int[3][3];
		for(int i = 0; i < m; i++){
			grid[moves[i][0]][moves[i][1]] = i % 2 == 0 ? 1 : -1;
		}
		for(int i = 0; i < 3; i++){
			int s1 = 0;
			int s2 = 0;
			for(int j = 0; j < 3; j++){
				s1 += grid[i][j];
				s2 += grid[j][i];
			}
			if(s1 == 3 || s2 == 3){
				return "A";
			}
			if(s1 == -3 || s2 == -3){
				return "B";
			}
		}
		int sum1 = grid[0][0] + grid[1][1] + grid[2][2];
		int sum2 = grid[0][2] + grid[1][1] + grid[2][0];
		if(sum1 == 3 || sum2 == 3){
			return "A";
		}
		if(sum2 == -3 || sum2 == -3){
			return "B";
		}
		return m == 9 ? "Draw" : "Pending";
	}
}
