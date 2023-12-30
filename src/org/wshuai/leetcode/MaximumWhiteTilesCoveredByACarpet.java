package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/29/2023.
 * #2271 https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet/
 */
public class MaximumWhiteTilesCoveredByACarpet {

    // time O(n * log(n)), space O(1)
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, (a, b) -> a[0] - b[0]);
        int res = 0, cover = 0, n = tiles.length;
        for (int i = 0, j = 0; res < carpetLen && i < tiles.length; ) {
            if (tiles[j][0] + carpetLen > tiles[i][1]) { // current tiles is fully covered by the carpet
                cover += tiles[i][1] - tiles[i][0] + 1;
                res = Math.max(res, cover);
                i++; // move the tail of the sliding window to process the next tiles
            } else {
                int partial = Math.max(0, tiles[j][0] + carpetLen - tiles[i][0]); // current tiles is partially (or not) covered by the carpet
                res = Math.max(res, cover + partial);
                cover -= (tiles[j][1] - tiles[j][0] + 1); // move the head to exclude the first tiles
                j++;
            }
        }
        return res;
    }
}
