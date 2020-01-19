package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/01/2016.
 * #0051 https://leetcode.com/problems/n-queens/
 */
public class NQueens {
	// time O(n^n), space O(n)
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> res = new ArrayList<>();
		solve(0, n, new ArrayList<Integer>(), res, new boolean[n],
				new boolean[n], new boolean[2*n - 1], new boolean[2*n - 1]);
		return res;
	}

	private void solve(int row, int n, List<Integer> cur, List<List<String>> res,
	                   boolean[] rows, boolean[] columns, boolean[] diagonals, boolean[] antiDiagonals){
		if(row == n){
			res.add(populateBoard(cur, n));
			return;
		}
		for(int i = 0; i < n; i++){
			if(rows[row] || columns[i] || diagonals[row - i + n - 1] || antiDiagonals[row + i]){
				continue;
			}
			cur.add(i);
			rows[row] = true;
			columns[i] = true;
			antiDiagonals[row + i] = true;
			diagonals[row - i + n - 1] = true;
			solve(row + 1, n, cur, res, rows, columns, diagonals, antiDiagonals);
			cur.remove(cur.size() - 1);
			rows[row] = false;
			columns[i] = false;
			antiDiagonals[row + i] = false;
			diagonals[row - i + n - 1] = false;
		}
	}

	private List<String> populateBoard(List<Integer> cur, int n){
		List<String> board = new ArrayList<>();
		for(int i = 0; i < cur.size(); i++){
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < n; j++){
				sb.append(cur.get(i) == j ? "Q" : ".");
			}
			board.add(sb.toString());
		}
		return board;
	}
}
