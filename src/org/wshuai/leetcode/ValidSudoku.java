package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/08/2016.
 * #0036 https://leetcode.com/problems/valid-sudoku/
 */
public class ValidSudoku {
	// time O(n^2)
	public boolean isValidSudoku(char[][] board) {
		int[] used = new int[9];

		// check rows
		for(int i = 0; i < 9; i++){
			Arrays.fill(used, 0);
			for(int j = 0; j < 9; j++){
				char val = board[i][j];
				if(val == '.'){
					continue;
				}
				if(used[val - '1'] > 0){
					return false;
				}
				used[val - '1'] = 1;
			}
		}

		// check columns
		for(int i = 0; i < 9; i++){
			Arrays.fill(used, 0);
			for(int j = 0; j < 9; j++){
				char val = board[j][i];
				if(val == '.'){
					continue;
				}
				if(used[val - '1'] > 0){
					return false;
				}
				used[val - '1'] = 1;
			}
		}

		// check 3x3 sub-boxes
		for(int k = 0; k < 9; k++){
			Arrays.fill(used, 0);
			for(int i = k/3*3; i < k/3*3 + 3; i++){
				for(int j = k%3*3; j < k%3*3 + 3; j++){
					char val = board[j][i];
					if(val == '.'){
						continue;
					}
					if(used[val - '1'] > 0){
						return false;
					}
					used[val - '1'] = 1;
				}
			}
		}

		return true;
	}
}
