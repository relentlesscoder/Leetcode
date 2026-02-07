package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/01/2016.
 * #0051 https://leetcode.com/problems/n-queens/
 */
public class NQueens {

    // time O(n^2 * n!), space O(n)
    public List<List<String>> solveNQueens(int n) {
        // 因为每一行必须选一个所以本质上是列的全排列
        List<List<String>> res = new ArrayList<>();
        int[] board = new int[n];
        int cols = 0, // 列是否被占
                diff = 0, // 反对角线是否被占
                sum = 0; // 对角线是否被占
        dfs(0, board, res, cols, diff, sum);
        return res;
    }

    private void dfs(int x, int[] board, List<List<String>> res, int cols, int diff, int sum) {
        int n = board.length;
        if (x == n) { // 所有皇后都放好了则构造矩阵
            List<String> ans = new ArrayList<>();
            for (int c : board) {
                char[] arr = new char[n];
                Arrays.fill(arr, '.');
                arr[c] = 'Q';
                ans.add(new String(arr));
            }
            res.add(ans);
            return;
        }
        for (int y = 0; y < n; y++) {
            if (((1 << y) & cols) == 0 && (1 << (x - y + n) & diff) == 0 && (1 << (x + y) & sum) == 0) {
                cols |= (1 << y);
                diff |= 1 << (x - y + n);
                sum |= 1 << (x + y);
                board[x] = y;
                dfs(x + 1, board, res, cols, diff, sum);
                cols ^= (1 << y);
                diff ^= 1 << (x - y + n);
                sum ^= 1 << (x + y);
            }
        }
    }
}
