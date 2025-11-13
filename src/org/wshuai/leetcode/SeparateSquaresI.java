package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/2025.
 * #3453 https://leetcode.com/problems/separate-squares-i/
 */
public class SeparateSquaresI {

    // time O(n * log(Int.MAX)), space O(1)
    public double separateSquares(int[][] squares) {
        double low = 0, high = 1.0 * Integer.MAX_VALUE;
        while (high - low > 1e-5) {
            double mid = low + (high - low) / 2.0;
            double diff = calc(mid, squares);
            // aAbove - aBelow is too small so go to left
            // side to find a smaller y line to make the
            // diff larger.
            if (diff <= 0) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return low;
    }

    private double calc(double line, int[][] squares) {
        double aAbove = 0, aBelow = 0;
        for (int i = 0; i < squares.length; i++) {
            int x = squares[i][0], y = squares[i][1], l = squares[i][2];
            double total = (double) l * l;

            if (line <= y) {
                aAbove += total;
            } else if (line >= y + l) {
                aBelow += total;
            } else {
                // The line intersects the square.
                double aboveHeight = (y + l) - line;
                double belowHeight = line - y;
                aAbove += l * aboveHeight;
                aBelow += l * belowHeight;
            }
        }
        return aAbove - aBelow;
    }
}
