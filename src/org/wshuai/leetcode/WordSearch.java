package org.wshuai.leetcode;

/**
 * Created by Wei on 02/14/2017.
 * #0079 https://leetcode.com/problems/word-search/
 */
public class WordSearch {
	private static final int[][] dirs = new int[][]{
			{1, -1, 0, 0},
			{0, 0, 1, -1}
	};
	private char[][] board;
	private int r, c;

	// time O(m*n*4^l)
	public boolean exist(char[][] board, String word) {
		if(board == null || board.length == 0 || board[0].length == 0 || word == null || word.isEmpty()){
			return false;
		}
		this.board = board;
		r = board.length;
		c = board[0].length;
		char[] chars = word.toCharArray();
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(dfs(i, j, chars, 0)){
					return true;
				}
			}
		}
		return false;
	}

	private boolean dfs(int i, int j, char[] arr, int l){
		if(i < 0 || i >= r || j < 0 || j >= c || board[i][j] == '*' || board[i][j] != arr[l]){
			return false;
		}
		if(l == arr.length - 1){
			return true;
		}
		char val = board[i][j];
		board[i][j] = '*';
		for(int k = 0; k < 4; k++){
			int x = i + dirs[0][k];
			int y = j + dirs[1][k];
			if(dfs(x, y, arr, l + 1)){
				return true;
			}
		}
		board[i][j] = val;
		return false;
	}
}
