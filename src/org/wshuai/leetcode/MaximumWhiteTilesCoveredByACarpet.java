package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/29/2023.
 * #2271 https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet/
 */
public class MaximumWhiteTilesCoveredByACarpet {

    // time O(n * log(n)), space O(1)
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        int res = 0, n = tiles.length;
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);
        for (int right = 0, left = 0, covered = 0; right < n; right++) {
            int start = tiles[right][0], end = tiles[right][1];
            covered += end - start + 1; // Add covered tiles

            int carpetLeft = end - carpetLen + 1; // Calculate carpet left end
            while (tiles[left][1] < carpetLeft) { // Slide out tiles that are fully uncovered
                covered -= tiles[left][1] - tiles[left][0] + 1;
                left++;
            }

            // Calculate partially uncovered (could be fully uncovered) tile at window left end
            int uncovered = Math.max(carpetLeft - tiles[left][0], 0);
            res = Math.max(res, covered - uncovered);
        }
        return res;
    }
}
