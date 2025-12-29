package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2016.
 * #0130 https://leetcode.com/problems/surrounded-regions/
 */
public class SurroundedRegions {
    private static final int[] DIRS = new int[]{0, -1, 0, 1, 0};

	// time O(m * n), space O(m * n)
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        // 遍历所有在网格外围上的值为O的格子，使用DFS来标记所有与这些格子连通的
		// 值为O的格子-这些共同组成所有无法被捕获的连通区域。把这些格子内容修改
		// 为A。
        int[] cols = new int[]{0, n - 1};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < m; j++) {
                if (board[j][cols[i]] == 'O') {
                    dfs(board, j, cols[i], 'A');
                }
            }
        }
        int[] rows = new int[]{0, m - 1};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                if (board[rows[i]][j] == 'O') {
                    dfs(board, rows[i], j, 'A');
                }
            }
        }
        // 再次遍历网格上余下的值为O的格子，这些格子组成所有的可以背捕获的连通区域。
		// 复用DFS代码将这些格子修改为X。对之前修改为A值的格子，恢复到之前的状态O
		// 因为这些连通区域无法被捕获。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    dfs(board, i, j, 'X');
                } else if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int r, int c, char v) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O') {
            return;
        }
        board[r][c] = v;
        for (int d = 0; d < 4; d++) {
            int x = r + DIRS[d], y = c + DIRS[d + 1];
            dfs(board, x, y, v);
        }
    }
}
