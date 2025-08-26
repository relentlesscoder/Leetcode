package org.wshuai.leetcode;

/**
 * Created by Wei on 08/24/2025.
 * #3274 https://leetcode.com/problems/check-if-two-chessboard-squares-have-the-same-color/
 */
public class CheckIfTwoChessboardSquaresHaveTheSameColor {

    // time O(1), space O(1)
    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        int c1 = coordinate1.charAt(0) - 'a', c2 = coordinate2.charAt(0) - 'a';
        int r1 = coordinate1.charAt(1) - '1', r2 = coordinate2.charAt(1) - '1';
        return (black(r1, c1) && black(r2, c2)) || (white(r1, c1) && white(r2, c2));
    }

    private boolean black(int x, int y) {
        return (x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1);
    }

    private boolean white(int x, int y) {
        return (x % 2 == 0 && y % 2 == 1) || (x % 2 == 1 && y % 2 == 0);
    }
}
