package org.wshuai.leetcode;

/**
 * Created by Wei on 02/14/2017.
 * #0079 https://leetcode.com/problems/word-search/
 */
public class WordSearch {

	private static final int[] DIRECTIONS = new int[]{0, 1, 0, -1, 0};

	// time O(m*n*4^l)
	public boolean exist(char[][] board, String word) {
		int m = board.length, n = board[0].length;
		char[] arr = word.toCharArray();
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(board[i][j] != arr[0]){
					continue;
				}
				if(dfs(i, j, board, 0, arr)){
					return true;
				}
			}
		}
		return false;
	}

	private boolean dfs(int i, int j, char[][] board, int k, char[] word){
		if(k == word.length){
			return true;
		}
		if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '*' || board[i][j] != word[k]){
			return false;
		}
		char val = board[i][j];
		board[i][j] = '*';
		for(int d = 0; d < 4; d++){
			int x = i + DIRECTIONS[d], y = j + DIRECTIONS[d + 1];
			if(dfs(x, y, board, k + 1, word)){
				return true;
			}
		}
		board[i][j] = val;
		return false;
	}
}
