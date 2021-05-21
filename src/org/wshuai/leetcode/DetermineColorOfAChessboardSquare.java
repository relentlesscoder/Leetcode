package org.wshuai.leetcode;

/**
 * Created by Wei on 05/21/2021.
 * #1812 https://leetcode.com/problems/determine-color-of-a-chessboard-square/
 */
public class DetermineColorOfAChessboardSquare {

    public boolean squareIsWhite(String coordinates) {
        int x = coordinates.charAt(0) - 'a' + 1, y = coordinates.charAt(1) - '0';
        return !(x % 2 == y % 2);
    }
}
