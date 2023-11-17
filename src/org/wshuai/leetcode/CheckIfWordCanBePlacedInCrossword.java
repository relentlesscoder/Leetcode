package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/16/2023.
 * #2018 https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword/
 */
public class CheckIfWordCanBePlacedInCrossword {

    // time O(m * n * l), space O(m * n)
    public boolean placeWordInCrossword(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        char[][] transpose = new char[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transpose[j][i] = board[i][j];
            }
        }
        String reverse = new StringBuilder(word).reverse().toString();
        return validate(board, word) || validate(board, reverse) || validate(transpose, word) || validate(transpose, reverse);
    }

    private boolean validate(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        int[][] count = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(count[i], -1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '#') {
                    continue;
                }
                if (j == 0 || board[i][j - 1] == '#' || count[i][j - 1] != -1) {
                    int index = j == 0 ? 0 : count[i][j - 1] + 1;
                    if (index < word.length() && (board[i][j] == ' ' || board[i][j] == word.charAt(index))) {
                        count[i][j] = index;
                        if (index == word.length() - 1 && (j == n - 1 || board[i][j + 1] == '#')) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
