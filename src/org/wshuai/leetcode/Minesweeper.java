package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/12/2019.
 * #0529 https://leetcode.com/problems/minesweeper/
 */
public class Minesweeper {

    private static final int[] DIRS = new int[]{1, -1, 0, -1, -1, 1, 1, 0, 1};

    // time O(m * n), space O(m * n)
    public char[][] updateBoardDFS(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        // 中奖了
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        dfs(board, x, y);
        return board;
    }

    private void dfs(char[][] board, int x, int y) {
		/* 示例1:
		click = [3,0]
		[["E","E","E","E","E"],
 		 ["E","E","M","E","E"],
 		 ["E","E","E","E","E"],
 		 ["E","E","E","E","E"]]

 		[["B","1","E","1","B"],
  		 ["B","1","M","1","B"],
  		 ["B","1","1","1","B"],
  		 ["B","B","B","B","B"]]
		*/
        int count = 0, m = board.length, n = board[0].length;
        // 统计相邻格子中地雷的数量
        for (int d = 0; d < 8; d++) {
            int r = x + DIRS[d], c = y + DIRS[d + 1];
            if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M') {
                count++;
            }
        }
        // 如果相邻的格子有地雷，更新格子的值为相邻的地雷的数量并停止继续搜索
        if (count > 0) {
            board[x][y] = (char) ('0' + count);
        } else {
            // 相邻的格子没有地雷，更新格子的值为B并继续搜索
            board[x][y] = 'B';
            for (int d = 0; d < 8; d++) {
                int r = x + DIRS[d], c = y + DIRS[d + 1];
                if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E') {
                    dfs(board, r, c);
                }
            }
        }
    }

    // time O(m * n), space O(min(m, n))
    public char[][] updateBoardBFS(char[][] board, int[] click) {
        int m = board.length, n = board[0].length, x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        board[x][y] = 'B';
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int count = 0;
            for (int d = 0; d < 8; d++) {
                int r = curr[0] + DIRS[d], c = curr[1] + DIRS[d + 1];
                if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M') {
                    count++;
                }
            }
            if (count > 0) {
                board[curr[0]][curr[1]] = (char) ('0' + count);
            } else {
                for (int d = 0; d < 8; d++) {
                    int r = curr[0] + DIRS[d], c = curr[1] + DIRS[d + 1];
                    if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E') {
                        board[r][c] = 'B';
                        queue.offer(new int[]{r, c});
                    }
                }
            }
        }
        return board;
    }
}
