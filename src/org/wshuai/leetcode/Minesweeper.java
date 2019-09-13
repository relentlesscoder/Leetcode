package org.wshuai.leetcode;

/**
 * Created by Wei on 9/12/2019.
 * #529 https://leetcode.com/problems/minesweeper/
 */
public class Minesweeper {
    private int[][] adj;
    private char[][] board;

    public char[][] updateBoard(char[][] board, int[] click) {
        this.adj = new int[2][8];
        this.adj[0] = new int[]{1, -1, 0, 0, -1, 1, -1, 1};
        this.adj[1] = new int[]{0, 0, 1, -1, -1, 1, 1, -1};
        this.board = board;
        int r = click[0];
        int c = click[1];
        if(board[r][c] == 'M'){
            this.board[r][c] = 'X';
        }else{
            dfs(r, c);
        }
        return this.board;
    }

    private void dfs(int r, int c){
        int mine = calculateMine(r, c);
        if(mine == 0){
            board[r][c] = 'B';
        }else{
            board[r][c] = (char)(mine + '0');
            // key point: only when the cell is updated to 'B', the process continue to do the DFS
            return;
        }
        for(int i = 0; i < 8; i++){
            int x = r + adj[0][i];
            int y = c + adj[1][i];
            if(x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'E'){
                dfs(x, y);
            }
        }
    }

    private int calculateMine(int r, int c){
        int count = 0;
        for(int i = 0; i < 8; i++){
            int x = r + adj[0][i];
            int y = c + adj[1][i];
            if(x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'M'){
                count++;
            }
        }
        return count;
    }
}
