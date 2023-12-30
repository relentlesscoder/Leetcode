package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/29/2023.
 * #2345 https://leetcode.com/problems/finding-the-number-of-visible-mountains/
 */
public class FindingTheNumberOfVisibleMountains {

    // time O(n * log(n)), space O(1)
    public int visibleMountains(int[][] peaks) {
        int res = 0, n = peaks.length, prev = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int x = peaks[i][0], y = peaks[i][1]; // range of x for a mountain with peak [x, y] is [x - y, x + y]
            peaks[i][0] = x - y;
            peaks[i][1] = x + y;
        }
        Arrays.sort(peaks, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]); // sorting the left side in ascending order and right side in descending order
        for (int i = 0; i < n; i++) {
            if (peaks[i][1] <= prev) { // if the right side of the current mountain is less than or equals to the previous right side then it is inside previous mountain
                continue;
            }
            prev = peaks[i][1];
            // special case, the same peaks are all inside each other, for example [1, 3], [1, 3] the visible mountains are 0.
            if (!(i < n - 1 && peaks[i][0] == peaks[i + 1][0] && peaks[i][1] == peaks[i + 1][1])) {
                res++;
            }
        }
        return res;
    }
}
