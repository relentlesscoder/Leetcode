package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/01/2025.
 * #1861 https://leetcode.com/problems/rotating-the-box/
 */
public class RotatingTheBox {

    // time O(m * n), space O(m * n)
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length, n = boxGrid[0].length;
        char[][] res = new char[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(res[i], '.');
        }
        for (int i = 0; i < m; i++) {
            // k is the row that next stone will "drop" to after rotating the box
            // k is set to the bottom of the box initially
            for (int j = n - 1, k = n - 1; j >= 0; j--) {
                if (boxGrid[i][j] == '*') {
                    res[j][m - 1 - i] = '*'; // obstacle stays at the same position after rotating the box
                    k = j - 1; // if we see an obstacle, update k to surface of it
                } else if (boxGrid[i][j] == '#') {
                    res[k--][m - 1 - i] = '#'; // "drop" the stone and update k
                }
            }
        }
        return res;
    }
}
