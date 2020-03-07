package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/12/2019.
 * #0529 https://leetcode.com/problems/minesweeper/
 */
public class Minesweeper {

	private static final int[][] dirs = new int[][]{
		{1, -1, 0, 0, -1, 1, 1, -1},
		{0, 0, 1, -1, -1, 1, -1, 1}
	};

	// time O(m*n), space O(m*n)
	public char[][] updateBoard(char[][] board, int[] click) {
		int m = board.length, n = board[0].length;
		if(board[click[0]][click[1]] == 'M'){
			board[click[0]][click[1]] = 'X';
			return board;
		}
		dfs(click[0], click[1], m, n, board);
		return board;
	}

	private void dfs(int i, int j, int m, int n, char[][] board){
		if(board[i][j] != 'E'){
			return;
		}
		board[i][j] = 'B';
		int mines = 0;
		for(int k = 0; k < 8; k++){
			int x = i + dirs[0][k], y = j + dirs[1][k];
			if(x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'M'){
				mines++;
			}
		}
		if(mines > 0){
			board[i][j] = (char)(mines + '0');
			return;
		}else{
			for(int k = 0; k < 8; k++){
				int x = i + dirs[0][k], y = j + dirs[1][k];
				if(x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'E'){
					dfs(x, y, m, n, board);
				}
			}
		}
	}

	// time O(m*n), space O(m*n)
	public char[][] updateBoardBFS(char[][] board, int[] click) {
		int m = board.length, n = board[0].length;
		if(board[click[0]][click[1]] == 'M'){
			board[click[0]][click[1]] = 'X';
			return board;
		}
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(click);
		board[click[0]][click[1]] = 'B';
		while(!queue.isEmpty()){
			int[] cur = queue.pollFirst();
			int mines = 0;
			for(int k = 0; k < 8; k++){
				int x = cur[0] + dirs[0][k], y = cur[1] + dirs[1][k];
				if(x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'M'){
					mines++;
				}
			}
			if(mines == 0){
				for(int k = 0; k < 8; k++){
					int x = cur[0] + dirs[0][k], y = cur[1] + dirs[1][k];
					if(x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'E'){
						board[x][y] = 'B';
						queue.offerLast(new int[]{x, y});
					}
				}
			}else{
				board[cur[0]][cur[1]] = (char)('0' + mines);
			}
		}
		return board;
	}
}
