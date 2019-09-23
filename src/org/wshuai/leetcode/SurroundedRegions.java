package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 11/5/16.
 * #130 https://leetcode.com/problems/surrounded-regions/
 */
public class SurroundedRegions {

	//10ms, BFS using Queue
	public void solve(char[][] board) {
		if (board == null) {
			return;
		}
		int rows = board.length;
		if (rows == 0) {
			return;
		}
		int cols = board[0].length;
		if (cols == 0) {
			return;
		}
		int[] vs = new int[]{-1, 1, 0, 0};
		int[] hs = new int[]{0, 0, -1, 1};
		Queue<MatrixCell> queue = new LinkedList<MatrixCell>();
		for (int i = 0; i < rows; i++) {
			if (board[i][0] == 'O') {
				queue.offer(new MatrixCell(i, 0));
			}
			if (board[i][cols - 1] == 'O') {
				queue.offer(new MatrixCell(i, cols - 1));
			}
		}
		for (int i = 0; i < cols; i++) {
			if (board[0][i] == 'O') {
				queue.offer(new MatrixCell(0, i));
			}
			if (board[rows - 1][i] == 'O') {
				queue.offer(new MatrixCell(rows - 1, i));
			}
		}
		while (!queue.isEmpty()) {
			MatrixCell idx = queue.poll();
			int r = idx.x;
			int c = idx.y;
			if (board[r][c] == 'T') {
				continue;
			}
			board[r][c] = 'T';
			for (int x = 0; x < 4; x++) {
				int i1 = r + vs[x];
				int j1 = c + hs[x];
				if (i1 >= 0 && i1 < rows && j1 >= 0 && j1 < cols && board[i1][j1] == 'O') {
					queue.offer(new MatrixCell(i1, j1));
				}
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == 'T') {
					board[i][j] = 'O';
				}
			}
		}
	}

	//BFS, stack overflow error on big matrix
	public void solveBFS(char[][] board) {
		if (board == null) {
			return;
		}
		int rows = board.length;
		if (rows == 0) {
			return;
		}
		int cols = board[0].length;
		if (cols == 0) {
			return;
		}
		int[] vs = new int[]{-1, 1, 0, 0};
		int[] hs = new int[]{0, 0, -1, 1};
		for (int i = 0; i < rows; i++) {
			solveBFSUtil(i, 0, board, hs, vs, rows, cols);
			solveBFSUtil(i, cols - 1, board, hs, vs, rows, cols);
		}
		for (int i = 0; i < cols; i++) {
			solveBFSUtil(0, i, board, hs, vs, rows, cols);
			solveBFSUtil(rows - 1, i, board, hs, vs, rows, cols);
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == 'T') {
					board[i][j] = 'O';
				}
			}
		}
	}

	private void solveBFSUtil(int i, int j, char[][] board, int[] hs, int[] vs, int rows, int cols) {
		if (i >= 0 && i < rows && j >= 0 && j < cols && board[i][j] == 'O') {
			board[i][j] = 'T';
			for (int x = 0; x < 4; x++) {
				solveBFSUtil(i + vs[x], j + hs[x], board, hs, vs, rows, cols);
			}
		}
	}
}

class MatrixCell {
	int x;
	int y;

	public MatrixCell(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
