package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/1/2016.
 * #51 https://leetcode.com/problems/n-queens/
 */
public class NQueens {
	//DFS
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> lst = new ArrayList<List<String>>();
		if (n == 1) {
			List<String> l = new ArrayList<String>();
			l.add("Q");
			lst.add(l);
			return lst;
		}
		if (n <= 3) {
			return lst;
		}
		char[][] board = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = '.';
			}
		}
		List<Integer> pos = new ArrayList<Integer>();
		solveNQueensUtil(board, n, lst, 0, pos);
		return lst;
	}

	private void solveNQueensUtil(char[][] board, int n, List<List<String>> lst, int cnt, List<Integer> pos) {
		if (cnt == n) {
			lst.add(printBoard(board, n));
		} else {
			for (int i = 0; i < n; i++) {
				if (isValidMove(pos, cnt, i, n)) {
					board[cnt][i] = 'Q';
					pos.add(cnt * n + i);
					solveNQueensUtil(board, n, lst, cnt + 1, pos);
					board[cnt][i] = '.';
					pos.remove(pos.size() - 1);
				}
			}
		}
	}

	private List<String> printBoard(char[][] board, int n) {
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			StringBuilder row = new StringBuilder();
			for (int j = 0; j < n; j++) {
				row.append(board[i][j]);
			}
			lst.add(row.toString());
		}
		return lst;
	}

	private boolean isValidMove(List<Integer> pos, int r, int c, int n) {
		int len = pos.size();
		for (int i = 0; i < len; i++) {
			int val = pos.get(i);
			int r1 = val / n;
			int c1 = val % n;
			if (r1 == r || c1 == c || Math.abs(r1 - r) == Math.abs(c1 - c)) {
				return false;
			}
		}
		return true;
	}
}
