package org.wshuai.leetcode;

/**
 * Created by Wei on 11/09/2016.
 * #0052 https://leetcode.com/problems/n-queens-ii/
 */
public class NQueensII {
	private int res;

	// time O(n^n), space O(n)
	public int totalNQueens(int n) {
		res = 0;
		solve(0, n, new boolean[n], new boolean[n],
				new boolean[2*n - 1], new boolean[2*n - 1]);
		return res;
	}

	private void solve(int row, int n, boolean[] rows, boolean[] columns,
	                   boolean[] diagonals, boolean[] antiDiagonals){
		if(row == n){
			res++;
			return;
		}
		for(int i = 0; i < n; i++){
			if(rows[row] || columns[i] || diagonals[row - i + n - 1] || antiDiagonals[row + i]){
				continue;
			}
			rows[row] = true;
			columns[i] = true;
			antiDiagonals[row + i] = true;
			diagonals[row - i + n - 1] = true;
			solve(row + 1, n, rows, columns, diagonals, antiDiagonals);
			rows[row] = false;
			columns[i] = false;
			antiDiagonals[row + i] = false;
			diagonals[row - i + n - 1] = false;
		}
	}
}
