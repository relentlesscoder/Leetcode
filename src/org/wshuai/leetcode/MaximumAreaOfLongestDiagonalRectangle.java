package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2025.
 * #3000 https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle/
 */
public class MaximumAreaOfLongestDiagonalRectangle {

    // time O(n), space O(1)
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int res = 0, max = 0;
        for (int[] dimension : dimensions) {
            int diagonal = dimension[0] * dimension[0] + dimension[1] * dimension[1];
            int area = dimension[0] * dimension[1];
            if (diagonal > max || (diagonal == max && area > res)) {
                max = diagonal;
                res = area;
            }
        }
        return res;
    }
}
